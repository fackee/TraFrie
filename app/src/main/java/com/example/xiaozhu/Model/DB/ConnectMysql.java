package com.example.xiaozhu.Model.DB;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/21.
 */
public class ConnectMysql {
    public ConnectMysql(){
        Connect();
    }

    private void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String url = "jdbc:mysql://localhost:3308/demo";
            Connection connection = (Connection) DriverManager.getConnection(url,"root","");
            Log.i("MYSQL===","SUCCESS");
            Statement stmt=(Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
