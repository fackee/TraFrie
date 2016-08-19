package com.example.xiaozhu.View.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.ReplyTopic;
import com.example.xiaozhu.View.Adapter.EveryTopicAdapter;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar head;
    private ImageView headbg;
    private CircleImageView topperson;
    private TextView top_title,top_content;
    private RecyclerView mRecyclerView;
    private List<ReplyTopic> topics;
    private Activity activity = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        initData();
        initView();
    }

    private void initView() {
        Intent it = getIntent();
        head = (Toolbar)findViewById(R.id.topic_head);
        head.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        head.setNavigationOnClickListener(this);
        headbg = (ImageView)findViewById(R.id.headbg);
        Picasso.with(this).load(it.getStringExtra("imgSrc")).into(headbg);
        topperson = (CircleImageView)findViewById(R.id.topperson);
        top_title = (TextView)findViewById(R.id.top_title);
        top_content = (TextView)findViewById(R.id.top_content);
        mRecyclerView = (RecyclerView)findViewById(R.id.topic_content_recycler);
        EveryTopicAdapter adapter = new EveryTopicAdapter(topics,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListner(new EveryTopicAdapter.OnRecyclerItemOnclickListner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("click","click");
                Intent intent = new Intent(activity,ArticalActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });
    }
    private void initData(){
        topics = new ArrayList<>();
        for(int i=0;i<10;i++){
            ReplyTopic t = new ReplyTopic();
            t.setContent("风回复客户开发和万科符合胃口良好氛围看，方维护开发后期额哦合法权利。付全额返回去了");
            t.setName("晓风残月");
            t.setTitle("清风徐来，水波不兴，举酒嘱客。");
            t.setTxSrc("http://192.168.191.1:8080/lvyou/img/tx2.jpg");
            topics.add(t);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  -1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
