package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.Topic;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/28.
 */
public  class TopicAdapter extends CommonRecyAdapter<Topic> implements View.OnClickListener{
    private List<Topic> topics;
    private Context mContext;
    private OnRecyclerItemOnclickListner listner = null;
    public TopicAdapter(List<Topic> topics , Context mContext) {
        super(mContext, topics, R.layout.topic_rec_content);
        this.mContext = mContext;
        this.topics = topics;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        view.setOnClickListener(this);
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        viewHolder.setText((TextView) viewHolder.getView(R.id.topic_title),topics.get(position).getTitle());
        viewHolder.setText((TextView) viewHolder.getView(R.id.view_num),topics.get(position).getViewnum());
        viewHolder.setText((TextView) viewHolder.getView(R.id.concern),topics.get(position).getConcern());
        Picasso.with(mContext).load(topics.get(position).getImgSrc()).into((ImageView) viewHolder.getView(R.id.topic_bg));
        viewHolder.itemView.setTag(position);
    }
    public void setOnItemClickListner(OnRecyclerItemOnclickListner listner){
        this.listner = listner;
    }
    @Override
    public void onClick(View v) {
        if(listner != null){
            listner.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static interface OnRecyclerItemOnclickListner{
        void onItemClick(View view,int position);
    }
     class MyViewHolder extends CommonRecyViewHolder {
         public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
