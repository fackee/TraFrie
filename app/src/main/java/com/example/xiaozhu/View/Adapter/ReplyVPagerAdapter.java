package com.example.xiaozhu.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xiaozhu on 2016/7/31.
 */
public class ReplyVPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;
    private FragmentManager fm;
    public ReplyVPagerAdapter(FragmentManager fm, List<Fragment> fragments,String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.fm = fm;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
