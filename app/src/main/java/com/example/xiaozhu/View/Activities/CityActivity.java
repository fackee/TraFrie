package com.example.xiaozhu.View.Activities;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.xiaozhu.Model.DB.ConnectMysql;
import com.example.xiaozhu.Presenter.Citypresenter;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityActivity extends BaseActivity {
    List<Map<String,Object>> data_list;
    private GridView mGridView;
    private List<Integer> imgid;
    private Citypresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_city);
        data_list = new ArrayList<>();
        initData();
        initView();
        new ConnectMysql();
    }

    private void initView(){
        mGridView = (GridView) findViewById(R.id.pic);
        String [] from ={"image",};
        int [] to = {R.id.pic};
        SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.city_pic, from, to);
        mGridView.setAdapter(sim_adapter);
    }

    public List<Map<String, Object>> initData(){
        imgid = new ArrayList<>();
        for(int i=0;i<15;i++){
            imgid.add(R.drawable.xm);
        }
        for(int i=0;i<imgid.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",imgid.get(i));
            data_list.add(map);
        }

        return data_list;
    }
}
