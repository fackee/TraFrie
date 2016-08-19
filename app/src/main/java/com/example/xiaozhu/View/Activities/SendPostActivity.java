package com.example.xiaozhu.View.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xiaozhu.Bean.SendData;
import com.example.xiaozhu.Model.Net.OkHttpUtil;
import com.example.xiaozhu.Model.Net.Url;
import com.example.xiaozhu.Model.Utills.MySharedreference;
import com.example.xiaozhu.Presenter.SendDataToHttpPresenter;
import com.example.xiaozhu.View.intefaces.ISender;
import com.example.xiaozhu.View.utils.ChangeUriToAbsPath;
import com.example.xiaozhu.View.utils.ImageUtils;
import com.example.xiaozhu.trafrie.BaseActivity;
import com.example.xiaozhu.trafrie.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendPostActivity extends BaseActivity implements ISender , View.OnClickListener{
    private static final String TAG="SENDACTIVITY";
    private static final int CHOOSE_PHOTE = 3;
    private Context mContext = this;
    private Activity activity = this;
    private Handler mHandler = new Handler();
    private EditText mContent;
    private Button Btcancel,Btsend;
    private ImageView AddPic;
    private SendDataToHttpPresenter presenter;
    private File[] files;
    private String[] formName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initView();
        presenter = new SendDataToHttpPresenter(mContext,mHandler,Url.SEND_POSTING_URL,this);
    }

    private void initView(){
        mContent = (EditText)findViewById(R.id.send_edit);
        Btsend = (Button)findViewById(R.id.send_send);
        Btcancel = (Button)findViewById(R.id.send_cancel);
        AddPic = (ImageButton)findViewById(R.id.select_pic);
        Btsend.setOnClickListener(this);
        Btcancel.setOnClickListener(this);
        AddPic.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_send:
                presenter.fetch();
                break;
            case R.id.send_cancel:
                break;
            case R.id.select_pic:
                selectPicture();
                break;
        }
    }
    public void selectPicture(){
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.pic_dialog);
        LinearLayout camara = (LinearLayout)window.findViewById(R.id.select_from_camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtils.pickImageFromCamara(activity);
                dialog.dismiss();
            }
        });
        LinearLayout galary = (LinearLayout)window.findViewById(R.id.select_from_galary);
        galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ImageUtils.pickImageFromAlnum(activity);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void showDialog() {

    }

    @Override
    public SendData sendData() {
        MySharedreference sharedreference = new MySharedreference(this);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String currentDate = format.format(new Date());
        SendData data = new SendData();
        Map<String,String> map= new HashMap<>();
        map.put("content",mContent.getText().toString());
        map.put("username",sharedreference.username);
        map.put("ptime", currentDate);
        Log.i("txurl",sharedreference.txUrl);
        map.put("txurl",sharedreference.txUrl);
        data.setMap(map);
        data.setFiles(files);
        data.setFormName(formName);
        return data;
    }

    @Override
    public void Success() {
        Toast.makeText(mContext,"发表成功",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ImageUtils.REQUEST_CODE_FROM_ALBUM:
                if(resultCode == RESULT_CANCELED) {
                    return;
                }
                Uri uri_album = data.getData();
                //ImageView imageView = (ImageView)findViewById(R.id.pick_pic);
                //Picasso.with(this).load(new File(ChangeUriToAbsPath.change(activity,uri_album))).into(imageView);
                Log.i(TAG, ChangeUriToAbsPath.change(activity,uri_album));
                files = new File[]{new File(ChangeUriToAbsPath.change(activity,uri_album))};
                formName = new String[]{"file"};
                break;
            case ImageUtils.REQUEST_CODE_FROM_CAMARA:
                if(resultCode == RESULT_CANCELED) {
                    ImageUtils.deletImageUri(mContext);
                }else{
                    ImageUtils.cropPhote(activity,ImageUtils.imageUriFromCmara);
                }
                break;
            case  ImageUtils.CROP_PHOTE:
                if(requestCode == RESULT_CANCELED){
                    return;
                }
                Log.i(TAG,ImageUtils.imageUriFromCmara+"");
                break;
        }
    }
}
