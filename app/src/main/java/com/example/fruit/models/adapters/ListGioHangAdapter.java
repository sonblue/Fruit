package com.example.fruit.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fruit.R;
import com.example.fruit.models.database.GioHangModels;

import java.util.List;

/**
 * Created by Son on 02/11/2016.
 */

public class ListGioHangAdapter extends BaseAdapter {
    private Context context;
    private List<GioHangModels> gioHangModelses;
    private LayoutInflater inflater;

    public ListGioHangAdapter (Context context, List<GioHangModels> gioHangModelses){
        this.context = context;
        this.gioHangModelses = gioHangModelses;

    }

    @Override
    public int getCount() {
        return gioHangModelses.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangModelses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item_gio_hang,null);
        }
        TextView txtTenSP = (TextView)convertView.findViewById(R.id.txtTenSanPham);
        TextView txtGiaBan = (TextView)convertView.findViewById(R.id.txtGiaBan);
        TextView txtSoLuong = (TextView)convertView.findViewById(R.id.txtSoLuong);


        GioHangModels gioHang = gioHangModelses.get(position);
        txtTenSP.setText(gioHang.getTenSanPham());
        txtSoLuong.setText(gioHang.getSoLuong());
        txtGiaBan.setText(gioHang.getGiaBan());

        return convertView;
    }
}
