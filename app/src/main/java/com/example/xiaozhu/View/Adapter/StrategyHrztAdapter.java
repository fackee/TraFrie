package com.example.xiaozhu.View.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.Bean.StrategyH;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/30.
 */
public class StrategyHrztAdapter extends CommonRecyAdapter<StrategyH>{
    private Context mContext;
    private List<StrategyH> strategyHs;
    public StrategyHrztAdapter(List<StrategyH> strategyHs , Context mContext){
        super(mContext,strategyHs, R.layout.stra_hrzt_rec);
        this.mContext = mContext;
        this.strategyHs = strategyHs;
    }
    @Override
    protected CommonRecyViewHolder getViewHolder(View view, int viewType) {
        return new MyViewHolder(view,mContext);
    }

    @Override
    protected void showViewHolder(CommonRecyViewHolder viewHolder, int position) {
        Picasso.with(mContext).load(strategyHs.get(position).getImgSrc()).into((ImageView) viewHolder.getView(R.id.tra_hrzt_bg));
        viewHolder.setText((TextView) viewHolder.getView(R.id.tra_hrzt_desc),strategyHs.get(position).getDeSc());
    }
    class MyViewHolder extends CommonRecyViewHolder {
        public MyViewHolder(View itemView,Context context) {
            super(itemView,context);
        }
    }
}
