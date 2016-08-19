package com.example.xiaozhu.View.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.xiaozhu.Bean.Posting;
import com.example.xiaozhu.Model.Net.Url;
import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.Presenter.GetDataFromHttpPresenter;
import com.example.xiaozhu.View.Adapter.PostingAdapter;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.View.intefaces.IReciveData;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostingActivity extends BaseActivity implements IReciveData ,View.OnClickListener{
    private List<Posting> postings;
    private Handler mHandler  = new Handler();
    private Context mContext = this;
    private String title;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_posting);
         Intent intent = getIntent();
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        Log.i("Title",intent.getStringExtra("Title"));
        collapsingToolbar.setTitle(intent.getStringExtra("Title"));
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(R.menu.tool_menu);
        recyclerView = (RecyclerView) findViewById(R.id.cate_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        new GetDataFromHttpPresenter(mContext,mHandler,this, Url.POSTING_URL).fetch();
        floatingActionButton = (FloatingActionButton)findViewById(R.id.pfb);
        floatingActionButton.setOnClickListener(this);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == 0){
                   floatingActionButton.setVisibility(View.VISIBLE);
                }else{
                    floatingActionButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        Toast.makeText(mContext,"加载中...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDatas(JSONArray jsonArray) {
        postings = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                ArrayList<String> urls = new ArrayList<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Posting posting = new Posting();
                urls.add("http://192.168.191.1:8080/lvyou/"+(String)jsonObject.get("imgurl"));
                posting.setUsername((String) jsonObject.get("username"));
                posting.setContent((String) jsonObject.get("content"));
                posting.setImgurl(urls);
                String time = (String)jsonObject.get("ptime");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String temp = format.format(new Date());
                if(time.substring(0,10).equals(temp)){
                    time = time.substring(10,16);
                    posting.setTime(time);
                }else{
                    posting.setTime((String)jsonObject.get("ptime"));
                }
                posting.setTxUrl((String)jsonObject.get("txurl"));
                postings.add(posting);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(new PostingAdapter(mContext,postings));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.pfb:
                MySharedreference sharedreference = new MySharedreference(this);
                if(sharedreference.IfOrNotLogin){
                    Intent it = new Intent(this,SendPostActivity.class);
                    startActivity(it);
                    finish();
                }else{
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(this,LoginActivity.class);
                    startActivity(it);
                    finish();
                }
                break;
        }
    }
}
