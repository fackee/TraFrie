package com.example.xiaozhu.Presenter;

import android.content.Context;
import android.os.Handler;

import com.example.xiaozhu.Model.Interfaces.AchSendDataToHttp;
import com.example.xiaozhu.Model.Interfaces.SendDataToHttp;
import com.example.xiaozhu.View.intefaces.ISender;

/**
 * Created by xiaozhu on 2016/7/24.
 */
public class SendDataToHttpPresenter {
    ISender iSender;
    SendDataToHttp sendDataToHttp;

    private Context mContext;
    private Handler mHandler;
    private String urlString;
    public SendDataToHttpPresenter(Context mContext,Handler mHandler,String urlString ,ISender iSender){
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.urlString = urlString;
        this.iSender = iSender;
    }

    public void fetch() {
        if (iSender != null) {
            iSender.showDialog();
            sendDataToHttp = new AchSendDataToHttp(mContext,mHandler,urlString);
            sendDataToHttp.sendData(iSender.sendData().getMap(),iSender.sendData().getFiles(),iSender.sendData().getFormName(), new SendDataToHttp.SendDataListner() {
                @Override
                public void onComplete() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            iSender.Success();
                        }
                    });
                }
            });
        }
    }
}
