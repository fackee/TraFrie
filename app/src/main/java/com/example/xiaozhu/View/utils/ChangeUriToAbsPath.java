package com.example.xiaozhu.View.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by xiaozhu on 2016/7/25.
 */
public class ChangeUriToAbsPath {
    public static String change(Activity activity, Uri uri){
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = activity.managedQuery( uri,
                proj,
                null,
                null,
                null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }
}
