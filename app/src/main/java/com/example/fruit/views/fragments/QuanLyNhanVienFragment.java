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
import com.example.fruit.models.adapters.ListUserAdapter;
import com.example.fruit.models.database.UserModels;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanLyNhanVienFragment extends Fragment {

    private Realm realm;
    private List<UserModels> listUser;
    ListView listView;

    public QuanLyNhanVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quan_ly_nhan_vien, container, false);
        realm = Realm.getDefaultInstance();
        listView = (ListView)view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataUser();
    }

    private void loadDataUser(){
        listUser = new ArrayList<>();
        listUser.clear();
        final RealmResults<UserModels> realmResults = realm.where(UserModels.class).findAll();
        for (int i = 0; i < realmResults.size(); i++){
            listUser.add(realmResults.get(i));
        }

        ListUserAdapter listUserAdapter  = new ListUserAdapter(getContext(), listUser);
        listView.setAdapter(listUserAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteUser(position,realmResults);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateDialogUser(listUser.get(position));
            }
        });

    }

    private void deleteUser(final int pos, final RealmResults<UserModels> results){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Xóa Sản Phẩm")
                .setMessage("Bạn có muốn xóa sản phẩm không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.beginTransaction();
                        results.deleteFromRealm(pos);
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

    private void updateDialogUser(final UserModels users){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_update_user, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final EditText fullname = (EditText)view.findViewById(R.id.edtFullname);
        fullname.setText(users.getFullname());
        final EditText username = (EditText)view.findViewById(R.id.edtUsername);
        username.setText(users.getUsername());
        final EditText address = (EditText)view.findViewById(R.id.edtAddress);
        address.setText(users.getAddress());
        final EditText email = (EditText)view.findViewById(R.id.edtEmail);
        email.setText(users.getEmail());
        final EditText pass = (EditText)view.findViewById(R.id.edtPass);

        builder.setCancelable(true).setTitle("Chỉnh sửa thông tin")
                .setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (fullname.getText().length() == 0 || username.getText().length()==0||
                                address.getText().length() == 0|| email.getText().length()==0){
                            Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                        } else {
                            updateData(users.getId(), fullname.getText().toString(), username.getText().toString(),
                                    address.getText().toString(), email.getText().toString(), pass.getText().toString());
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

//    private boolean isUsernameExist(String username){
//        RealmResults listUsername = realm
//                .where(UserModels.class)
//                .equalTo("userName",username).findAll();
//        return listUsername.size() > 1;
//    }

    private void updateData(int id, String fullname, String username, String address, String email, String pass){
        UserModels user = realm.where(UserModels.class).equalTo("id",id).findFirst();
        realm.beginTransaction();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(pass);
        realm.commitTransaction();
        onResume();
    }

}
