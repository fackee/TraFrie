package com.example.xiaozhu.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaozhu.View.Fragments.PostHotFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/26.
 */
public class PostViewPagerAdapter extends FragmentStatePagerAdapter{

    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    private List<Fragment> fragments;
    private List<String> taglist;
    private String[] titles = new String[]{"随便看看","到处走走"};
    private FragmentManager fm;
    public PostViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.fm = fm;
        taglist = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getItemPosition(Object object) {
        Log.i("Object",object.getClass().getSimpleName());
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
