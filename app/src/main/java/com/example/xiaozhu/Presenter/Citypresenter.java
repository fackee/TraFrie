package com.example.xiaozhu.Presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xiaozhu.Model.DB.Mydbhelper;
import com.example.xiaozhu.trafrie.MyApplication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/20.
 */
public class Citypresenter {
    private Mydbhelper mydbhelper;
    private Map<String,Integer> provinces;
    private Map<String,Integer> municipalities;
    SQLiteDatabase database;
    public Citypresenter(){
        mydbhelper = new Mydbhelper(MyApplication.getContext(),"City.db",null,1);
        database = mydbhelper.getWritableDatabase();
        provinces = new LinkedHashMap<>();
        municipalities =new LinkedHashMap<>();
        getData();
    }
    public void getData(){
        Cursor cursor = database.query("swf_area",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getInt(cursor.getColumnIndex("id"))%100==0){
                    provinces.put(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(cursor.getColumnIndex("id")));
                }else{
                    municipalities.put(cursor.getString(cursor.getColumnIndex("name")),cursor.getInt(cursor.getColumnIndex("paren_id")));
                }
            }while (cursor.moveToNext());
        }
    }
}
