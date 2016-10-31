package com.example.zachcheu.carboncredit;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.mikephil.charting.animation.Easing;
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
    private ArrayList<Long> distanceData = new ArrayList<Long>();
    private ArrayList<Long> driveTimeData = new ArrayList<Long>();
    private ArrayList<Long> timeData = new ArrayList<Long>();
    FloatingActionButton refresh;
    TextView stringLog, xlabel, ylabel;
    Typeface gothic;
    RelativeLayout.LayoutParams params;


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
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.screen2);
        stringLog = (TextView) getActivity().findViewById(R.id.stringLog);
        ylabel = (TextView) getActivity().findViewById(R.id.ystring);
        xlabel = (TextView) getActivity().findViewById(R.id.xstring);
        gothic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        xlabel.setTypeface(gothic);
        ylabel.setTypeface(gothic);
        ylabel.setGravity(Gravity.CENTER);
        xlabel.setGravity(Gravity.CENTER);
        stringLog.setTypeface(gothic);
        stringLog.setTextColor(getResources().getColor(R.color.log_header_color));
        xlabel.setTextColor(Color.WHITE);
        ylabel.setTextColor(Color.WHITE);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        CarbonChart = (LineChart) getActivity().findViewById(R.id.carbonchart);
        list = (ListView) getActivity().findViewById(R.id.list);
        refresh = (FloatingActionButton) getActivity().findViewById(R.id.floating_refresh);
        params = new RelativeLayout.LayoutParams(refresh.getButtonSize(),refresh.getButtonSize());
        params.bottomMargin = (int)list.getY()-(refresh.getButtonSize()/2);
        //rl.(refresh,params);
        CarbonChart.getLayoutParams().height=size.y*5/12;
        list.getLayoutParams().height=size.y*11/20;
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

        CarbonChart.setBackgroundColor(getResources().getColor(R.color.graph_background));

        //axis options
        XAxis x = CarbonChart.getXAxis();
        YAxis y = CarbonChart.getAxisLeft();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setAxisMinValue(2);
        x.setLabelCount(carbonData.size()-1);
        x.setAxisMaxValue(carbonData.size());//add values for unique increasing data
        x.setTextColor(Color.WHITE);
        x.setTypeface(gothic);
        x.setAvoidFirstLastClipping(true);
        y.setDrawGridLines(false);
        y.setAxisMinValue(0);
        y.setTextColor(Color.WHITE);
        y.setTypeface(gothic);

        CarbonChart.getAxisRight().setEnabled(false);
        CarbonChart.invalidate();
        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updateList();
            }
        });
    }

    private void updateList() {
        CarbonChart.animateY(1000, Easing.EasingOption.EaseInOutCirc);
        //CarbonChart.animateXY(3000, 3000, Easing.EasingOption.EaseInCirc, Easing.EasingOption.Linear);
        carbonData = FileHelper.ReadCarbon(getContext());
        driveTimeData = FileHelper.ReadDriveTime(getContext());
        timeData = FileHelper.ReadTime(getContext());
        distanceData = FileHelper.ReadDistance(getContext());
        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 0;i<carbonData.size();i++){
            entries.add(new Entry(i+1,carbonData.get(i)));
        }
        dataSet = new LineDataSet(entries,"");
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTypeface(gothic);
        lineData = new LineData(dataSet);
        CarbonChart.setData(lineData);
        CarbonChart.notifyDataSetChanged();
        CarbonChart.invalidate();
        this.log.clear();
        System.out.println("Arraylist size: " +carbonData.size());
        for(int i = 0; i<carbonData.size();i++){
            Log data = new Log();
            data.setCredit("Carbon Credit: " + carbonData.get(i));
            data.setTime(((driveTimeData.get(i))/1000)/60);
            data.setDate(timeUnit(((System.currentTimeMillis()-timeData.get(i))/1000)));
            data.setDist(distanceData.get(i));
            this.log.add(0,data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        updateList();
        super.onResume();
    }
    public String timeUnit(long min){
        if(min>60){
            return min/60+" minutes ago";
        }else if(min>3600){
            return min/3600+" hours ago";
        }else{
            return min+" seconds ago";
        }
    }
}
