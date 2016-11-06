package com.example.fruit.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.database.SanPhamModels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Son on 01/11/2016.
 */

public class ThemSanPham extends AppCompatActivity {
    EditText edtSP, edtGiaBan, edtXuatXu, edtKhoiLuong, edtThongTin;
    TextView txtDateTime;
    Button btnSave;
    ImageButton imgHinhSanPham;
    Calendar time;
    private BottomSheetDialog dialog;
    private Realm realm;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_san_pham);

        ThemSanPham.this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        realm = Realm.getDefaultInstance();

        txtDateTime = (TextView) findViewById(R.id.txtDate);
        edtSP = (EditText) findViewById(R.id.edtTenSanPham);
        edtGiaBan = (EditText) findViewById(R.id.edtGiaBan);
        edtXuatXu = (EditText) findViewById(R.id.edtXuatXu);
        edtKhoiLuong = (EditText) findViewById(R.id.edtKhoiLuong);
        edtThongTin = (EditText) findViewById(R.id.edtThongTin);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSP.getText().length() == 0 || edtXuatXu.getText().length() == 0 ||
                        edtGiaBan.getText().length() == 0 || edtKhoiLuong.getText().length() == 0 ||
                        edtThongTin.getText().length() == 0) {
                    Toast.makeText(ThemSanPham.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    RealmResults<SanPhamModels> realmResults = realm.where(SanPhamModels.class).findAll();
                    if (realmResults.size() > 0) {
                        SanPhamModels sanPhams = realmResults.get(realmResults.size() - 1);
                        id = sanPhams.getId() + 1;
                        Log.d("ID: ", String.valueOf(id));
                        ThemSanPham(id);
                    } else {
                        id = 1;
                        Log.d("ID: ", String.valueOf(id));
                        ThemSanPham(id);
                    }
                }
            }
        });
        dateTime();
    }

    private void dateTime(){
        time = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = null;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String stringDate = simpleDateFormat.format(time.getTime());
        txtDateTime.setText(stringDate);
    }

    private void ThemSanPham(int id) {
        realm.beginTransaction();
        SanPhamModels sanPhams = realm.createObject(SanPhamModels.class);
        sanPhams.setId(id);
        sanPhams.setTenSanPham(edtSP.getText().toString());
        sanPhams.setXuatXu(edtXuatXu.getText().toString());
        sanPhams.setGiaBan(edtGiaBan.getText().toString());
        sanPhams.setKhoiLuong(edtKhoiLuong.getText().toString());
        sanPhams.setThongTin(edtThongTin.getText().toString());
        sanPhams.setDateTime(txtDateTime.getText().toString());
        realm.commitTransaction();
        Toast.makeText(this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
        ThemSanPham.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
