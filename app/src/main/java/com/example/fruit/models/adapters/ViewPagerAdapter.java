package com.example.fruit.models.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fruit.views.fragments.NhapHangFragment;
import com.example.fruit.views.fragments.XuatHangFragment;

/**
 * Created by Son on 31/10/2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public ViewPagerAdapter (FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NhapHangFragment nhapHangFragment = new NhapHangFragment();
                return  nhapHangFragment;
            case 1:
                XuatHangFragment xuatHangFragment = new XuatHangFragment();
                return xuatHangFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
