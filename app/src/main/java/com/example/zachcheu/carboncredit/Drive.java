package com.example.zachcheu.carboncredit;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.mapbox.mapboxsdk.maps.MapView;

/**
 * Created by ZachCheu on 10/13/16.
 */
public class Drive extends ActionBarActivity implements LocationListener {

    // gets context location!
    private LocationManager lm;
    private TextView textView;
    public MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive);

        textView = (TextView) findViewById(R.id.speedView);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
    }

    @Override
    public void onLocationChanged(final Location location) {
        Drive.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf((int) (location.getSpeed()*2.2369)));
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
    public void onPause(){
        super.onPause();
        //mapView.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        //mapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        //mapView.onLowMemory();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //mapView.onDestroy();
    }
}

