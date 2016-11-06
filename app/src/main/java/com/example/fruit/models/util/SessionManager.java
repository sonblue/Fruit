package com.example.fruit.models.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Son on 01/11/2016.
 */

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final String PREF_NAME = "Fruit";
    private static final String KEY_USERNAME = "username";

    private static final String KEY_ISLOGGEDIN = "isLoggedIn";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPreferences.edit();
    }

    public void saveLoginCredenetials(String username){
        editor.putString(KEY_USERNAME,username);
        editor.putBoolean(KEY_ISLOGGEDIN,true);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_ISLOGGEDIN,false);
    }

    public void logout(){
        editor.putBoolean(KEY_ISLOGGEDIN,false);
        editor.commit();
    }
}
