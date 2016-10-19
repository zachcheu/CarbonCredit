package com.example.zachcheu.carboncredit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;

public class FirstScreenFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private View buttonView = null;
    FloatingActionButton start;
    Animation animationFadeIn;
    Handler myHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.screen1, container, false);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);
        myHandler = new Handler();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonView = getView().findViewById(R.id.floating_start);
        buttonView.setVisibility(View.GONE);
        start = (FloatingActionButton) getView().findViewById(R.id.floating_start);
        animationFadeIn = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),android.R.anim.fade_in);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Drive.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        buttonView.setVisibility(View.GONE);
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonView.setVisibility(View.VISIBLE);
                buttonView.startAnimation(animationFadeIn);
            }
        },1000);
    }
}