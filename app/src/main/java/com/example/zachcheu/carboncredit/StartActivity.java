package com.example.zachcheu.carboncredit;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by ZachCheu on 10/17/16.
 */
public class StartActivity extends AppCompatActivity{
    ViewPager defaultViewPager;
    private CollectionPagerAdapter collectionPageAdapter;
    Integer[] colors = {Color.parseColor("#000000"), Color.parseColor("#ecf0f1")};
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        collectionPageAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
        defaultViewPager = (ViewPager) findViewById(R.id.viewpager_default);
        defaultViewPager.setOffscreenPageLimit(2);

        final CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.view);
        defaultViewPager.setAdapter(collectionPageAdapter);
        defaultIndicator.setViewPager(defaultViewPager);

        defaultIndicator.setOnPageChangeListener(new CustomOnPageListener());

        /*
         * Gradual color shift from page to page test!
         */


    }
    private class CustomOnPageListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position < (collectionPageAdapter.getCount() -1) && position < (colors.length - 1)) {

                defaultViewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));

            } else {

                // the last page color
                defaultViewPager.setBackgroundColor(colors[colors.length - 1]);

            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
