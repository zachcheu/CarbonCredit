package com.example.zachcheu.carboncredit;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by DevWork on 10/27/16.
 */

public class PointsFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private ImageView profilePic;
    private TextView infoLevel, level, points, textNextLevel, pointsNextLevel;
    private Typeface gothic,arcade;
    private ProgressBar xpBar;
    private int totalXp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.point_layout, container, false);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);

        System.out.println("Map fragment started");
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences setting = getActivity().getSharedPreferences("PrefFile", 0);
        totalXp = setting.getInt("xp",0)+Var.startXp;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        profilePic = (ImageView) getActivity().findViewById(R.id.profilePic);
        infoLevel = (TextView)getActivity().findViewById(R.id.infoLevel);
        level = (TextView)getActivity().findViewById(R.id.level);
        points = (TextView)getActivity().findViewById(R.id.points);
        textNextLevel = (TextView)getActivity().findViewById(R.id.textNextLevel);
        pointsNextLevel = (TextView)getActivity().findViewById(R.id.pointsNextLevel);
        xpBar = (ProgressBar)getActivity().findViewById(R.id.progressBar);
        xpBar.setScaleY(2f);
        profilePic.setBackgroundResource(R.mipmap.rocket);
        profilePic.setScaleX(0.8f);
        profilePic.setScaleY(0.8f);
        gothic = Typeface.createFromAsset(getActivity().getAssets(),"fonts/gothic.ttf");
        arcade = Typeface.createFromAsset(getActivity().getAssets(),"fonts/arcade.ttf");
        infoLevel.setTypeface(gothic);
        level.setTypeface(arcade);
        points.setTypeface(arcade);
        textNextLevel.setTypeface(gothic);
        pointsNextLevel.setTypeface(arcade);
        profilePic.getLayoutParams().height=size.x/3;
        profilePic.getLayoutParams().width=size.x/3;
        update();
    }
    public void update(){
        //xpBar.setProgress(25);
        System.out.println(getSetProgressValue(totalXp));
        System.out.println(getSetMaxValue(totalXp));
        System.out.println("Progress: "+(int)(100*((double)getSetProgressValue(totalXp)/(double)getSetMaxValue(totalXp))));
        xpBar.setProgress((int)(100*((double)getSetProgressValue(totalXp)/(double)getSetMaxValue(totalXp))));
        //xpBar.setMax(getSetMaxValue(totalXp));
        level.setText(getRank(totalXp));
        points.setText(totalXp+" pts");
        pointsNextLevel.setText(getSetMaxValue(totalXp)-getSetProgressValue(totalXp) +" pts");
        xpBar.invalidate();
    }
    public int getSetProgressValue(int totalXp){
        for(int i =0 ; i<Var.xpLvl.length; i++){
            if(totalXp-Var.xpLvl[i]>0){
                totalXp-=Var.xpLvl[i];
            }else{
                break;
            }
        }
        return totalXp;
    }
    public int getSetMaxValue(int totalXp){
        int i;
        for(i =0; i<Var.xpLvl.length;i++){
            if(totalXp-Var.xpLvl[i]>0){
                totalXp-=Var.xpLvl[i];
            }else{
                break;
            }
        }
        return Var.xpLvl[i];
    }
    public String getRank(int totalXp){
        int i;
        for(i =0; i<Var.xpLvl.length;i++){
            if(totalXp-Var.xpLvl[i]>0){
                totalXp-=Var.xpLvl[i];
            }else{
                break;
            }
        }
        return Var.xpName[i];
    }
}
