package com.example.xiaozhu.Model.Interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/24.
 */
public interface SendDataToHttp {
    public void sendData(Map<String,String> map , File[] files,String[] formName,SendDataListner listner);

    public interface SendDataListner{
        public void onComplete();
    }
}
