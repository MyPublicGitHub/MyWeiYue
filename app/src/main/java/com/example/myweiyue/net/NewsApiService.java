package com.example.myweiyue.net;

import com.example.myweiyue.bean.NewsDetailBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 冯涛 on 2018/1/22.
 * E-mail:716774214@qq.com
 */

public interface NewsApiService {

    @GET("ClientNews")
    Observable<List<NewsDetailBean>> getNewsDetail(@Query("id") String id, @Query("action") String action, @Query("pullNum") int pullNum);
}
