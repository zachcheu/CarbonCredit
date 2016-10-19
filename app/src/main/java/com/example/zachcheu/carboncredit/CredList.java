package com.example.zachcheu.carboncredit;

import java.util.ArrayList;

/**
 * Created by ZachCheu on 10/18/16.
 */

public class CredList {
    ArrayList<PointsToCred> list;
    int[] carbonCredit = new int[10];
    int[] day = new int[] {1,2,3,4,5,6,7,8,9,10};
    public CredList(){
        list = new ArrayList<>();
    }
    public void addCred(PointsToCred c){
        list.add(c);
    }
    public void arrayCreate(){
        for(int i = list.size()-10;i<list.size();i++){
            carbonCredit[i] = list.get(i).getCarbonCredit();
        }
    }
}
