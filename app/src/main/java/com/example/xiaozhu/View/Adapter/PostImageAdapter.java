package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/20.
 */
public class PostImageAdapter extends CommonGridAdapter<String>{
    private List<String> strings;
    private Context mContext;
    private int layouyId;
    public PostImageAdapter(List strings, Context mContext) {
        super(strings,mContext,R.layout.comu_pic);
        this.strings = strings;
        this.mContext = mContext;

    }

    @Override
    public void initView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = new MyViewHolder(mContext,position,convertView,parent);
        holder.setImageRec((ImageView) holder.getView(R.id.com_pic),strings.get(position));
    }
    class MyViewHolder extends CommonGridViewHolder{
        public MyViewHolder(Context mContext, int position, View itemView, ViewGroup parent) {
            super(mContext, position, itemView, parent);
        }
    }
}
