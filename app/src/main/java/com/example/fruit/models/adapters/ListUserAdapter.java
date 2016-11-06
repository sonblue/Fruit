package com.example.fruit.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fruit.R;
import com.example.fruit.models.database.UserModels;

import java.util.List;

/**
 * Created by Son on 31/10/2016.
 */

public class ListUserAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<UserModels> userlist;

    public ListUserAdapter(Context context, List<UserModels> userlist) {
        this.context = context;
        this.userlist = userlist;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public Object getItem(int position) {
        return userlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_item_user,null);
        }

        TextView fullName = (TextView) convertView.findViewById(R.id.tvUserFullName);
        TextView userName = (TextView) convertView.findViewById(R.id.tvUserName);

        UserModels user = userlist.get(position);
        fullName.setText(user.getFullname());
        userName.setText(user.getUsername());

        return convertView;
    }
}
