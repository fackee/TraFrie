package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.xiaozhu.Bean.Recruit;
import com.example.xiaozhu.trafrie.R;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/22.
 */
public class RecruitAdapter extends CommonRecyAdapter<Recruit> implements View.OnClickListener{
    private List<Recruit> recruits;
    private Context mContext;
    private OnRecyclerItemOnclickListner listner = null;
    public RecruitAdapter(Context mContext,List<Recruit> recruits){
        super(mContext,recruits,R.layout.recruit_rec_content);
        this.recruits = recruits;
        this.mContext = mContext;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        view.setOnClickListener(this);
        return new MyViewHolder(view,mContext);
    }
    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        viewHolder.setText((TextView) viewHolder.getView(R.id.recruit_title),recruits.get(position).getTitle());
        viewHolder.setText((TextView) viewHolder.getView(R.id.recruit_content),recruits.get(position).getContent());
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

    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
    public static interface OnRecyclerItemOnclickListner{
        void onItemClick(View view,int position);
    }
}
