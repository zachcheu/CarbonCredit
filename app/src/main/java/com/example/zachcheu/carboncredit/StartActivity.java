package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;

/**
 * Created by ZachCheu on 10/17/16.
 */
public class StartActivity extends Activity{
    private View buttonView;
    FloatingActionButton start;
    Animation animationFadeIn;
    Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        myHandler = new Handler();
        buttonView = findViewById(R.id.floating_start);
        buttonView.setVisibility(View.GONE);
        start = (FloatingActionButton) findViewById(R.id.floating_start);
        animationFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonView.setVisibility(View.VISIBLE);
                buttonView.startAnimation(animationFadeIn);
            }
        },1000);
    }
}
