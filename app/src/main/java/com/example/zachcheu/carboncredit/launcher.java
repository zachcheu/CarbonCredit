package com.example.zachcheu.carboncredit;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.mapbox.mapboxsdk.MapboxAccountManager;

import java.util.ArrayList;

/**
 * Created by DevWork on 10/27/16.
 */

public class launcher extends AppCompatActivity{

    /*
     * Custom adapter to manage fragments
     */
    public CollectionPagerAdapter adapter;

    /*
     * Custom viewpager to disable scrolling
     */
    public CustomViewPager customViewPager;


    /*
     * Holds the various images for the navbar
     */
    public ArrayList<AHBottomNavigationItem> _itemlist;


    /*
     * library used to create navbar
     */
    public static AHBottomNavigation bottomNavigation;

    /*
     * list that holds all fragments
     */
    public ArrayList<Fragment> fragmentArrayList;

    /*
     * fragments
     */
    public MapFragment mapFragment;
    public LogsFragment logsFragment;
    public PointsFragment pointsFragment;
    public SettingsFragment settingsFragment;

    public static PointF pointF;

    public Frug f = new Frug();

    public static float dis;


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        MapboxAccountManager.start(this, getString(R.string.access_token));
        //setStatusBarTranslucent(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.launcher_layout);

        // finish initialization of variables
        adapter = new CollectionPagerAdapter(getSupportFragmentManager());
        pointF = new PointF();
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        _itemlist = new ArrayList<AHBottomNavigationItem>();
        fragmentArrayList = new ArrayList<Fragment>();
        mapFragment = new MapFragment();
        logsFragment = new LogsFragment();
        pointsFragment = new PointsFragment();
        settingsFragment = new SettingsFragment();

        customViewPager = (CustomViewPager) findViewById(R.id.viewPager);
        mapFragment.setContext(this);

        // Add fragments to list
        fragmentArrayList.add(logsFragment);
        fragmentArrayList.add(mapFragment);
        fragmentArrayList.add(pointsFragment);
        fragmentArrayList.add(settingsFragment);

        // transfer list to adapter
        adapter.addFragmentArray(fragmentArrayList);

        customViewPager.setAdapter(adapter);
        customViewPager.setCurrentItem(0, false);
        customViewPager.setOffscreenPageLimit(3);

        // Add images & text to a list
        _itemlist.add(new AHBottomNavigationItem("Logs", R.drawable.wood));
        _itemlist.add(new AHBottomNavigationItem("Map", R.drawable.map));
        _itemlist.add(new AHBottomNavigationItem("Point", R.drawable.perm_group_location_normal));
        _itemlist.add(new AHBottomNavigationItem("Settings", R.drawable.settings));

        // force add to toolbar
        bottomNavigation.addItems(_itemlist);

        // configures colors & behaviors of the navbar
        ConfigNavBarSettings();

        pointF.set(bottomNavigation.getWidth(), bottomNavigation.getY());

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                customViewPager.setCurrentItem(position, false);
                return true;
            }
        });


    }

    public void ConfigNavBarSettings(){
        this.bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        this.bottomNavigation.setBehaviorTranslationEnabled(false);
        this.bottomNavigation.setAccentColor(Color.parseColor("#2b3239"));
        this.bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        this.bottomNavigation.setForceTint(false);
        this.bottomNavigation.setForceTitlesDisplay(true);
        this.bottomNavigation.setColored(false);
        this.bottomNavigation.setCurrentItem(0);
    }


    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
