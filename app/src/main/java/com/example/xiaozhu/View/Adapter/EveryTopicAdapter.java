package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.ReplyTopic;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/30.
 */
public class EveryTopicAdapter extends CommonRecyAdapter<ReplyTopic> implements View.OnClickListener{
    private List<ReplyTopic> topics;
    private Context mContext;
    private OnRecyclerItemOnclickListner listner = null;
    public EveryTopicAdapter(List<ReplyTopic> topics , Context mContext){
        super(mContext,topics, R.layout.topic_content_rec);
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
        Picasso.with(mContext).load(topics.get(position).getTxSrc()).into((ImageView) viewHolder.getView(R.id.topic_content_rec_tx));
        viewHolder.getView(R.id.topic_content_rec_tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.setText((TextView) viewHolder.getView(R.id.topic_content_rec_name),topics.get(position).getName());
        viewHolder.setText((TextView) viewHolder.getView(R.id.topic_content_rec_title),topics.get(position).getTitle());
        viewHolder.setText((TextView) viewHolder.getView(R.id.topic_content_rec_content),topics.get(position).getContent());
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
