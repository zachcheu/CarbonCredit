package com.example.zachcheu.carboncredit;
import java.util.ArrayList;

public class DrivePointManager {

    ArrayList<DrivePoint> points;

    public DrivePointManager() {
        points = new ArrayList<DrivePoint>();
    }

    public ArrayList<DrivePoint> getDrivePoints(){
        return this.points;
    }

    public void addPoint(DrivePoint d) {
        this.points.add(d);
    }

    public DrivePoint getDrivePoint(int index){
        return points.get(index);
    }

    public DrivePoint getLastPoint() {
        if (points.size() < 2) {
            // Must have at least 2 points for a line
            return null;
        }
        return points.get(points.size() - 1);
    }
    public DrivePoint getSecondToLastPoint() {
        if (points.size() < 2) {
            // Must have at least 2 points for a line
            return null;
        }
        return points.get(points.size() - 2);
    }
    public ArrayList<DrivePoint> getDriveList(){
        return points;
    }
}