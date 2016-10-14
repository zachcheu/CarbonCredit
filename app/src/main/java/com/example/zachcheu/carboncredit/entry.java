package com.example.zachcheu.carboncredit;

/**
 * Created by ZachCheu on 10/14/16.
 */

public class entry {
    private double timeZero;
    private int avgCarEffic;
    private int persCarEffic;
    private int totalRefresh;
    private int totalRefreshIdle;
    private double speedDiff;
    private int carbCred;

    public entry(double timeZero, int avgCarEffic, int persCarEffic, int totalRefresh, int totalRefreshIdle, double speedDiff){
        this.timeZero = timeZero;
        this.avgCarEffic = avgCarEffic;
        this.persCarEffic = persCarEffic;
        this.totalRefresh = totalRefresh;
        this.totalRefreshIdle = totalRefreshIdle;
        this.speedDiff = speedDiff;
    }
}
