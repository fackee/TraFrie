package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.View.CustomView.HorizontalSpaceItemDecoration;
import com.example.xiaozhu.View.CustomView.NoScrolGridView;
import com.example.xiaozhu.View.CustomView.VerticalSpaceItemDecoration;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/22.
 */
public  class CommonRecyViewHolder extends RecyclerView.ViewHolder{
    private Context mContext;
    public View itemView;
    private SparseArray<View> mViews;
    public CommonRecyViewHolder(View itemView,Context mContext) {
        super(itemView);
        this.itemView = itemView;
        this.mContext = mContext;
        mViews = new SparseArray<>();
    }
    public  <T extends View>T  getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public void setText(TextView textView,String string){
        textView.setText(string);
    }
    public void setImageSrc(ImageView imageView , int imageSrc){imageView.setImageResource(imageSrc);}
    public void setGridAdapter(NoScrolGridView gridView, PostImageAdapter adapter){
        gridView.setAdapter(adapter);
    }
    public void setRecAdapter(RecyclerView recAdapter ,SubRecAdapter adapter ){
        recAdapter.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recAdapter.setLayoutManager(linearLayoutManager);
        recAdapter.addItemDecoration(new HorizontalSpaceItemDecoration(10));
    }

    public void InflaterMenu(Toolbar toolbar ,int menuId){
        List<Integer> list = new ArrayList<>();
        if(!list.contains(getPosition())){
            toolbar.inflateMenu(menuId);
            list.add(getPosition());
        }
    }
}
