package com.example.myweiyue.app;


import android.util.Log;

import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.component.DaggerApplicationComponent;
import com.example.myweiyue.module.ApplicationModule;
import com.example.myweiyue.module.HttpModule;
import com.example.myweiyue.utils.ContextUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */

public class MyApp extends LitePalApplication {

    private ApplicationComponent mApplicationComponent;

    private static MyApp sMyApp;

    public static int width = 0;

    public static int height = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        BGASwipeBackManager.getInstance().init(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
        LitePal.initialize(this);
        width = ContextUtils.getSreenWidth(MyApp.getContext());
        height = ContextUtils.getSreenHeight(MyApp.getContext());
    }

    public static MyApp getInstance() {
        return sMyApp;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
