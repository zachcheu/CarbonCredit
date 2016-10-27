package com.example.zachcheu.carboncredit;
public class Log {
    private String credit;
    private String date;
    private double time;
    private float dist;

    public Log() {
    }

    public Log(String name, String date, double time, float dist) {
        this.credit = name;
        this.date = date;
        this.time = time;
        this.dist = dist;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String name) {
        this.credit = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public float getDist() {
        return dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

}