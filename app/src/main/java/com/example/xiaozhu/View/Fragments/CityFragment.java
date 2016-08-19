package com.example.xiaozhu.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.Bean.City;
import com.example.xiaozhu.Bean.CityRsc;
import com.example.xiaozhu.View.Adapter.ParentRecAdapter;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private List<String> category;
    private List<List<City>> cities;
    private RecyclerView mRecyclerView;

    public CityFragment() {
        // Required empty public constructor
    }

    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
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
        initData();
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.parent_rec);
        mRecyclerView.setAdapter(new ParentRecAdapter(this.getContext(),category,cities));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        return view;
    }

    public void initData(){
        CityRsc cityRsc = new CityRsc(getContext());
        category = new ArrayList<>();
        cities = new ArrayList<>();
        for(Map.Entry<Integer,String> entry : cityRsc.province.entrySet()){
            category.add(entry.getValue());
        }
        for(Map.Entry<String,List<String>> entry : cityRsc.municiple.entrySet()){
            List<City> list = new ArrayList<>();
            for(int i=0;i<entry.getValue().size();i++){
                City city = new City();
                city.setPlaceName(entry.getValue().get(i));
                city.setImageSrc(R.drawable.jx);
                list.add(city);
            }
            cities.add(list);
        }
    }
}