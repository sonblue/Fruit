package com.example.fruit.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fruit.R;
import com.example.fruit.models.database.SanPhamModels;
import com.example.fruit.views.fragments.HomeFragment;

import io.realm.Realm;

/**
 * Created by Son on 03/11/2016.
 */

public class ChiTietSanPham extends AppCompatActivity {
    private Realm realm;
    SanPhamModels sanPhamModels;
    TextView txTenSP, txXuatXu, txGiaBan, txKhoiLuong, txThongTin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        setContentView(R.layout.chi_tiet_san_pham);
        AnhXa();
        getDataFromHomeFragment();
        setData();
    }
    private void AnhXa(){
        txTenSP = (TextView)findViewById(R.id.txtTenSanPham);
        txXuatXu = (TextView)findViewById(R.id.txtXuatXu);
        txGiaBan = (TextView)findViewById(R.id.txtGiaBan);
        txKhoiLuong = (TextView)findViewById(R.id.txtKhoiLuong);
        txThongTin = (TextView)findViewById(R.id.txtThongTin);
    }


    public void getDataFromHomeFragment(){
        int sanphamID = getIntent().getIntExtra("SanPhamID",-1);
        sanPhamModels = HomeFragment.getInstance().searchPerson(sanphamID);
    }

    public void setData(){
        txTenSP.setText(sanPhamModels.getTenSanPham());
        txXuatXu.setText(sanPhamModels.getXuatXu());
        txGiaBan.setText(sanPhamModels.getGiaBan());
        txKhoiLuong.setText(sanPhamModels.getKhoiLuong());
        txThongTin.setText(sanPhamModels.getThongTin());
    }


}
