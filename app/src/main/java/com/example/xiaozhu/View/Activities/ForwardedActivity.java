package com.example.xiaozhu.View.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ForwardedActivity extends BaseActivity implements View.OnClickListener,Toolbar.OnMenuItemClickListener{
    private Toolbar mToolbar;
    private EditText mEditText;
    private CheckBox mCheckBox;
    private TextView mTextView;
    private Context mContext = this;
    private Handler mHandler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forwarded);
        Intent intent = getIntent();
        mToolbar = (Toolbar)findViewById(R.id.for_toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.inflateMenu(R.menu.send_rec_head);
        mToolbar.setOnMenuItemClickListener(this);
        mEditText = (EditText)findViewById(R.id.for_edit);
        mTextView = (TextView)findViewById(R.id.for_content) ;
        mTextView.setHint("@"+intent.getStringExtra("username")+"://"+ Html.fromHtml("<br>")+intent.getStringExtra("content"));
        mCheckBox = (CheckBox)findViewById(R.id.for_check);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ReplyActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send_rec_send:
                Intent intent = getIntent();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String rtime = format.format(new Date());
                Map<String,String> map = new HashMap<>();
                map.put("poster",intent.getStringExtra("username"));
                map.put("ptime",intent.getStringExtra("time"));
                map.put("replyer",new MySharedreference(this).username);
                map.put("content",mEditText.getText().toString());
                Log.i("txurl",new MySharedreference(this).txUrl);
                map.put("rtxurl",new MySharedreference(this).txUrl);
                map.put("rtime",rtime);
                Log.i("data",intent.getStringExtra("username")+intent.getStringExtra("time")+new MySharedreference(this).username+mEditText.getText().toString());
                OkHttpClientUtils.postMapAndFilePairAsycn(this, "http://192.168.191.1:8080/lvyou/upreply.php", map, null, null, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext,"success",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;
        }
        return false;
    }
}
