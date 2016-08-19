package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.RecruitList;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/8/2.
 */
public class RecruitListAdapter extends CommonRecyAdapter<RecruitList>{

    private Context mContext;
    private List<RecruitList> recruitLists;

    public RecruitListAdapter(Context mContext,List<RecruitList> recruitLists){
        super(mContext,recruitLists, R.layout.recruit_list_content);
        this.mContext = mContext;
        this.recruitLists = recruitLists;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        Picasso.with(mContext).load(recruitLists.get(position).getTxUrl()).into((CircleImageView)viewHolder.getView(R.id.recruit_list_tx));
        Picasso.with(mContext).load(recruitLists.get(position).getSexUrl()).into((ImageView) viewHolder.getView(R.id.recruit_list_sex));
        viewHolder.setText((TextView) viewHolder.getView(R.id.recruit_list_name),recruitLists.get(position).getName());
        viewHolder.setText((TextView) viewHolder.getView(R.id.recruit_list_content),recruitLists.get(position).getContent());
    }

    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
