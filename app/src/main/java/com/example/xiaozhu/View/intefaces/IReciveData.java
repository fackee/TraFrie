package com.example.xiaozhu.View.intefaces;

import com.example.xiaozhu.Bean.Posting;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public interface IReciveData {
    public void showLoading();
    public void showDatas(JSONArray jsonArray);
}
