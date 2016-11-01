package com.example.zachcheu.carboncredit;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    public static class MySettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
