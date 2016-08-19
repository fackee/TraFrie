package com.example.xiaozhu.Model.Net;

import android.content.Context;
import android.util.Log;

import com.example.xiaozhu.trafrie.R;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


/**
 * Created by xiaozhu on 2016/7/22.
 */
public class OkHttpUtil {
    private static  OkHttpClient okHttpClient = new OkHttpClient();
    /**
     *  获取请求对象
     */
    private static Request buildGetRequest(String urlString){
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        return request;
    }
    /**
     *  获取响应对象
     */
    private static Response biuldResponse(String urlString) throws IOException {
        Request request = buildGetRequest(urlString);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }
    /**
     *  获取ResponseBody对象
     */
    private static ResponseBody biudResponseBody(String urlString) throws IOException{
        Response response = biuldResponse(urlString);
        if(response.isSuccessful()){
            return response.body();
        }
        return null;
    }
    /**
     *  网络请求返回字符串
     */
    public static String GetStringFromUrl(String urlString) throws IOException{
        ResponseBody responseBody = biudResponseBody(urlString);
        if(responseBody!=null){
            return responseBody.string();
        }
        return null;
    }
    /**
     *  网络请返回字节数组
     */
    public static byte[] GetbyteFromUrl(String urlString) throws IOException{
        ResponseBody responseBody = biudResponseBody(urlString);
        if(responseBody!=null){
            return responseBody.bytes();
        }
        return null;
    }

    /**
     *  网络请返回流对象
     */
    public static InputStream GetInputStreamFromUrl(String urlString) throws IOException{
        ResponseBody responseBody = biudResponseBody(urlString);
        if(responseBody!=null){
            return responseBody.byteStream();
        }
        return null;
    }
    /**
     *  开启异步线程，通过实现回掉方法实现数据加载
     */
    public static void getDataAsync(String urlString, Callback callback){
        Request request = buildGetRequest(urlString);
        okHttpClient.newCall(request).enqueue(callback);
    }
    /**
     *  Post 上传图文
     */
    private static Request biuldPostRequest(String urlString , RequestBody requestBody){
        Request.Builder builder = new Request.Builder();
        builder.url(urlString).post(requestBody);
        return  builder.build();
    }

    private static String postRequestBody(String urlString , RequestBody requestBody) throws IOException {
        Request request = biuldPostRequest(urlString,requestBody);
        Response response  = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        return null;
    }

    private static RequestBody buildFileRequestBody(Map<String ,String> map , File[] files , String[] formFieldName){
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        //文件以为的其他数据
        if(map!=null){
            for(Map.Entry<String,String> entry : map.entrySet()){
                builder.addPart(Headers.of("Content-Disposition","form-data;name=\""+entry.getKey()+"\""),RequestBody.create(null,entry.getValue()));
            }
        }
        if(files!=null){
            for(int i=0;i<files.length;i++){
                File file = files[i];
                String fileName = file.getName();
                RequestBody requestBody = RequestBody.create(MediaType.parse(getMimeType(fileName)),file);
                builder.addPart(Headers.of("Content-Disposition","form-data;name=\""+formFieldName[i]+"\";filename=\""+fileName+"\""),requestBody);
                Log.i("Arguments",getMimeType(fileName)+formFieldName[i]+fileName);
            }
        }
        return builder.build();
    }
    private static String getMimeType(String fileName){
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(fileName);
        if(contentTypeFor==null){
            contentTypeFor = "application/octec-stream";
        }
        return contentTypeFor;
    }
    private static void postRequestBodyAsycn(String urlString ,RequestBody requestBody,Callback callback){
        Request request = biuldPostRequest(urlString,requestBody);
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void postMapAndFilePairAsycn(String urlString , Map<String,String> map ,File[] files,String[] formFieldName,Callback callback){
        RequestBody requestBody = buildFileRequestBody(map,files,formFieldName);
        postRequestBodyAsycn(urlString,requestBody,callback);
    }
}

