package com.example.zachcheu.carboncredit;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SecondScreenFragment extends Fragment{
    public static final String ARG_OBJECT = "object";
    LineChart CarbonChart;
    LineDataSet dataSet;
    LineData lineData;
    File f;
    FileReader r;
    BufferedReader b;
    int index;
    ArrayList<String> data = new ArrayList<String>();

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
        f = new File(Environment.getDataDirectory()+File.separator+"file.txt");
        String out = "";
        String k="";
        try {
            r = new FileReader(f);
            b = new BufferedReader(r);
            while((out=b.readLine())!=null){
                data.add(out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 0;i<data.size();i++){
            index = data.get(i).indexOf(",");
            entries.add(new Entry(i,Float.valueOf(data.get(i).substring(index))));
        }
        dataSet = new LineDataSet(entries,"carbon");
        lineData = new LineData(dataSet);
        CarbonChart.setData(lineData);
        super.onResume();
    }
}