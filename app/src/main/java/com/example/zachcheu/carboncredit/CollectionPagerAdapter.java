package com.example.zachcheu.carboncredit;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.View;

public class CollectionPagerAdapter extends FragmentPagerAdapter {

    public CollectionPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println(position);
        Fragment fragment;
        Bundle args = new Bundle();
        if(position != 1){
            fragment = new FirstScreenFragment();
            args.putInt(FirstScreenFragment.ARG_OBJECT, position);
        }else{
            fragment = new SecondScreenFragment();
            args.putInt(SecondScreenFragment.ARG_OBJECT, position);
        }
        fragment.setArguments(args);
        return fragment;    }

    //when view is destroyed
    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}