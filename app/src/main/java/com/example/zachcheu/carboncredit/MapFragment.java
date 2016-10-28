package com.example.zachcheu.carboncredit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by DevWork on 10/27/16.
 */

public class MapFragment extends Fragment implements LocationListener {
    public static final String ARG_OBJECT = "object";

    public MapView mapView;
    public LocationManager lm;
    public DrivePointManager drivePointManager;
    public Context mContext;
    Typeface typeface;

    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MapboxAccountManager.getInstance();
        View rootView = inflater.inflate(R.layout.map_layout, container, false);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);

        drivePointManager = new DrivePointManager();
        lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        textView = (TextView) rootView.findViewById(R.id.speedText);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        textView.setTypeface(typeface);
        System.out.println("test");


        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                // this is where we interact with the map
                mapboxMap.setStyleUrl(Style.MAPBOX_STREETS);
                //mapboxMap.setMaxZoom(10);
                mapboxMap.setMyLocationEnabled(true);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(mapboxMap.getMyLocation().getLatitude(), mapboxMap.getMyLocation().getLongitude()))
                        .zoom(13)
                        .bearing(180)
                        .tilt(30)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000);

                mapboxMap.getUiSettings().setTiltGesturesEnabled(false);
                //mapboxMap.easeCamera(CameraUpdateFactory.newCameraPosition());
            }
        });

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
        return rootView;
    }

    private void addLine() {

        final DrivePoint lastPoint = drivePointManager.getLastPoint();
        final DrivePoint secondtoLastPoint = drivePointManager.getSecondToLastPoint();

        if (lastPoint == null)
            return;

        if (secondtoLastPoint == null)
            return;

        int speed1 = lastPoint.getSpeed();
        int speed2 = secondtoLastPoint.getSpeed();

        // calculate average speed between two points
        int averageSpeed = (speed1 + speed2) / 2;

        final int color;

        /*
         * Determine color condition
         */
        if (averageSpeed >= 0 && averageSpeed < 25) {
            color = Color.parseColor("#2ecc71");
        } else if (averageSpeed >= 25 && averageSpeed <= 40) {
            color = Color.parseColor("#e67e22");
        } else {
            color = Color.parseColor("#c0392b");
        }


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(secondtoLastPoint.getmLocation().getLatitude(), secondtoLastPoint.getmLocation().getLongitude()))
                        .add(new LatLng(lastPoint.getmLocation().getLatitude(), lastPoint.getmLocation().getLongitude()))
                        .add(new LatLng(secondtoLastPoint.getmLocation().getLatitude(), secondtoLastPoint.getmLocation().getLongitude()))
                        .width(5)
                        .color(color));
            }
        });
    }

    @Override
    public void onLocationChanged(final Location location) {

        //@ param 1 : time -> we still need to calculate it properly...default is 0 for now
        //@ last param: isHighway...still need google maps api for this to work
        final Location temp = location;
        drivePointManager.addPoint(new DrivePoint(
                0,
                (int) (location.getSpeed() * 2.2369),
                location,
                false));

        addLine();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(location.hasSpeed())
                    textView.setText("Speed: " + String.valueOf((int) (temp.getSpeed() * 2.2369)) + " (mph)");
                else
                    textView.setText("Speed: 0 (mph)");
            }
        });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public void setContext(Context m) {
        this.mContext = m;
    }
}
