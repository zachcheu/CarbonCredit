package com.example.zachcheu.carboncredit;
public class Log {
    private String credit;
    private int date;
    private double time;
    private int dist;

    public Log() {
    }

    public Log(String name, int date, double time, int dist) {
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

}