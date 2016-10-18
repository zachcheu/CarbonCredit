package com.example.zachcheu.carboncredit;

import java.util.ArrayList;

/**
 * Created by DevWork on 10/16/16.
 */

public class DrivePointManager {

    ArrayList<DrivePoint> points;

    public DrivePointManager() {
        points = new ArrayList<DrivePoint>();
    }

    public void addPoint(DrivePoint d) {
        this.points.add(d);
    }

    public DrivePoint getDrivePoint(int index){
        return points.get(index);
    }
}