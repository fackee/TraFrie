package com.example.xiaozhu.View.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.Bean.StartegyV;
import com.example.xiaozhu.Bean.StrategyH;
import com.example.xiaozhu.View.Adapter.StraViewPagerAdapter;
import com.example.xiaozhu.View.Adapter.StrategyHrztAdapter;
import com.example.xiaozhu.View.Adapter.StrategyVtcAdapter;
import com.example.xiaozhu.View.CustomView.HorizontalSpaceItemDecoration;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;


public class StrategyFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private RecyclerView rec_vertical;
    private List<StartegyV> startegyVs;
    private List<Fragment> fragments;
    private ViewPager mViewPager;
    private View zero,one,two,three,four;
    public StrategyFragment() {

    }


    public static StrategyFragment newInstance(String param1, String param2) {
        StrategyFragment fragment = new StrategyFragment();
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
        startegyVs = new ArrayList<>();
        fragments = new ArrayList<>();
        for(int j=0;j<8;j++){
            StartegyV sv = new StartegyV();
            sv.setImgSrc("http://192.168.191.1:8080/lvyou/img/bg02.jpg");
            sv.setDesc("老城、老味、老潮汕");
            sv.setName("旧情不忘");
            sv.setContent("可是人们却慢慢淡忘了这个最们却慢慢淡忘了这个早开放的城市之.");
            startegyVs.add(sv);
        }
        for(int i=0;i<5;i++){
            fragments.add(new StatrgyHeadFragment());
            fragments.add(new StatrgyHeadFragment());
            fragments.add(new StatrgyHeadFragment());
            fragments.add(new StatrgyHeadFragment());
            fragments.add(new StatrgyHeadFragment());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strategy, container, false);
        zero = view.findViewById(R.id.zero);
        zero.setBackgroundColor(Color.WHITE);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        mViewPager = (ViewPager)view.findViewById(R.id.stra_viewpager);
        mViewPager.setAdapter(new StraViewPagerAdapter(getFragmentManager(),fragments));
        rec_vertical = (RecyclerView)view.findViewById(R.id.stra_vtc);
        rec_vertical.setAdapter(new StrategyVtcAdapter(startegyVs,getContext()));
        rec_vertical.setLayoutManager(new LinearLayoutManager(getContext()));
        rec_vertical.addItemDecoration(new VerticalSpaceItemDecoration(15));
        mViewPager.addOnPageChangeListener(this);;
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                zero.setBackgroundColor(Color.WHITE);
                one.setBackgroundColor(Color.GRAY);
                two.setBackgroundColor(Color.GRAY);
                three.setBackgroundColor(Color.GRAY);
                four.setBackgroundColor(Color.GRAY);
                break;
            case 1:
                zero.setBackgroundColor(Color.GRAY);
                one.setBackgroundColor(Color.WHITE);
                two.setBackgroundColor(Color.GRAY);
                three.setBackgroundColor(Color.GRAY);
                four.setBackgroundColor(Color.GRAY);
                break;
            case 2:
                zero.setBackgroundColor(Color.GRAY);
                one.setBackgroundColor(Color.GRAY);
                two.setBackgroundColor(Color.WHITE);
                three.setBackgroundColor(Color.GRAY);
                four.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                zero.setBackgroundColor(Color.GRAY);
                one.setBackgroundColor(Color.GRAY);
                two.setBackgroundColor(Color.GRAY);
                three.setBackgroundColor(Color.WHITE);
                four.setBackgroundColor(Color.GRAY);
                break;
            case 4:
                zero.setBackgroundColor(Color.GRAY);
                one.setBackgroundColor(Color.GRAY);
                two.setBackgroundColor(Color.GRAY);
                three.setBackgroundColor(Color.GRAY);
                four.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
