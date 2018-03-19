package com.example.myweiyue.module;

import android.content.Context;

import com.example.myweiyue.app.MyApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */
@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    MyApp providesApplication() {
        return (MyApp) mContext.getApplicationContext();
    }

    @Provides
    Context providesContext() {
        return mContext;
    }
}
