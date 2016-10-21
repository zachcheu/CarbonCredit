package com.example.zachcheu.carboncredit;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class SecondScreenFragment extends Fragment{
    public static final String ARG_OBJECT = "object";
    private LineChart CarbonChart;
    private LineDataSet dataSet;
    private LineData lineData;
    private ListView list;
    private List<Log> log = new ArrayList<Log>();
    private CustomListAdapter adapter;
    private File f;
    private FileReader r;
    private BufferedReader b;
    private ArrayList<Integer> carbonData = new ArrayList<Integer>();
    private ArrayList<Integer> distanceData = new ArrayList<Integer>();
    private ArrayList<Integer> driveTimeData = new ArrayList<Integer>();
    private ArrayList<Integer> timeData = new ArrayList<Integer>();


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
        list = (ListView) getActivity().findViewById(R.id.list);
        CarbonChart.getLayoutParams().height=size.y/3;
        list.getLayoutParams().height=size.y*2/3;
        adapter = new CustomListAdapter(getActivity(),log);
        list.setAdapter(adapter);
        updateList();


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

    private void updateList() {
        carbonData = FileHelper.ReadCarbon(getContext());
        driveTimeData = FileHelper.ReadDriveTime(getContext());
        timeData = FileHelper.ReadTime(getContext());
        distanceData = FileHelper.ReadDistance(getContext());
        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 0;i<carbonData.size();i++){
            entries.add(new Entry(i,carbonData.get(i)));
        }
        dataSet = new LineDataSet(entries,"Carbon Credit");
        lineData = new LineData(dataSet);
        CarbonChart.setData(lineData);
        this.log.clear();
        for(int i = 0; i<carbonData.size();i++){
            Log data = new Log();
            data.setCredit("Carbon Credit: " + carbonData.get(i));
            data.setTime(driveTimeData.get(i));
            data.setDate(timeData.get(i));
            data.setDist(distanceData.get(i));
            this.log.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        updateList();
        super.onResume();
    }
}