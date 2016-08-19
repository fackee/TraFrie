package com.example.xiaozhu.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaozhu.Bean.Posting;
import com.example.xiaozhu.Model.Net.Url;
import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.Presenter.GetDataFromHttpPresenter;
import com.example.xiaozhu.View.Adapter.PostingAdapter;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.View.intefaces.IReciveData;
import com.example.xiaozhu.trafrie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class PostHotFragment extends Fragment implements IReciveData{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PostHotFragment";
    private String mParam1;
    private String mParam2;
    private List<Posting> postings;
    private Handler mHandler;
    private Context mContext;
    private RecyclerView recyclerView;

    public PostHotFragment(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public static PostHotFragment newInstance(String param1, String param2,Handler mHandler) {
        PostHotFragment fragment = new PostHotFragment(mHandler);
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
        View view = inflater.inflate(R.layout.fragment_post_hot,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        new GetDataFromHttpPresenter(this.getContext(),mHandler,this, Url.POSTING_URL).fetch();
        return view;
    }
    public void showLoading() {
        Toast.makeText(this.getContext(),"加载中...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDatas(JSONArray jsonArray) {
        postings = new ArrayList<>();
        Log.i("jsonarray",jsonArray+"");
        for(int i=0;i<jsonArray.length();i++){
            try {
                ArrayList<String> urls = new ArrayList<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Posting posting = new Posting();
                urls.add("http://192.168.191.1:8080/lvyou/"+(String)jsonObject.get("imgurl"));
                posting.setUsername((String) jsonObject.get("username"));
                posting.setContent((String) jsonObject.get("content"));
                posting.setPraiseNum(jsonObject.getString("praise"));
                posting.setReplyNum(jsonObject.getString("replynum"));
                posting.setPraiseNum(jsonObject.getString("praise"));
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
                String txurl = jsonObject.getString("txurl");
                Log.i(TAG,txurl.replace("\"",""));
                posting.setTxUrl(txurl);
                postings.add(posting);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new PostingAdapter(getContext(),postings));
            }
        });
    }
}
