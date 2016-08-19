package com.example.xiaozhu.Model.Interfaces;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.Model.Net.OkHttpUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public  class AchGetDataFromHttp implements GetDataFromHttp {
    private Context mContext;
    private Handler mHandler;
    private String urlString;
    public AchGetDataFromHttp(Context mContext, Handler mHandler,String urlString){
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.urlString = urlString;
    }
    @Override
    public void LoadData(final LoadDataListner listner) {
        final JSONArray jsonArray = new JSONArray();
        OkHttpClientUtils.getDataAsync(mContext,urlString, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onResponse(Response response) {
                try {
                    JSONArray array = new JSONArray(response.body().string());
                    jsonArray.put(array);
                    listner.onComplete(jsonArray.getJSONArray(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
