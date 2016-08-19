package com.example.xiaozhu.View.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

/**
 * Created by xiaozhu on 2016/7/25.
 */
public class ImageUtils {
    public static final int REQUEST_CODE_FROM_ALBUM = 103;
    public static final int REQUEST_CODE_FROM_CAMARA=102;
    public static final int CROP_PHOTE = 104;
    public static Uri imageUriFromCmara;
    public static String name = null;
    public static void pickImageFromAlnum(final Activity activity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent,REQUEST_CODE_FROM_ALBUM);
    }

    public static void pickImageFromCamara(final Activity activity){
        imageUriFromCmara = createImageUri(activity);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUriFromCmara);
        activity.startActivityForResult(intent,REQUEST_CODE_FROM_CAMARA);
    }

    public static void cropPhote(final Activity activity,Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUriFromCmara,"image/*");
        intent.putExtra("scale",true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUriFromCmara);
        activity.startActivityForResult(intent,CROP_PHOTE);

    }

    private static Uri createImageUri(Context context){
        name = "test"+ System.currentTimeMillis();
        ContentValues values =new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME,name+".jpeg");
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        Uri uri = context.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        return uri;
    }
    public static void deletImageUri(Context context){
        context.getContentResolver().delete(imageUriFromCmara,null,null);
    }
}
