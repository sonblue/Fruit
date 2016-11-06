package com.example.fruit.models.database;

import io.realm.RealmObject;

/**
 * Created by Son on 02/11/2016.
 */

public class GioHangModels extends RealmObject  {
    private int id;
    private String TenSanPham;
    private String GiaBan;
    private String SoLuong;
    private String Total;


    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }


}
