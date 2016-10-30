package com.example.zachcheu.carboncredit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

public class CollectionPagerAdapter extends FragmentPagerAdapter {


    public ArrayList<Fragment> fragmentArrayList;


    public CollectionPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
        fragmentArrayList = new ArrayList<Fragment>();
    }


    /*
     * Add a list of Fragments to the Array list
     */
    public void addFragmentArray(ArrayList<Fragment> f) {
        for (Fragment frag : f)
            this.fragmentArrayList.add(frag);
    }


    /*
     * Order of getItem():
     *  first fragment == position -> 0
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Bundle args = new Bundle();
        fragment = fragmentArrayList.get(position);
        args.putInt("object", position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }


    @Override
    public int getCount() {
        return this.fragmentArrayList.size();
    }
}