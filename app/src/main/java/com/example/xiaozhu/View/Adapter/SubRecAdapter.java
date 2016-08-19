package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.City;
import com.example.xiaozhu.View.Activities.MainActivity;
import com.example.xiaozhu.View.Activities.PostingActivity;
import com.example.xiaozhu.trafrie.R;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/27.
 */
public class SubRecAdapter extends CommonRecyAdapter<City>{
    private Context mContext;
    private List<City> cities;
    public SubRecAdapter(Context mContext,List<City> cities) {
        super(mContext,cities, R.layout.city_pic);
        this.cities = cities;
        this.mContext = mContext;
    }

    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, final int position) {
        viewHolder.setImageSrc((ImageView) viewHolder.getView(R.id.pic),cities.get(position).getImageSrc());
        viewHolder.getView(R.id.pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostingActivity.class);
                intent.putExtra("Title",cities.get(position).getPlaceName());
                mContext.startActivity(intent);
            }
        });
        viewHolder.setText((TextView)viewHolder.getView(R.id.cityname),cities.get(position).getPlaceName());
    }
    class MyViewHolder extends CommonRecyViewHolder{
        public MyViewHolder(View itemView,Context context) {
            super(itemView ,context);
        }
    }
}
