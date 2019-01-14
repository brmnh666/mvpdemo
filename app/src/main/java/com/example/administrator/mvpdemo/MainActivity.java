package com.example.administrator.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mvpdemo.mvpdemo.service.entity.Book;
import com.example.administrator.mvpdemo.mvpdemo.service.presenter.BookPresenter;
import com.example.administrator.mvpdemo.mvpdemo.service.view.BookView;

public class MainActivity extends AppCompatActivity  {
private TextView textView;
private Button button;
private BookPresenter mBookPresenter=new BookPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    mBookPresenter.getSearchBooks("金瓶梅",null,0,1);
            }
        });
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
    }
    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            textView.setText(mBook.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();


        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }
}
