package com.example.zachcheu.carboncredit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

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
     * Default viewpager... nothing special
     */
    public ViewPager viewPager;


    /*
     * Holds the various images for the navbar
     */
    public ArrayList<AHBottomNavigationItem> _itemlist;


    /*
     * library used to create navbar
     */
    public AHBottomNavigation bottomNavigation;

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


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.launcher_layout);

        // finish initialization of variables
        adapter = new CollectionPagerAdapter(getSupportFragmentManager());
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        _itemlist = new ArrayList<AHBottomNavigationItem>();
        fragmentArrayList = new ArrayList<Fragment>();
        mapFragment = new MapFragment();
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Add fragments to list
        fragmentArrayList.add(mapFragment);

        // transfer list to adapter
        adapter.addFragmentArray(fragmentArrayList);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        // Add images & text to a list
        _itemlist.add(new AHBottomNavigationItem("Logs", R.drawable.wood));
        _itemlist.add(new AHBottomNavigationItem("Map", R.drawable.map));
        _itemlist.add(new AHBottomNavigationItem("Point", R.drawable.perm_group_location_normal));
        _itemlist.add(new AHBottomNavigationItem("Settings", R.drawable.settings));

        // force add to toolbar
        bottomNavigation.addItems(_itemlist);

        // configures colors & behaviors of the navbar
        ConfigNavBarSettings();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
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
        this.bottomNavigation.setCurrentItem(1);
    }
}
