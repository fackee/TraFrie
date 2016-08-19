package com.example.xiaozhu.View.Activities;

import android.content.Context;
import android.os.Handler;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaozhu.Bean.Recruit;
import com.example.xiaozhu.Model.Net.Url;
import com.example.xiaozhu.Presenter.GetDataFromHttpPresenter;
import com.example.xiaozhu.View.Adapter.RecruitAdapter;
import com.example.xiaozhu.View.Adapter.ReplyVPagerAdapter;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.View.Fragments.RecruitListFragment;
import com.example.xiaozhu.View.Fragments.StrategyFragment;
import com.example.xiaozhu.View.Fragments.TopicFragment;
import com.example.xiaozhu.View.intefaces.IReciveData;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecruitActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "RecruitActivity";
    Handler mHandler = new Handler();
    private Context mContext = this;
    private Toolbar mToolbar;
    private CircleImageView mCircleImageView;
    private ImageView mImageView;
    private TextView name,title,content,status;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mButton;
    private List<Fragment> fragments;
    private String[] titles;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        initFragments();
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar)findViewById(R.id.ar_toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.setTitle("招募令");
        mToolbar.inflateMenu(R.menu.reply_menu);
        mCircleImageView = (CircleImageView)findViewById(R.id.ar_tx);
        mImageView = (ImageView)findViewById(R.id.ar_sex) ;
        name = (TextView)findViewById(R.id.ar_name);
        title = (TextView)findViewById(R.id.ar_title);
        content = (TextView)findViewById(R.id.ar_content);
        status = (TextView)findViewById(R.id.ar_stutas);
        mTabLayout = (TabLayout)findViewById(R.id.ar_tab);
        mButton = (Button)findViewById(R.id.ar_join) ;
        mViewPager = (ViewPager)findViewById(R.id.ar_viewpager);
        mViewPager.setAdapter(new ReplyVPagerAdapter(getSupportFragmentManager(),fragments,titles));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new RecruitListFragment());
        fragments.add(new RecruitListFragment());
        titles = new String[]{"已招募","申请中"};
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case -1:
                break;
        }
    }
}
