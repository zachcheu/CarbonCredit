package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Log> LogItems;

    public CustomListAdapter(Activity activity, List<Log> LogItems) {
        this.activity = activity;
        this.LogItems = LogItems;
    }

    @Override
    public int getCount() {
        return LogItems.size();
    }

    @Override
    public Object getItem(int location) {
        return LogItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView credit = (TextView) convertView.findViewById(R.id.credit);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView dist = (TextView) convertView.findViewById(R.id.distance);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        // getting Log data for the row
        Log m = LogItems.get(position);

        // credit
        credit.setText(m.getCredit());

        // time
        time.setText("Drive Time: " + String.valueOf(m.getTime()));

        // dist
        dist.setText("Distance: "+m.getDist());

        // release date
        date.setText(String.valueOf(m.getDate()));

        return convertView;
    }

}