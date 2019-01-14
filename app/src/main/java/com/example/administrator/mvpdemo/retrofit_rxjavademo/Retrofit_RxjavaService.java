package com.example.administrator.mvpdemo.retrofit_rxjavademo;

import com.example.administrator.mvpdemo.retrofitdemo.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/*URL:https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1
* */

public interface Retrofit_RxjavaService {
  /*  @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("start") int start,
                             @Query("count") int count);
*/
  /*加入rxjava后*/
    @GET("book/search")
    Observable <Book> getSearchBook(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);

/*可以看到，在原来的RetrofitService 中我们把getSearchBook方法返回的类型Call改为了Observable，
也就是被观察者。其他都没变。然后就是创建RetrofitService 实体类：*/
}
