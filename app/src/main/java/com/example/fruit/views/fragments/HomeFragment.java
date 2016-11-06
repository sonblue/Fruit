package com.example.fruit.views.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.adapters.ListSanPhamAdapter;
import com.example.fruit.models.database.GioHangModels;
import com.example.fruit.models.database.SanPhamModels;
import com.example.fruit.views.activities.ThemSanPham;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static HomeFragment instance;
    private List<SanPhamModels> sanPhamsList;
    private Realm realm;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        realm  = Realm.getDefaultInstance();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.btnThem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThemSanPham.class));
            }
        });

        instance = this;

        return view;
    }
    public static HomeFragment getInstance(){
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData(){
        sanPhamsList = new ArrayList<>();
        sanPhamsList.clear();
        final RealmResults<SanPhamModels> realmResults = realm.where(SanPhamModels.class).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            sanPhamsList.add(realmResults.get(i));
        }

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ListSanPhamAdapter testAdapter = new ListSanPhamAdapter(getContext(), sanPhamsList);
        recyclerView.setAdapter(testAdapter);


    }

    public SanPhamModels searchPerson(int personId) {
        RealmResults<SanPhamModels> results = realm.where(SanPhamModels.class).equalTo("id", personId).findAll();

        realm.beginTransaction();
        realm.commitTransaction();

        return results.get(0);
    }


    public void addToCart(final SanPhamModels models) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_item_gio_hang, null, false);
        final TextView txtTenSP = (TextView) view.findViewById(R.id.txtTenSanPham);
        final TextView txtGiaBan = (TextView) view.findViewById(R.id.txtGiaBan);
        txtTenSP.setText(models.getTenSanPham());
        txtGiaBan.setText(models.getGiaBan());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setCancelable(true).setTitle("Thêm Vào Giỏ Hàng")
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        realm.beginTransaction();
                        GioHangModels gioHangModels = realm.createObject(GioHangModels.class);
                        gioHangModels.setTenSanPham(txtTenSP.getText().toString());
                        gioHangModels.setGiaBan(txtGiaBan.getText().toString());
                        gioHangModels.setSoLuong("1");
                        realm.commitTransaction();
                        Toast.makeText(getContext(), "Thành Công", Toast.LENGTH_SHORT).show();


                    }
                }).setNegativeButton("Bỏ Qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog updataDialog = builder.create();
        updataDialog.show();

    }
}
