package com.example.myweiyue.component;

import android.content.Context;

import com.example.myweiyue.app.MyApp;
import com.example.myweiyue.module.ApplicationModule;
import com.example.myweiyue.module.HttpModule;
import com.example.myweiyue.net.JanDanApi;
import com.example.myweiyue.net.NewsApi;

import dagger.Component;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */
@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface ApplicationComponent {
    MyApp getApplication();

    NewsApi getNetEaseApi();

    JanDanApi getJanDanApi();

    Context getContext();
}
