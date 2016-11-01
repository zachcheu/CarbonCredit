package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ZachCheu on 10/31/16.
 */

public class LogoScreenStart extends Activity {
    ImageView logo;
    TextView Title, Desc;
    Animation animationFadeIn;
    Animation in;
    Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.logo_start);
        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);
        handle = new Handler();

        animationFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
        logo = (ImageView) findViewById(R.id.logoStart);
        logo.setImageResource(R.drawable.logo);
        Title = (TextView) findViewById(R.id.title);
        Desc = (TextView) findViewById(R.id.desc);
        Title.setText("Polar Drive");
        Desc.setText("-Closer To Zero-");
    }

    @Override
    public void onStart() {
        super.onStart();
        Title.setAnimation(in);
        Desc.setAnimation(in);
        handle.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(LogoScreenStart.this,launcher.class);
                startActivity(i);
            }
        }, 3000);
    }

}
