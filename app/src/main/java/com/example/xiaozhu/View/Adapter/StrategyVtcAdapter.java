package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.StartegyV;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/30.
 */
public class StrategyVtcAdapter extends CommonRecyAdapter<StartegyV>{
    private Context mContext;
    private List<StartegyV> startegyVs;
    public StrategyVtcAdapter(List<StartegyV> startegyVs , Context mContext) {
        super(mContext,startegyVs, R.layout.stra_vtc_rec);
        this.mContext = mContext;
        this.startegyVs = startegyVs;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        Picasso.with(mContext).load(startegyVs.get(position).getImgSrc()).into((ImageView) viewHolder.getView(R.id.stra_vtc_gb));
        viewHolder.setText((TextView) viewHolder.getView(R.id.stra_vtc_desc),startegyVs.get(position).getDesc());
        viewHolder.setText((TextView) viewHolder.getView(R.id.stra_vtc_author),startegyVs.get(position).getName());
        viewHolder.setText((TextView) viewHolder.getView(R.id.stra_vtc_content),startegyVs.get(position).getContent());
    }
    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
