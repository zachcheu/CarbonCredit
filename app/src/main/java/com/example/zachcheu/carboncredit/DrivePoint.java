package com.example.zachcheu.carboncredit;
import android.location.Location;

/**
 * Created by DevWork on 10/14/16.
 */

public class  DrivePoint {

    public boolean isHighway() {
        return isHighway;
    }

    public void setHighway(boolean highway) {
        isHighway = highway;
    }

    private boolean isHighway;
    /*
     * Location information
     */
    private Location mLocation;

    private Integer speed = null;

    private long time;

    public Location getmLocation() {
        return mLocation;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public DrivePoint() {

    }

    public DrivePoint(long time, Integer speed, Location mLocation, boolean isHighway) {
        this.time = time;
        this.speed = speed;
        this.mLocation = mLocation;
        this.isHighway = isHighway;
    }


}