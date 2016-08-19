package com.example.xiaozhu.trafrie;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by xiaozhu on 2016/7/20.
 */
public class MyApplication extends Application{
    private static Context context;
    private static MyApplication application;
    public static MyApplication getInstance(){
        return application;
    }
    @Override
    public void onCreate(){
        context = getApplicationContext();
        application = this;
        initOkHttpUtils();
    }

    private void initOkHttpUtils() {
        OkHttpClient okHttpClient = OkHttpClientUtils.getOkHttpSingleOnInstance();
    }

    public static Context getContext(){
        return context;
    }
}
