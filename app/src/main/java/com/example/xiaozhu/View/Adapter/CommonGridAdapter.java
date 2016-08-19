package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/25.
 */
public abstract class CommonGridAdapter<T> extends BaseAdapter{
    private List<T> lists;
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutId;
    public CommonGridAdapter(List<T> lists, Context mContext, int layouyId){
        this.lists = lists;
        this.mContext = mContext;
        this.layoutId = layouyId;
        mInflater = LayoutInflater.from(this.mContext);
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = mInflater.inflate(layoutId,null);
            initView(position,convertView,parent);
        }
        return convertView;
    }
    public abstract void initView(int position, View convertView, ViewGroup parent);
}
