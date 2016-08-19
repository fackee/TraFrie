package com.example.xiaozhu.Presenter;

import android.content.Context;
import android.os.Handler;

import com.example.xiaozhu.Model.DB.Mydbhelper;
import com.example.xiaozhu.Model.Interfaces.AchGetDataFromHttp;
import com.example.xiaozhu.Model.Interfaces.GetDataFromHttp;
import com.example.xiaozhu.View.Activities.PostingActivity;
import com.example.xiaozhu.View.intefaces.IReciveData;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public  class GetDataFromHttpPresenter {
    IReciveData iReciveData;
    GetDataFromHttp getDataFromHttp;

    private Context mContext;
    private Handler mHandler;
    private String urlString;
    public GetDataFromHttpPresenter(Context mContext, Handler mHandler, IReciveData iReciveData, String urlString){
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.iReciveData = iReciveData;
        this.urlString = urlString;
        getDataFromHttp = new AchGetDataFromHttp(mContext,mHandler,urlString);
    }

    public void fetch(){
        iReciveData.showLoading();
        if(getDataFromHttp!=null){
            getDataFromHttp.LoadData(new GetDataFromHttp.LoadDataListner() {
                @Override
                public void onComplete(JSONArray jsonArray) {
                    iReciveData.showDatas(jsonArray);
                }
            });
        }
    }
}
