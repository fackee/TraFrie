package com.example.xiaozhu.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.Bean.Topic;
import com.example.xiaozhu.View.Activities.TopicActivity;
import com.example.xiaozhu.View.Adapter.TopicAdapter;
import com.example.xiaozhu.View.CustomView.HorizontalSpaceItemDecoration;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;

public class TopicFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private List<Topic> topics;
    public TopicFragment() {

    }

    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        initData();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.topic_rec);
        TopicAdapter topicAdapter = new TopicAdapter(topics,getContext());
        mRecyclerView.setAdapter(topicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        topicAdapter.setOnItemClickListner(new TopicAdapter.OnRecyclerItemOnclickListner() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("POSITION",position+"");
                Intent intent = new Intent(getActivity(), TopicActivity.class);
                intent.putExtra("imgSrc",topics.get(position).getImgSrc());
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
    private void initData(){
        topics = new ArrayList<>();
        for(int i=0;i<7;i++){
            Topic topic = new Topic();
            topic.setConcern("14234 人关注");
            topic.setTitle("寻找中国最美山水");
            topic.setViewnum("14231414 次浏览");
            topic.setImgSrc("http://192.168.191.1:8080/lvyou/img/ss.jpg");
            topics.add(topic);
        }
    }

}
