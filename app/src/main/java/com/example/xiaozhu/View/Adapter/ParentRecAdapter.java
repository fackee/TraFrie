package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.xiaozhu.Bean.City;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/27.
 */
public class ParentRecAdapter extends CommonRecyAdapter<String>{
    private Context mContext;
    private List<String> category;
    private List<List<City>> cities;
    public ParentRecAdapter(Context mContext,List<String> category, List<List<City>> cities){
        super(mContext,category, R.layout.sub_rec);
        this.mContext = mContext;
        this.category = category;
        this.cities = cities;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        viewHolder.setRecAdapter((RecyclerView)viewHolder.getView(R.id.sub_rec),new SubRecAdapter(mContext,cities.get(position)));
        viewHolder.setText((TextView)viewHolder.getView(R.id.citycategory),category.get(position));
    }
    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
