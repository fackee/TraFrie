package com.example.xiaozhu.View.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xiaozhu on 2016/7/22.
 */
public class NoScrolGridView extends GridView{
    public NoScrolGridView(Context context) {
        super(context);
    }

    public NoScrolGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrolGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
