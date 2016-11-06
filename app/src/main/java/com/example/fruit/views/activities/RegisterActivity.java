package com.example.fruit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruit.R;
import com.example.fruit.models.database.UserModels;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Son on 01/11/2016.
 */

public class RegisterActivity extends AppCompatActivity {
    private Realm realm;
    EditText edtUser, edtFullname, edtAddress, edtEmail, edtPass;
    Button btnLogin;
    TextView txtLink_Login;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        realm = Realm.getDefaultInstance();

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtFullname = (EditText) findViewById(R.id.edtFullname);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);

        txtLink_Login = (TextView) findViewById(R.id.link_login);
        txtLink_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnLogin = (Button) findViewById(R.id.btn_Register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.getText().length() == 0 || edtFullname.getText().length() == 0 || edtAddress.getText().length() == 0
                        || edtEmail.getText().length() == 0 || edtPass.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Hãy nhập đây đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    RealmResults<UserModels> realmResults = realm.where(UserModels.class).findAll();
                    if (realmResults.size() > 0) {
                        UserModels users = realmResults.get(realmResults.size() - 1);
                        id = users.getId() + 1;
                        Log.d("User ID: ", String.valueOf(id));
                        register(id);
                    } else {
                        id = 1;
                        Log.d("User ID: ", String.valueOf(id));
                        register(id);
                    }
                }
            }
        });
    }

    private void register(int id){
        realm.beginTransaction();
        UserModels users = realm.createObject(UserModels.class);
        users.setId(id);
        users.setUsername(edtUser.getText().toString());
        users.setFullname(edtFullname.getText().toString());
        users.setAddress(edtAddress.getText().toString());
        users.setEmail(edtEmail.getText().toString());
        users.setPassword(edtPass.getText().toString());
        realm.commitTransaction();
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        RegisterActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
