package com.example.administrator.mvpdemo.retrofit_rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.mvpdemo.R;
import com.example.administrator.mvpdemo.retrofitdemo.Book;
import com.example.administrator.mvpdemo.retrofitdemo.RetrofitService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Retrofit_RxjavaActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);


        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持rxjava
                .build();

        Retrofit_RxjavaService service = retrofit.create(Retrofit_RxjavaService.class);
        Observable <Book> observable =service.getSearchBook("金瓶梅",null,0,1);

        observable.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程3
                   .observeOn(AndroidSchedulers.mainThread())//请求完成后再主线程更新ui'
                   .subscribe(new Observer<Book>() { //订阅
                       @Override
                       public void onCompleted() {
                           //所有事件都完成，可以做些操作

                       }

                       @Override
                       public void onError(Throwable e) {
                        e.printStackTrace();//请求过程中发生错误
                       }

                       @Override
                       public void onNext(Book book) {
                       //这里的book就是我们请求接口返回的实体类
                       }
                   });
    }
}
