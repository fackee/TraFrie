package com.example.xiaozhu.Model.Interfaces;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.Model.Net.OkHttpUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/24.
 */
public class AchSendDataToHttp implements SendDataToHttp{
    private Context mContext;
    private Handler mHandler;
    private String urlString;
    public AchSendDataToHttp(Context mContext,Handler mHandler ,String urlString){
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.urlString = urlString;
    }
    @Override
    public void sendData(Map<String, String> map, File[] files,String[] formName, final SendDataListner listner) {
        OkHttpClientUtils.postMapAndFilePairAsycn(mContext,urlString,map, files, formName, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("FAIL","FAIL");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i("SUCCESS","SUCCESS");
            }
        });
    }
}
