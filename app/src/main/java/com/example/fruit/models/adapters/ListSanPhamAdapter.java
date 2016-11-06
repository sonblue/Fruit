package com.example.fruit.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.database.SanPhamModels;
import com.example.fruit.views.activities.ChiTietSanPham;
import com.example.fruit.views.fragments.HomeFragment;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Son on 05/11/2016.
 */

public class ListSanPhamAdapter extends RecyclerView.Adapter<ListSanPhamAdapter.SanPhamViewHolder> {

    private List<SanPhamModels> sanPhamModel;
    private Context context;
    private Realm realm;
    public ListSanPhamAdapter(Context context, List<SanPhamModels> sanPhamModel) {
        this.context = context;
        this.sanPhamModel = sanPhamModel;
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_san_pham, null, false);
        return new SanPhamViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final SanPhamViewHolder holder, final int position) {

        holder.TenSanPham.setText(sanPhamModel.get(position).getTenSanPham());
        holder.XuatXu.setText(sanPhamModel.get(position).getXuatXu());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("SanPhamID", sanPhamModel.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.imgAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu(holder.imageView);
                SanPhamModels sanpham  = HomeFragment.getInstance().searchPerson(sanPhamModel.get(position).getId());
                HomeFragment.getInstance().addToCart(sanpham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.sanPhamModel.size();
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public TextView TenSanPham;
        private TextView GiaBan;
        private TextView XuatXu;
        private ImageButton imgAddToCart;

        private View container;

        public SanPhamViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgAnh);

            TenSanPham = (TextView)itemView.findViewById(R.id.txtTenSanPham);
            GiaBan = (TextView)itemView.findViewById(R.id.txtGiaBan);
            XuatXu = (TextView)itemView.findViewById(R.id.txtXuatXu);
            container  = itemView.findViewById(R.id.card_view);
            imgAddToCart = (ImageButton)itemView.findViewById(R.id.imgAddToCart);
        }
    }

    private void showPopMenu(View view){
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater  =  popupMenu.getMenuInflater();
        inflater.inflate(R.menu.add_to_cart, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popupMenu.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_add_to_cart:
                    Toast.makeText(context, "Đã Thêm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();

                    return true;
                default:
            }
            return false;
        }
    }
}
