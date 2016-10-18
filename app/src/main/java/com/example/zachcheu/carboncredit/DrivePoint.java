package com.example.zachcheu.carboncredit;

import android.location.Location;

/**
 * Created by DevWork on 10/14/16.
 */

public class DrivePoint {
    //test

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

    private float time;

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

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public DrivePoint(float time, Integer speed, Location mLocation, boolean isHighway) {
        this.time = time;
        this.speed = speed;
        this.mLocation = mLocation;
        this.isHighway = isHighway;
    }


}