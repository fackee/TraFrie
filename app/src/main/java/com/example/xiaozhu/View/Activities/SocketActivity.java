package com.example.xiaozhu.View.Activities;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xiaozhu.View.utils.RegisterUtil;
import com.example.xiaozhu.trafrie.R;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Registration;

public class SocketActivity extends AppCompatActivity {
    private Context mContext = this;
    private Handler mHandler = new Handler();
    private  ConnectionConfiguration configuration;
    private XMPPConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        configuration = new ConnectionConfiguration("127.0.0.1",5222,"");
        configuration.setSASLAuthenticationEnabled(false);
        connection = new XMPPConnection(configuration);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new RegisterUtil(mContext,connection);
                    connection.connect();
                    connection.login("admin","zjx7532554");
                    Log.i("success","success");
                } catch (XMPPException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
