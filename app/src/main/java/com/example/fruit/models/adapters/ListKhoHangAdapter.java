package com.example.fruit.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fruit.R;
import com.example.fruit.models.database.SanPhamModels;

import java.util.List;

/**
 * Created by Son on 31/10/2016.
 */

public class ListKhoHangAdapter extends BaseAdapter {
    private Context context;
    private List<SanPhamModels> sanPhamList;
    private LayoutInflater inflater;

    public ListKhoHangAdapter(Context context, List<SanPhamModels> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanPhamList.get(position);
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
            convertView = inflater.inflate(R.layout.list_item_kho_hang,null);
        }
        TextView txtTenSP = (TextView)convertView.findViewById(R.id.txtTenSanPham);
        TextView txtXuatXu = (TextView)convertView.findViewById(R.id.txtXuatXu);
        TextView txtKhoiLuong = (TextView)convertView.findViewById(R.id.txtKhoiLuong);
        TextView txtDate = (TextView)convertView.findViewById(R.id.txtDateTime);

        SanPhamModels sanPham = sanPhamList.get(position);
        txtTenSP.setText(sanPham.getTenSanPham());
        txtXuatXu.setText(sanPham.getXuatXu());
        txtKhoiLuong.setText(sanPham.getKhoiLuong());
        txtDate.setText(sanPham.getDateTime());
        return convertView;
    }
}
