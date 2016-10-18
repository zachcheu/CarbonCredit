package com.example.zachcheu.carboncredit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SecondScreenFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.screen2, container, false);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);
        return rootView;
    }
}