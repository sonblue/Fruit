package com.example.fruit.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fruit.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class XuatHangFragment extends Fragment {

    Realm realm;
    public XuatHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_xuat_hang, container, false);
        realm = Realm.getDefaultInstance();

        LineChart lineChart = (LineChart)view. findViewById(R.id.barChart);


        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(2f, 1));
        entries.add(new Entry(1f, 2));
        entries.add(new Entry(0f, 3));
        entries.add(new Entry(2f, 4));
        entries.add(new Entry(3f, 5));
        entries.add(new Entry(4f, 6));
        entries.add(new Entry(2f, 7));
        entries.add(new Entry(5f, 8));
        entries.add(new Entry(6f, 9));
        entries.add(new Entry(3f, 10));
        entries.add(new Entry(4f, 11));
        LineDataSet dataset = new LineDataSet(entries, "Color");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Thang 1");
        labels.add("Thang 2");
        labels.add("Thang 3");
        labels.add("Thang 4");
        labels.add("Thang 5");
        labels.add("Thang 6");
        labels.add("Thang 7");
        labels.add("Thang 8");
        labels.add("Thang 9");
        labels.add("Thang 10");
        labels.add("Thang 11");
        labels.add("Thang 12");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        return view;
    }

}
