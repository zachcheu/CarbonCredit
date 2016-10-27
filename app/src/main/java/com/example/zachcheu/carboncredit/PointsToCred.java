package com.example.zachcheu.carboncredit;


import java.util.ArrayList;

public class PointsToCred {

    private static ArrayList<DrivePoint> list;
    private int CreditEffi;
    private int CreditIdle;
    private int CreditLimit;
    private int CreditAccel;
    private int CarbonCred;
    public PointsToCred(ArrayList<DrivePoint> list){
        this.list = list;
        if(list.size()>2) {
            CreditEffi = CreditCarEffi();
            CreditIdle = (int) CreditCarIdle();
            if(CreditIdle<0){
                CreditIdle = 100;
            }
            CreditLimit = CreditCarLimit();
            CreditAccel = CreditCarAccel();
            CarbonCred = 50 + CreditEffi + CreditIdle + CreditLimit + CreditAccel;
            System.out.println("Idle: "+CreditIdle + "Effi: "+CreditEffi + "Limit: "+CreditLimit + "Accel: "+CreditAccel);
        }
    }
    public static int CreditCarEffi(){
        return Var.avgMph-Var.carsMph[Var.Trans]*(2/3);
    }
    public static float CreditCarIdle(){
        float time1 = 0;
        float time2 = 0;
        float timeTot = 0;
        for(int i = 0; i< list.size();i++){
            if(list.get(i).getSpeed().equals(0)){
                time1 = Math.round(list.get(i).getTime());
                for(int j = i+1; j<list.size();j++) {
                    if (!list.get(j).getSpeed().equals(0)) {
                        time2 = Math.round(list.get(j).getTime());
                        j = list.size();
                    }
                }
            }
            timeTot +=time2-time1;
        }
        return (Var.idleBase-Math.round(list.get(list.size()-1).getTime()-timeTot))*10;
    }
    public static int CreditCarLimit(){
        int up = 0;
        int down = 0;
        int totSpeed = 0;
        for(int i = 0; i<list.size();i++){
            if(list.get(i).isHighway()){
                up = Var.highUp;
                down = Var.highDown;
            }else{
                up = Var.localUp;
                down = Var.localDown;
            }
            if(list.get(i).getSpeed()>up){
                totSpeed+=list.get(i).getSpeed()-up;
            }
            if(list.get(i).getSpeed()<down){
                totSpeed+=down-list.get(i).getSpeed();
            }
        }
        return (totSpeed*10)/list.size();
    }
    public static int CreditCarAccel(){
        int totChangeSpeed = 0;
        for(int i = 1; i<list.size();i++){
            totChangeSpeed += Math.abs(list.get(i-1).getSpeed()-list.get(i).getSpeed());
        }
        return (int)Math.round((7.5-(totChangeSpeed/list.size()))*4);
    }
    public int getCarbonCredit(){
        return CarbonCred;
    }
    public String best(){
        int largest = CreditEffi;
        String large = "Car Efficiency";
        if(largest<CreditAccel){
            largest = CreditAccel;
            large = "Acceleration";
        }if(largest<CreditLimit){
            largest = CreditLimit;
            large = "Staying within Speed Limit";
        }if(largest<CreditIdle){
            largest = CreditIdle;
            large = "fuel efficency by avoiding idle time";
        }
        return large;
    }
    public String worst(){
        int smallest = CreditEffi;
        String small = "Car Efficiency";
        if(smallest>CreditAccel){
            smallest = CreditAccel;
            small = "Acceleration";
        }if(smallest>CreditLimit){
            smallest = CreditLimit;
            small = "Staying within Speed Limit";
        }if(smallest>CreditIdle){
            smallest = CreditIdle;
            small = "fuel efficency by avoiding idle time";
        }
        return small;
    }
}