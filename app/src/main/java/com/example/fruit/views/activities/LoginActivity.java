package com.example.fruit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.database.UserModels;
import com.example.fruit.models.util.SessionManager;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Son on 01/11/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private Realm realm;
    EditText edtUser, edtPass;
    TextView txtLinkRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        realm = Realm.getDefaultInstance();

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        txtLinkRegister = (TextView) findViewById(R.id.link_Register);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.getText().length() == 0 || edtPass.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "mời nhập user && pass", Toast.LENGTH_SHORT).show();
                } else {
                    validateLogin(edtUser.getText().toString(), edtPass.getText().toString());
                }
            }
        });

        txtLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), RegisterActivity.class));
            }
        });
    }

    private void validateLogin(String user, String pass) {
        RealmResults realmResults = realm.where(UserModels.class).equalTo("Username", user).findAll();
        if (realmResults.size() > 0) {
            RealmResults listPass = realm.where(UserModels.class).equalTo("Username", user).equalTo("Password", pass).findAll();
            if (listPass.size() > 0) {
                SessionManager sessionManager = new SessionManager(LoginActivity.this);
                sessionManager.saveLoginCredenetials(user);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                LoginActivity.this.finish();
            } else {
                Toast.makeText(this, "username && pass không đúng", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "username không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
