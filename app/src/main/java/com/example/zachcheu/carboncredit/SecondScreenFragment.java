package com.example.zachcheu.carboncredit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SecondScreenFragment extends Fragment{
    SharedPreferences pref;
    public static final String ARG_OBJECT = "object";
    LineChart CarbonChart;
    ArrayList<Integer> list;
    Set<String> set;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.screen2, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        int index = args.getInt(ARG_OBJECT);


        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        CarbonChart = (LineChart) getActivity().findViewById(R.id.carbonchart);
        CarbonChart.getLayoutParams().height=size.y/3;

        //initialize chart and data within chart
        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 0;i<list.size();i++){
            //entries.add(new Entry(i,list.get(i).getCarbonCredit()));
            entries.add(new Entry(i,list.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries,"carbon");
        LineData lineData = new LineData(dataSet);
        CarbonChart.setData(lineData);
        CarbonChart.setLogEnabled(true);
        CarbonChart.moveViewToX(0f);

        //description
        CarbonChart.setDescription("");
        CarbonChart.setNoDataText("Start New Drive to Collect Data");

        //enable touch gestures
        CarbonChart.setTouchEnabled(true);
        CarbonChart.setDragEnabled(true);
        CarbonChart.setScaleEnabled(true);

        CarbonChart.setBackgroundColor(Color.LTGRAY);

        //axis options
        XAxis x = CarbonChart.getXAxis();
        YAxis y = CarbonChart.getAxisLeft();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setAxisMinValue(0);
        x.setLabelCount(9);
        x.setAxisMaxValue(9);//add values for unique increasing data
        x.setTextColor(Color.WHITE);
        x.setAvoidFirstLastClipping(true);
        y.setDrawGridLines(false);
        y.setTextColor(Color.WHITE);

        CarbonChart.getAxisRight().setEnabled(false);
        CarbonChart.invalidate();
    }
    @Override
    public void onResume() {
        list.add(pref.getInt("data", 0));
        super.onResume();
    }
}