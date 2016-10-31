package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomRankListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Player> PlayerItems;
    AssetManager assetManager;

    public CustomRankListAdapter(Activity activity, List<Player> PlayerItems) {
        this.activity = activity;
        this.PlayerItems = PlayerItems;
        assetManager = activity.getAssets();
    }

    @Override
    public int getCount() {
        return PlayerItems.size();
    }

    @Override
    public Object getItem(int location) {
        return PlayerItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface gothic = Typeface.createFromAsset(assetManager, "fonts/gothic.ttf");
        Typeface arcade = Typeface.createFromAsset(assetManager, "fonts/arcade.ttf");
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.player_row, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.pic);

        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        TextView user = (TextView) convertView.findViewById(R.id.user);
        TextView dist = (TextView) convertView.findViewById(R.id.min);
        TextView date = (TextView) convertView.findViewById(R.id.dist);
        TextView strMile = (TextView) convertView.findViewById(R.id.strMiles);
        rank.setTypeface(arcade);
        user.setTypeface(gothic);
        dist.setTypeface(arcade);
        date.setTypeface(arcade);
        strMile.setTypeface(arcade);


        // getting Log data for the row
        Player m = PlayerItems.get(position);

        // rank
        rank.setText(""+m.getRank());

        image.setImageResource(Var.picId[m.getPicId()]);

        // user
        user.setText(m.getUser());

        // dist
        dist.setText(""+m.getTime()+" drives");

        // release date
        date.setText(""+m.getDist());

        return convertView;
    }

}