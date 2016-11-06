package com.example.fruit.views.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fruit.R;
import com.example.fruit.models.adapters.ListGioHangAdapter;
import com.example.fruit.models.database.GioHangModels;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangFragment extends Fragment {
    GioHangModels giohang;
    private ListView listView;
    private List<GioHangModels> gioHangModelses;
    private Realm realm;
    Button btnThanhToan;
    TextView txtTotal;
    public GioHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        realm = Realm.getDefaultInstance();
        listView = (ListView)view.findViewById(R.id.listView);
        txtTotal = (TextView)view.findViewById(R.id.txtTotal);
        btnThanhToan = (Button)view.findViewById(R.id.btnThanhToan);

//        txtTotal.setText(giohang.getSoLuong());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        gioHangModelses = new ArrayList<>();
        gioHangModelses.clear();

        final RealmResults<GioHangModels> realmResults = realm.where(GioHangModels.class).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            gioHangModelses.add(realmResults.get(i));
        }
        ListGioHangAdapter listGioHangAdapter = new ListGioHangAdapter(getContext(), gioHangModelses);
        listView.setAdapter(listGioHangAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteUser(position, realmResults);
            }
        });
    }

    private void deleteUser(final int pos, final RealmResults<GioHangModels> results){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Xóa Giỏ Hàng")
                .setMessage("Bạn có muốn xóa sản phẩm không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.beginTransaction();
                        results.deleteFromRealm(pos);
                        realm.commitTransaction();
                        onResume();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .show();
    }

    private void getData(){


    }

}
