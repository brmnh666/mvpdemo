package com.example.administrator.mvpdemo.mvpdemo.service.presenter;

import android.content.Intent;

import com.example.administrator.mvpdemo.mvpdemo.service.view.View;

/*presenter主要用于网络的请求以及数据的获取*/
public interface Presenter {
    void onCreate();
    void onStart();//暂时没用到
    void onStop();
    void pause();//暂时没用到
    void attachView(View view);
    void attachIncomingIntent(Intent intent);//暂时没用到
}
