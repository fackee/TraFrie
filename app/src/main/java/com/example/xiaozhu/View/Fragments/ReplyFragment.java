package com.example.xiaozhu.View.Fragments;


import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.Bean.Reply;
import com.example.xiaozhu.View.Adapter.RecruitAdapter;
import com.example.xiaozhu.View.Adapter.ReplyRecAdapter;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<Reply> replies;
    private List<Map<String,String>> Datas;
    public ReplyFragment(List<Map<String,String>> Datas) {
        this.Datas = Datas;
    }

    public static ReplyFragment newInstance(String param1, String param2,List<Map<String,String>> Datas) {
        ReplyFragment fragment = new ReplyFragment(Datas);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initData() {
        replies = new ArrayList<>();
        for(int i=0;i<Datas.size();i++){
            Reply reply = new Reply();
            reply.setName(Datas.get(i).get("replyer"));
            reply.setContent(Datas.get(i).get("content"));
            reply.setTime(Datas.get(i).get("rtime"));
            reply.setTxUrl(Datas.get(i).get("txurl"));
            replies.add(reply);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reply, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.reply_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(2));
        ReplyRecAdapter adapter = new ReplyRecAdapter(getContext(),replies);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
