package com.example.xiaozhu.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/31.
 */
public class UserInfoPagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private String[] titles = new String[]{"TA的动态","TA的话题","TA的攻略"};
    private FragmentManager fm;
    public UserInfoPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.fm = fm;
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
