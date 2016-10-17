package com.example.zachcheu.carboncredit;

import java.util.ArrayList;

/**
 * Created by DevWork on 10/16/16.
 */

public class drivePointManager {
    //test

    static ArrayList<DrivePoint> points;

    public drivePointManager() {
        points = new ArrayList<DrivePoint>();
    }

    public static void addPoint(DrivePoint d) {
        points.add(d);
    }

    public DrivePoint getDrivePoint(int index){
        return points.get(index);
    }
}