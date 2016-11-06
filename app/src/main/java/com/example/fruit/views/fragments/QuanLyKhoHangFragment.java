package com.example.fruit.views.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.adapters.ListKhoHangAdapter;
import com.example.fruit.models.database.SanPhamModels;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanLyKhoHangFragment extends Fragment {

    private Realm realm;
    private List<SanPhamModels> sanPhamsList;
    ListView listView;

    public QuanLyKhoHangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quan_ly_kho_hang, container, false);
        realm = Realm.getDefaultInstance();
        listView = (ListView)view.findViewById(R.id.listView);

        return view;
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
        for (int i = 0; i < realmResults.size(); i++){
            sanPhamsList.add(realmResults.get(i));
        }
        ListKhoHangAdapter listKhoHangAdapter = new ListKhoHangAdapter(getContext(), sanPhamsList);
        listView.setAdapter(listKhoHangAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateDialogUser(sanPhamsList.get(position));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteSP(position, realmResults);
                return true;
            }
        });
    }

    private void deleteSP(final int pos, final RealmResults<SanPhamModels> result) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Xóa Sản Phẩm")
                .setMessage("Bạn có muốn xóa sản phẩm không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.beginTransaction();
                        result.deleteFromRealm(pos);
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

    private void updateDialogUser(final SanPhamModels sanPhams) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.update_san_pham, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final EditText tenSP = (EditText) view.findViewById(R.id.edtTenSanPham);
        tenSP.setText(sanPhams.getTenSanPham());
        final EditText xuatxu = (EditText) view.findViewById(R.id.edtXuatXu);
        xuatxu.setText(sanPhams.getXuatXu());
        final EditText giaban = (EditText) view.findViewById(R.id.edtGiaBan);
        giaban.setText(sanPhams.getGiaBan());
        final EditText khoiluong = (EditText) view.findViewById(R.id.edtKhoiLuong);
        khoiluong.setText(sanPhams.getKhoiLuong());
        final EditText thongtin = (EditText) view.findViewById(R.id.edtThongTin);
        thongtin.setText(sanPhams.getThongTin());

        builder.setCancelable(true).setTitle("Chỉnh sửa thông tin")
                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (tenSP.getText().length() == 0 || xuatxu.getText().length() == 0 ||
                                giaban.getText().length() == 0 || khoiluong.getText().length() == 0 || thongtin.getText().length() == 0) {
                            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                        } else {
                            updateData(sanPhams.getId(), tenSP.getText().toString(), xuatxu.getText().toString(),
                                    giaban.getText().toString(), khoiluong.getText().toString(), thongtin.getText().toString());
                        }
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

    private void updateData(int id, String tenSP, String xuatxu, String giaban, String khoiluong, String thongtin) {
        SanPhamModels sanPhams = realm.where(SanPhamModels.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        sanPhams.setTenSanPham(tenSP);
        sanPhams.setXuatXu(xuatxu);
        sanPhams.setGiaBan(giaban);
        sanPhams.setKhoiLuong(khoiluong);
        sanPhams.setThongTin(thongtin);
        realm.commitTransaction();
        onResume();
    }

}
