package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.Posting;
import com.example.xiaozhu.Model.Net.OkHttpClientUtils;
import com.example.xiaozhu.View.Activities.ReplyActivity;
import com.example.xiaozhu.View.Activities.UerInfoActivity;
import com.example.xiaozhu.View.CustomView.NoScrolGridView;
import com.example.xiaozhu.trafrie.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/19.
 */
public class PostingAdapter extends CommonRecyAdapter<Posting>{
    private List<Posting> postings;
    private Context mContext;
    public PostingAdapter(Context mContext,List<Posting> postings){
        super(mContext,postings,R.layout.rec_layout);
        this.postings = postings;
        this.mContext = mContext;
    }

    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, final int position) {
        Picasso.with(mContext).load(postings.get(position).getTxUrl()).into((ImageView) viewHolder.getView(R.id.tx));
        viewHolder.getView(R.id.tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,UerInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        viewHolder.getView(R.id.post_reply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext,ReplyActivity.class);
                it.putExtra("txUrl",postings.get(position).getTxUrl());
                it.putExtra("username",postings.get(position).getUsername());
                it.putExtra("time",postings.get(position).getTime());
                it.putExtra("content",postings.get(position).getContent());
                it.putExtra("replynum",postings.get(position).getReplyNum());
                it.putExtra("praisenum",postings.get(position).getPraiseNum());
                it.putStringArrayListExtra("imgUrl",postings.get(position).getImgurl());
                mContext.startActivity(it);
            }
        });
        viewHolder.getView(R.id.post_praise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map = new HashMap<String, String>();
                map.put("poster",postings.get(position).getUsername());
                map.put("ptime",postings.get(position).getTime());
                OkHttpClientUtils.postMapAndFilePairAsycn(mContext, "http://192.168.191.1:8080/lvyou/updatePraise.php", map, null, null, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                    }
                });
            }
        });
        viewHolder.setText((TextView) viewHolder.getView(R.id.user),postings.get(position).getUsername());
        viewHolder.setText((TextView) viewHolder.getView(R.id.time),postings.get(position).getTime());
        viewHolder.setText((TextView) viewHolder.getView(R.id.content),postings.get(position).getContent());
        viewHolder.setGridAdapter((NoScrolGridView) viewHolder.getView(R.id.show_pic),new PostImageAdapter(postings.get(position).getImgurl(),mContext));
        viewHolder.setText((TextView) viewHolder.getView(R.id.replynum),postings.get(position).getReplyNum());
        viewHolder.setText((TextView) viewHolder.getView(R.id.praisenum),postings.get(position).getPraiseNum());
    }


    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
