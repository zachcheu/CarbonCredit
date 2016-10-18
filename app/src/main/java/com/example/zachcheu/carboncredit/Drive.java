package com.example.zachcheu.carboncredit;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Drive extends Activity implements LocationListener {

    // gets context location!
    private LocationManager lm;
    private TextView textView;
    private Long start_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive);

        textView = (TextView) findViewById(R.id.speed);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "gothic.ttf");
        textView.setTypeface(typeface);
        start_time = System.currentTimeMillis();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
    }

    @Override
    public void onLocationChanged(final Location location) {
        Drive.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    drivePointManager.addPoint(new DrivePoint(System.currentTimeMillis()-start_time, (int)(location.getSpeed() * 2.2369), location, false));
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
}