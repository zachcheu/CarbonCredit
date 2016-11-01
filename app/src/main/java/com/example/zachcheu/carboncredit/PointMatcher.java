package com.example.zachcheu.carboncredit;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PointMatcher {
	/**
	 * Delay between syncing points
	 */
	private static final int SYNC_DELAY = 30 * 1000;
	/**
	 * When points were last synced.
	 */
	private static long lastSyncTime = System.currentTimeMillis();
	private static final Gson gson = new GsonBuilder().serializeNulls().create();
	private static final String ENDPOINT = "http://test.roadmatching.com/rest/mapmatch";
	/**
	 * Callback for after points have synced.
	 */
	private static PointMatcherCallback callback;
	// Hardcoding API keys is totally the best practice...
	private static final String APP_ID = "383309be";
	private static final String APP_KEY = "0deccee154174c9f9615a8e393e7236c";

	private static Map<Long, DrivePoint> drivePoints = new HashMap<>();
	private static List<UploadLocationInfo> locations = new ArrayList<>();
	private static long curWaypointID = 0;
	private static final PointSyncThread THREAD;
	static {
		THREAD = new PointSyncThread();
		THREAD.start();
	}

	public static void setCallback(PointMatcherCallback callback) {
		if (callback == null) {
			throw new IllegalArgumentException("callback must not be null!");
		}
		PointMatcher.callback = callback;
	}

	public static void addPoint(DrivePoint point) {
		if (callback == null) {
			throw new IllegalStateException("Must register a callback with setCallback before adding points!");
		}
		drivePoints.put(curWaypointID, point);
		synchronized (locations) {
			locations.add(new UploadLocationInfo(point.getmLocation(), curWaypointID));
		}
		curWaypointID++;
		syncIfNeeded();
	}

	private static void syncIfNeeded() {
		if (System.currentTimeMillis() - lastSyncTime >= SYNC_DELAY) {
			THREAD.sync();
		}
	}

	/**
	 * Performed from a seperate thread
	 */
	private static void sync() {
		try {
			MatchingResult res = doMatch();
			List<DrivePointPair> pointPairs = new ArrayList<>();
			for (DiaryEntry entry : res.diary.entries) {
				for (Link link : entry.route.entries) {
					Wpt src = link.getSrc();
					Wpt dst = link.getDst();
					DrivePoint dpSrc = drivePoints.get(link.src).clone();
					DrivePoint dpDst = drivePoints.get(link.src).clone();
					dpSrc.getmLocation().setLongitude(src.x);
					dpSrc.getmLocation().setLatitude(src.y);
					dpDst.getmLocation().setLongitude(dst.x);
					dpDst.getmLocation().setLatitude(dst.y);
					pointPairs.add(new DrivePointPair(dpSrc, dpDst));
				}
			}
			callback.addLines(pointPairs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Performs an HTTP request
	 * @return
	 */
	private static MatchingResult doMatch() throws Exception {
		HttpURLConnection connection = (HttpURLConnection) (new URL(ENDPOINT
				+ "?app_id=" + APP_ID + "&app_key=" + APP_KEY)).openConnection();

		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "text/csv");

		connection.setDoOutput(true); // Triggers POST.

		try (OutputStream output = connection.getOutputStream()) {
			try (PrintStream ps = new PrintStream(output)) {
				synchronized (locations) {
					for (UploadLocationInfo info : locations) {
						ps.printf("%d,%f,%f,%tFT%tT%n",
								info.waypointID,
								info.location.getLongitude(),
								info.location.getLatitude(),
								info.location.getTime());
					}

					locations.clear();
					lastSyncTime = System.currentTimeMillis();
				}
			}
		}
		
		try (InputStream stream = connection.getInputStream()) {
			try (InputStreamReader reader = new InputStreamReader(stream)) {
				return gson.fromJson(reader, MatchingResult.class);
			}
		} catch (Exception e) {
			e.printStackTrace();

			try (InputStream stream = connection.getErrorStream()) {
				try (Scanner sc = new Scanner(stream)) {
					throw new RuntimeException(sc.useDelimiter("\\Z").next());
				}
			}
		}
	}

	private static class PointSyncThread extends Thread {
		private volatile boolean isReadyToSync = false;
		@Override
		public void run() {
			while (true) {
				yield();
				if (isReadyToSync) {
					PointMatcher.sync();
				}
			}
		}

		/**
		 * Marks thread as ready to sync
		 */
		public void sync() {
			isReadyToSync = true;
		}
	}
	private static class UploadLocationInfo {
		public UploadLocationInfo(Location location, long waypointID) {
			this.location = location;
			this.waypointID = waypointID;
		}

		public Location location;
		public long waypointID;
	}
	
	private static class MatchingResult {
		public Diary diary;
	}
	private static class Diary {
		public DiaryEntry[] entries;
	}
	private static class DiaryEntry {
		public Route route;
	}
	private static class Route {
		public Link[] entries;
	}
	private static class Link {
		public long id;
		public long src;
		public long dst;
		public float err;
		public String geometry;
		public Wpt[] wpts;
		public Wpt getSrc() {
			return getWpt(src);
		}
		public Wpt getDst() {
			return getWpt(dst);
		}
		public Wpt getWpt(long id) {
			for (Wpt wpt : wpts) {
				if (wpt.id == id) {
					return wpt;
				}
			}
			return null;
		}
	}
	private static class Wpt {
		public long id;
		public float x;
		public float y;
	}
}
