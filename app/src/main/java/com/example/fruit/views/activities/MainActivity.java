package com.example.fruit.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fruit.R;
import com.example.fruit.models.util.SessionManager;
import com.example.fruit.views.fragments.GioHangFragment;
import com.example.fruit.views.fragments.QuanLyKhoHangFragment;
import com.example.fruit.views.fragments.QuanLyNhanVienFragment;
import com.example.fruit.views.fragments.HomeFragment;
import com.example.fruit.views.fragments.ThongKeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment)
                .addToBackStack(null).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SessionManager sessionManager = new SessionManager(MainActivity.this);
            sessionManager.logout();
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            MainActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ic_home) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.ic_thongke) {
            ThongKeFragment thongKeFragment = new ThongKeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, thongKeFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.ic_gio_hang) {
            GioHangFragment gioHangFragment = new GioHangFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, gioHangFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.ic_kho_hang) {
            QuanLyKhoHangFragment quanLySanPhamFragment = new QuanLyKhoHangFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, quanLySanPhamFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.ic_nhan_vien) {
            QuanLyNhanVienFragment quanLyNhanVienFragment = new QuanLyNhanVienFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, quanLyNhanVienFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.ic_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Đóng ứng dụng");
            builder.setMessage("Bạn có muốn đóng ứng dụng không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
