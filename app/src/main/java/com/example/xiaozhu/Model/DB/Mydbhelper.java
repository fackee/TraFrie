package com.example.xiaozhu.Model.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by xiaozhu on 2016/7/20.
 */
public class Mydbhelper extends SQLiteOpenHelper{
    private static final String Create_CITY= "create table swf_area("
    +"id integer primary key autoincrement,"
    +"parent_id integer not null,"
    +"name text not null,"
    +"sort integer not null)";
    private Context mContext;
    public Mydbhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_CITY);
        Toast.makeText(mContext,"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
