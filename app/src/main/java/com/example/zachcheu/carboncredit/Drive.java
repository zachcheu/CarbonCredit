package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
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

import java.util.ArrayList;
import java.util.List;

public class Drive extends Activity implements LocationListener {

    // gets context location!
    private LocationManager lm;
    private TextView textView;
    public MapView mapView;
    public DrivePointManager drivePointManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapboxAccountManager.start(this, getString(R.string.access_token));
        setContentView(R.layout.drive);

        drivePointManager = new DrivePointManager();
        textView = (TextView) findViewById(R.id.speedView);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "gothic.ttf");
        textView.setTypeface(typeface);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                // this is where we interact with the map
                mapboxMap.setStyleUrl(Style.LIGHT);
                //mapboxMap.setMaxZoom(10);
                mapboxMap.setMyLocationEnabled(true);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(mapboxMap.getMyLocation().getLatitude(), mapboxMap.getMyLocation().getLongitude()))
                        .zoom(17)
                        .bearing(180)
                        .tilt(30)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 7000);
                addPoints(mapboxMap);

                //mapboxMap.easeCamera(CameraUpdateFactory.newCameraPosition());
            }
        });

        // lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
        System.currentTimeMillis();

    }

    //drawing points test
    private void addPoints(final MapboxMap mapboxMap) {
        final List<LatLng> c = new ArrayList<>();
        c.add(new LatLng((double) (47.673988), (double) (-122.121513)));
        c.add(new LatLng((double) (40.730610), (double) (-73.935242)));
        c.add(new LatLng((double) (47.673988), (double) (-122.121513)));

        Drive.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapboxMap.addPolyline(new PolylineOptions()
                        .addAll(c)
                        .width(10)
                        .color(Color.parseColor("#191919"))) ;
            }
        });
    }



    @Override
    public void onLocationChanged(final Location location) {
        //@ param 1 : time -> we still need to calculate it properly...default is 0 for now
        //@ last param: isHighway...still need google maps api for this to work
        drivePointManager.addPoint(new DrivePoint(
                0.0f,
                (int)(location.getSpeed() * 2.2369),
                location,
                false));

        Drive.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf((int) (location.getSpeed() * 2.2369)));

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
        //TO DO!
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    public void startTimer(){

    }

    public void stopTimer(){
        SystemClock s;
    }
}