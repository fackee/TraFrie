package com.example.xiaozhu.Model.Utills;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by xiaozhu on 2016/7/20.
 */
public class  MySharedreference {
    private Context mContext;
    public static Boolean IfOrNotLogin;
    public static String signal;
    public static String username;
    public static String txUrl;
    public static String userid;
    public static String userpsd;
    SharedPreferences preferences;
    public MySharedreference(Context mContext){
        this.mContext = mContext;
        preferences = mContext.getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        IfOrNotLogin = preferences.getBoolean("ifornotlogin",false);
        username = preferences.getString("username",null);
        signal = preferences.getString("signal",null);
        txUrl = preferences.getString("txurl",null);
        userid = preferences.getString("userid",null);
        userpsd = preferences.getString("userpsd",null);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
