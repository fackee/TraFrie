package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by xiaozhu on 2016/7/25.
 */
public class CommonGridViewHolder {
    private Context mContext;
    private int position;
    private View itemView;
    private ViewGroup parent;
    private SparseArray<View> mViews;
    public CommonGridViewHolder(Context mContext,int position , View itemView ,ViewGroup parent){
        this.mContext = mContext;
        this.position = position;
        this.itemView = itemView;
        this.parent = parent;
        mViews = new SparseArray<>();
    }

    public <T extends View>T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public void setImageRec(ImageView imageView , String string){
        Picasso.with(mContext).load(string).into(imageView);
    }
}
