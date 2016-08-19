package com.example.xiaozhu.View.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaozhu.Bean.Recruit;
import com.example.xiaozhu.Model.Net.Url;
import com.example.xiaozhu.Presenter.GetDataFromHttpPresenter;
import com.example.xiaozhu.View.Activities.RecruitActivity;
import com.example.xiaozhu.View.Activities.SendRecruitActivity;
import com.example.xiaozhu.View.Activities.TopicActivity;
import com.example.xiaozhu.View.Adapter.RecruitAdapter;
import com.example.xiaozhu.View.intefaces.IReciveData;
import com.example.xiaozhu.trafrie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecruitFragment extends Fragment implements IReciveData,View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    List<Recruit> recruits;
    private Handler mHandler;

    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    public RecruitFragment(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public static RecruitFragment newInstance(String param1, String param2,Handler mHandler) {
        RecruitFragment fragment = new RecruitFragment(mHandler);
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
        View view =  inflater.inflate(R.layout.fragment_recruit, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recruit_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        new GetDataFromHttpPresenter(this.getContext(),mHandler,this, Url.RECRIUT_URL).fetch();
        actionButton = (FloatingActionButton)view.findViewById(R.id.rec_edit);
        actionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"加载中...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDatas(JSONArray jsonArray) {
        recruits = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Recruit recruit = new Recruit();
                recruit.setTitle((String) jsonObject.get("title"));
                recruit.setContent((String) jsonObject.get("content"));
                recruits.add(recruit);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                RecruitAdapter adapter = new RecruitAdapter(getContext(),recruits);
                adapter.setOnItemClickListner(new RecruitAdapter.OnRecyclerItemOnclickListner() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), RecruitActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rec_edit:
                Intent intent = new Intent(this.getActivity(), SendRecruitActivity.class);
                startActivity(intent);
                getActivity().finish();
        }
    }
}
