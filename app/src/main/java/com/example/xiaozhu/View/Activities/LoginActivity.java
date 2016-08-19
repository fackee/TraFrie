package com.example.xiaozhu.View.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private final String TAG = "LoginActivity";
    private Toolbar toolbar;
    private EditText username;
    private EditText password;
    private Button login,rigister;
    private Context mContext = this;
    private Activity activity = this;
    public String name;
    public String signal;
    public String txUrl;
    public String userid,psd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(this);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        rigister = (Button)findViewById(R.id.rigster);
        login.setOnClickListener(this);
        rigister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                userid = username.getText().toString();
                psd = password.getText().toString();
                if(TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())){
                    Toast.makeText(this,"账号或者密码不能为空",Toast.LENGTH_LONG).show();
                }else{
                    Map<String,String> map = new HashMap<>();
                    map.put("username",userid);
                    map.put("password",psd);
                    OkHttpClientUtils.postMapAndFilePairAsycn(this,"http://192.168.191.1:8080/lvyou/login.php",map,null,null,new Callback(){
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(mContext,"请检查网络连接",Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onResponse(Response response) throws IOException {
                                try {
                                    JSONObject object = new JSONObject(response.body().string());
                                    name = object.getString("username");
                                    signal = object.getString("signat");
                                    txUrl = object.getString("txurl").replace("\"","");
                                    MySharedreference reference = new MySharedreference(mContext);
                                    reference.getPreferences().edit().putBoolean("ifornotlogin",true).commit();
                                    reference.getPreferences().edit().putString("username",name).commit();
                                    reference.getPreferences().edit().putString("signal",signal).commit();
                                    reference.getPreferences().edit().putString("txurl",txUrl).commit();
                                    reference.getPreferences().edit().putString("userid",userid).commit();
                                    reference.getPreferences().edit().putString("userpsd",psd).commit();
                                    Intent it = new Intent(mContext,MainActivity.class);
                                    startActivity(it);
                                    activity.finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                    });
                }
                break;
            case R.id.rigster:
                break;
        }
    }
}
