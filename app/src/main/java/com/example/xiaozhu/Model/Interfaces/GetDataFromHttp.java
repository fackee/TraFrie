package com.example.xiaozhu.Model.Interfaces;

import com.example.xiaozhu.Bean.Posting;
import com.squareup.okhttp.Callback;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public interface GetDataFromHttp {
    public void LoadData(LoadDataListner listner);
    public interface LoadDataListner{
        void onComplete(JSONArray jsonArray);
    }
}
