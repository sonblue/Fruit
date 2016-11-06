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
public class NhapHangFragment extends Fragment {
    Realm realm;
    public NhapHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nhap_hang, container, false);

        realm = Realm.getDefaultInstance();

        LineChart lineChart = (LineChart)view. findViewById(R.id.barChart);



        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(2f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(8f, 5));
        entries.add(new Entry(5f, 6));
        entries.add(new Entry(2f, 7));
        entries.add(new Entry(9f, 8));
        entries.add(new Entry(6f, 9));
        entries.add(new Entry(3f, 10));
        entries.add(new Entry(4f, 11));
        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Tháng 1");
        labels.add("Tháng 2");
        labels.add("Tháng 3");
        labels.add("Tháng 4");
        labels.add("Tháng 5");
        labels.add("Tháng 6");
        labels.add("Tháng 7");
        labels.add("Tháng 8");
        labels.add("Tháng 9");
        labels.add("Tháng 10");
        labels.add("Tháng 11");
        labels.add("Tháng 12");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        return view;
    }




}
