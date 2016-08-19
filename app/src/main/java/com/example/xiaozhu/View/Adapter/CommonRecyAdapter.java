package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/22.
 */
public  abstract class CommonRecyAdapter<T> extends RecyclerView.Adapter<CommonRecyViewHolder>{
    private Context mContext;
    protected List<T> mDatas;
    private LayoutInflater mInflater;
    private int layoutId;
    public CommonRecyAdapter(Context mContext, List<T> mDatas, int layoutId){
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.layoutId = layoutId;;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public CommonRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutId,parent,false);
        return getViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(CommonRecyViewHolder holder, int position) {
        showViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    protected abstract CommonRecyViewHolder getViewHolder(View view, int viewType);
    protected abstract  void showViewHolder(CommonRecyViewHolder viewHolder, int position);
}
