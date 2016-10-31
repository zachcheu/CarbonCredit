package com.example.zachcheu.carboncredit;

public class Player {
    private int rank;
    private String user;
    private long time;
    private long dist;
    private int picId;

    public Player() {
    }

    public Player(int rank, String user, long time, long dist, int picId) {
        this.rank = rank;
        this.user = user;
        this.time = time;
        this.dist = dist;
        this.picId = picId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public void setPicId(int id){
        picId = id;
    }
    public int getPicId(){
        return picId;
    }

    public long getDist() {
        return dist;
    }

    public void setDist(long dist) {
        this.dist = dist;
    }

}