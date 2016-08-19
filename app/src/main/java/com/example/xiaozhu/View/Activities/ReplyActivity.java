package com.example.xiaozhu.View.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.View.Adapter.PostImageAdapter;
import com.example.xiaozhu.View.Adapter.ReplyVPagerAdapter;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.View.CustomView.NoScrolGridView;
import com.example.xiaozhu.View.Fragments.ReplyFragment;
import com.example.xiaozhu.View.Fragments.StrategyFragment;
import com.example.xiaozhu.View.Fragments.TopicFragment;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ReplyActivity extends BaseActivity implements View.OnClickListener,Toolbar.OnMenuItemClickListener{
    private Toolbar mToolbar;
    private CircleImageView mCircleImageView;
    private TextView name,time,content,praise;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private EditText mEditText;
    private List<Fragment> fragments;
    private String[] titles;
    private Activity activity = this;
    private Context mContext = this;
    private Handler mHandler = new Handler();
    private List<Map<String,String>> replyData;
    private int replyNum;
    private NoScrolGridView gridView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        initData();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        fragments = new ArrayList<>();
        fragments.add(new ReplyFragment(replyData));
        fragments.add(new ReplyFragment(replyData));
        titles = new String[]{"评论 "+intent.getStringExtra("replynum"),"转发 20"};
        mToolbar = (Toolbar)findViewById(R.id.reply_toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.setTitle("评论列表");
        mToolbar.inflateMenu(R.menu.reply_menu);
        mToolbar.setOnMenuItemClickListener(this);
        mCircleImageView = (CircleImageView)findViewById(R.id.reply_tx);
        Picasso.with(this).load(intent.getStringExtra("txUrl")).into(mCircleImageView);
        name = (TextView)findViewById(R.id.reply_name);
        name.setText(intent.getStringExtra("username"));
        time = (TextView)findViewById(R.id.reply_time);
        time.setText(intent.getStringExtra("time"));
        content = (TextView)findViewById(R.id.reply_content);
        content.setText(intent.getStringExtra("content"));
        praise = (TextView)findViewById(R.id.praise_num);
        praise.setText(intent.getStringExtra("praisenum"));
        mTabLayout = (TabLayout)findViewById(R.id.reply_tab);
        mViewPager = (ViewPager)findViewById(R.id.reply_viewpager);
        mViewPager.setAdapter(new ReplyVPagerAdapter(getSupportFragmentManager(),fragments,titles));
        mTabLayout.setupWithViewPager(mViewPager);
        gridView = (NoScrolGridView)findViewById(R.id.pic_list);
        gridView.setAdapter(new PostImageAdapter(intent.getStringArrayListExtra("imgUrl"),this));
    }

    private void initData() {
        replyData = new ArrayList<>();
        Intent i = getIntent();
        String name = i.getStringExtra("username");
        String time = i.getStringExtra("time");
        Map<String,String> map = new HashMap<>();
        map.put("poster",name);
        map.put("ptime",time);
        OkHttpClientUtils.postMapAndFilePairAsycn(this, "http://192.168.191.1:8080/lvyou/reply.php", map, null, null, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                try {
                    JSONArray array = new JSONArray(response.body().string());
                    replyNum = array.length();
                    Log.i("number",replyNum+"");
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        Map<String,String> mp = new HashMap<String, String>();
                        mp.put("replyer",jsonObject.getString("replyer"));
                        mp.put("content",jsonObject.getString("content"));
                        mp.put("rtime",jsonObject.getString("rtime"));
                        mp.put("txurl",jsonObject.getString("rtxurl").replace("\"",""));
                        replyData.add(mp);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        replyNum = replyData.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.reply:
                Intent intent = new Intent(this,WriteReplyActivity.class);
                Intent it = getIntent();
                intent.putExtra("username",it.getStringExtra("username"));
                intent.putExtra("time",it.getStringExtra("time"));
                startActivity(intent);
                break;
            case R.id.chuanfa:
                Intent itt = new Intent(this,ForwardedActivity.class);
                itt.putExtra("username",name.getText().toString());
                itt.putExtra("content",content.getText().toString());
                startActivity(itt);
                break;
        }
        return true;
    }
}
