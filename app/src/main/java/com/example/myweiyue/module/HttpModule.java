package com.example.myweiyue.module;

import com.example.myweiyue.app.MyApp;
import com.example.myweiyue.net.ApiConstants;
import com.example.myweiyue.net.JanDanApi;
import com.example.myweiyue.net.JanDanApiService;
import com.example.myweiyue.net.NewsApi;
import com.example.myweiyue.net.NewsApiService;
import com.example.myweiyue.net.RetrofitConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        Cache cache = new Cache(new File(MyApp.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

        return new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor())
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    @Provides
    NewsApi provideNetEaseApis(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());
        return NewsApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(NewsApiService.class));
    }

    @Provides
    JanDanApi provideJanDanApis(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());
        return JanDanApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sJanDanApi)
                .build().create(JanDanApiService.class));
    }
}
