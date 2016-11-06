package com.example.fruit.models.database;

import io.realm.RealmObject;

/**
 * Created by Son on 01/11/2016.
 */

public class SanPhamModels extends RealmObject {

    public int id;
    public String TenSanPham;
    public String XuatXu;
    public String GiaBan;
    public String KhoiLuong;
    public String ThongTin;
    public String DateTime;

    public SanPhamModels() {
    }

    public SanPhamModels(String tenSanPham, String xuatXu, String giaBan) {
        TenSanPham = tenSanPham;
        XuatXu = xuatXu;
        GiaBan = giaBan;
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

    public String getXuatXu() {
        return XuatXu;
    }

    public void setXuatXu(String xuatXu) {
        XuatXu = xuatXu;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }

    public String getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        KhoiLuong = khoiLuong;
    }

    public String getThongTin() {
        return ThongTin;
    }

    public void setThongTin(String thongTin) {
        ThongTin = thongTin;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }
}
