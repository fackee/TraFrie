package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.Reply;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/8/1.
 */
public class ReplyRecAdapter extends CommonRecyAdapter<Reply> implements View.OnClickListener{
    private Context mContext;
    private List<Reply> replies;
    private List<Integer> addMenu;
    public CommonRecyViewHolder viewHolder;
    public ReplyRecAdapter(Context mContext,List<Reply> replies){
        super(mContext,replies, R.layout.reply_content);
        this.mContext = mContext;
        this.replies = replies;
        addMenu = new ArrayList<>();
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(view,mContext);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder,final int position) {
        viewHolder.InflaterMenu((Toolbar) viewHolder.getView(R.id.reply_content_toolbar),R.menu.reply_tool);
        Picasso.with(mContext).load(replies.get(position).getTxUrl()).into((ImageView) viewHolder.getView(R.id.reply_content_tx));
        viewHolder.setText((TextView) viewHolder.getView(R.id.reply_content_name),replies.get(position).getName());
        viewHolder.setText((TextView) viewHolder.getView(R.id.reply_content_time),replies.get(position).getTime());
        viewHolder.setText((TextView) viewHolder.getView(R.id.reply_content_content),replies.get(position).getContent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                break;
        }
    }

    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
