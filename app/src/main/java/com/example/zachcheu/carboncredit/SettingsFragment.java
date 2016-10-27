package com.example.zachcheu.carboncredit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DevWork on 10/27/16.
 */

public class SettingsFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.settings_layout, container, false);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);

        System.out.println("Map fragment started");
        return rootView;
    }
}
