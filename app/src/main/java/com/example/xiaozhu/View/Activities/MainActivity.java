package com.example.xiaozhu.View.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.View.CustomView.CircleImageView;
import com.example.xiaozhu.View.Fragments.PostingFragment;
import com.example.xiaozhu.View.Fragments.RecruitFragment;
import com.example.xiaozhu.View.Fragments.StrategyFragment;
import com.example.xiaozhu.View.Fragments.TopicFragment;
import com.example.xiaozhu.trafrie.MyApplication;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener,TabLayout.OnTabSelectedListener{
    private final String TAG = "MAINACTIVITY";
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout mTabLayout;
    private List<Fragment> fragments;
    private Context mContext = this;
    private Handler mHandler = new Handler();
    private String uname,signal,txUrl;
    private Boolean IfOrNot;
    private Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_reorder_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawLayout.openDrawer(mNavigationView);
            }
        });
        mToolbar.inflateMenu(R.menu.main_tool);
    }

    private void initView() {
        MySharedreference sharedreference = new MySharedreference(mContext);
        uname = sharedreference.getPreferences().getString("username","");
        signal = sharedreference.getPreferences().getString("signal","");
        txUrl = sharedreference.getPreferences().getString("txurl","");
        IfOrNot = sharedreference.getPreferences().getBoolean("ifornotlogin",false);
        initFragments();
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        View view = mNavigationView.getHeaderView(0);
        initHeadView(view,IfOrNot);
        mDrawLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("风光"),true);
        mTabLayout.addTab(mTabLayout.newTab().setText("招募"));
        mTabLayout.addTab(mTabLayout.newTab().setText("热议"));
        mTabLayout.addTab(mTabLayout.newTab().setText("攻略"));
        mTabLayout.setOnTabSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main,new PostingFragment(mHandler));
        transaction.commit();
    }

    private void initFragments(){
        fragments = new ArrayList<>();
        fragments.add(new PostingFragment(mHandler));
        fragments.add(new RecruitFragment(mHandler));
        fragments.add(new TopicFragment());
        fragments.add(new StrategyFragment());
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_history:
                item.setChecked(false);
                break;
            case R.id.nav_collection:
                item.setChecked(false);
                break;
            case R.id.nav_info:
                item.setChecked(false);
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        mDrawLayout.openDrawer(mNavigationView);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.i(TAG,tab.getPosition()+"");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main,fragments.get(tab.getPosition()));
        transaction.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if(tab.getPosition()==0){

        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
    public void initHeadView(View view,Boolean flag){
        if (flag){
            Button login = (Button)view.findViewById(R.id.login);
            login.setVisibility(View.INVISIBLE);
            CircleImageView tx = (CircleImageView)view.findViewById(R.id.user_tx);
            tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,UerInfoActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            TextView name = (TextView)view.findViewById(R.id.user_name);
            TextView sig = (TextView)view.findViewById(R.id.user_sig);
            name.setText(uname);
            sig.setText(signal);
            Picasso.with(this).load(txUrl).into(tx);
        }else{
            LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.alreadylogin);
            linearLayout.setVisibility(View.INVISIBLE);
            Button login = (Button)view.findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
