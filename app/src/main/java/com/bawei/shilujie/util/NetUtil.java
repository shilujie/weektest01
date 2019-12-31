package com.bawei.shilujie.util;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:8:46
 *@Description:${DESCRIPTION}
 **/


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    //单例
    public static NetUtil getInstance() {
        if (netUtil == null){
            synchronized (NetUtil.class){
                if (netUtil == null){
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }

    //get请求
    public void getJsonGet(String httpUrl,MyCallBack myCallBack){
        Request request = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.getError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                if (response != null && response.isSuccessful()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getSuccess(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getError(new Throwable("失败"));
                        }
                    });
                }
            }
        });
    }

    //post请求
    public void getJsonPost(String httpUrl, Map<String,String> map, MyCallBack myCallBack){

        FormBody.Builder builder = new FormBody.Builder();
        for (String key: map.keySet()) {
            String value = map.get(key);
            builder.add(key,value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.getError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                if (response != null && response.isSuccessful()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getSuccess(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getError(new Throwable("失败"));
                        }
                    });
                }
            }
        });
    }

    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.getActiveNetworkInfo();
        if (connectivityManager != null) {
            return true;
        }else {
            return false;
        }
    }
    //接口
    public interface MyCallBack{
        void getSuccess(String json);
        void getError(Throwable throwable);
    }
}
