package com.example.xiaozhu.View.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaozhu.View.Adapter.UserInfoPagerAdapter;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.View.Fragments.PostHotFragment;
import com.example.xiaozhu.View.Fragments.RecruitFragment;
import com.example.xiaozhu.View.Fragments.TopicFragment;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;

import java.util.ArrayList;
import java.util.List;

public class UerInfoActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private CircleImageView mCircleImageView;
    private TextView name,signal;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private Handler mHandler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_info);
        initFragments();
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar)findViewById(R.id.userinfo_toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.inflateMenu(R.menu.user_info);
        mImageView =(ImageView)findViewById(R.id.userinfo_head_bg);
        mCircleImageView = (CircleImageView)findViewById(R.id.userinfo_tx);
        name = (TextView)findViewById(R.id.userinfo_name);
        signal = (TextView)findViewById(R.id.userinfo_signal);
        mTabLayout = (TabLayout)findViewById(R.id.userinfo_tab);
        mViewPager = (ViewPager)findViewById(R.id.userinfo_viewpager);
        mViewPager.setAdapter(new UserInfoPagerAdapter(getSupportFragmentManager(),fragments));
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new PostHotFragment(mHandler));
        fragments.add(new RecruitFragment(mHandler));
        fragments.add(new TopicFragment());
    }

        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  -1:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
