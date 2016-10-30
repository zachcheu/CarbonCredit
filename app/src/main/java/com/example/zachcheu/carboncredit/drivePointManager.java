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
    public long getDriveTime(){
        //fix when time is understood

        System.out.print("Drive Time: "+points.get(points.size()-1).getTime());
        return points.get(points.size()-1).getTime()-points.get(0).getTime();
    }
    public int getAverageSpeed(){
        int total = 0;
        for(int i = 0; i<points.size();i++){
            total += points.get(i).getSpeed();

        }
        return total/points.size();
    }
    public long getDistance(){
        //fix when time is understood
        System.out.println("Distance: "+getAverageSpeed()*getDriveTime());
        return getAverageSpeed()*getDriveTime();
    }
    public long sinceDrive(){
        System.out.println("Since Drive: "+points.get(1).getTime());
        return points.get(points.size()-1).getTime();
    }
}