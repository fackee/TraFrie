package com.example.xiaozhu.View.CustomView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaozhu on 2016/7/27.
 */
public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int space;

    public HorizontalSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0)
            outRect.left = space;
    }
}
