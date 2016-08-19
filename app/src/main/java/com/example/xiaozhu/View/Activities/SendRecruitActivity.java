package com.example.xiaozhu.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;

public class SendRecruitActivity extends BaseActivity implements View.OnClickListener,Toolbar.OnMenuItemClickListener{

    private EditText title,content;
    private Toolbar head,foot;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_recruit);
        initView();
    }

    private void initView() {
        title = (EditText)findViewById(R.id.send_rec_title);
        content = (EditText)findViewById(R.id.send_rec_content);
        head = (Toolbar)findViewById(R.id.sr_head);
        head.setNavigationIcon(R.drawable.ic_clear_white_24dp);
        head.setNavigationOnClickListener(this);
        head.setOnMenuItemClickListener(this);
        head.setTitle("招募驴友");
        head.inflateMenu(R.menu.send_rec_head);
        foot = (Toolbar)findViewById(R.id.sr_foot);
        foot.inflateMenu(R.menu.send_rec_foot);
        foot.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  -1:
                Intent it = new Intent(this,MainActivity.class);
                startActivity(it);
                finish();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send_rec_send:
                break;
            case R.id.save:
                break;
            case R.id.aite:
                break;
            case R.id.pick_pic:
                break;
        }
        return false;
    }
}
