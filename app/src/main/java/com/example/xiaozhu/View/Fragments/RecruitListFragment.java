package com.example.xiaozhu.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.Bean.RecruitList;
import com.example.xiaozhu.View.Adapter.RecruitListAdapter;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;

public class RecruitListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private List<RecruitList> recruitLists;
    public RecruitListFragment() {

    }

    public static RecruitListFragment newInstance(String param1, String param2) {
        RecruitListFragment fragment = new RecruitListFragment();
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
        recruitLists = new ArrayList<>();
        for(int i=0;i<10;i++){
            RecruitList r = new RecruitList();
            r.setTxUrl("http://192.168.191.1:8080/lvyou/img/tx2.jpg");
            r.setSexUrl("http://192.168.191.1:8080/lvyou/img/male.png");
            r.setName("阿西吧");
            r.setContent("啊楼楼楼楼楼楼。。。");
            recruitLists.add(r);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recruitlist, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recruit_list_rec);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecruitListAdapter(getContext(),recruitLists));
        return view;
    }

}
