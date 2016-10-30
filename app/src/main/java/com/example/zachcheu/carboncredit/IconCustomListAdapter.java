package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by ZachCheu on 10/28/16.
 */

public class IconCustomListAdapter extends BaseAdapter {
    private Activity activity;

    public Integer[] iconImage = {
        R.drawable.rocketicon, R.drawable.batman, R.drawable.cloud
    };
    public IconCustomListAdapter(Activity activity){
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return iconImage.length;
    }

    @Override
    public Object getItem(int i) {
        return iconImage[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(iconImage[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
}
