package com.example.myweiyue.net;

import android.support.annotation.StringDef;

import com.example.myweiyue.bean.NewsDetailBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 冯涛 on 2018/1/22.
 * E-mail:716774214@qq.com
 */

public class NewsApi {

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT, ACTION_DOWN, ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions {

    }

    public static NewsApi sInstance;
    private static NewsApiService mService;

    public NewsApi(NewsApiService newsApiService) {
        mService = newsApiService;
    }

    public static NewsApi getInstance(NewsApiService newsApiService) {
        if (sInstance == null) {
            sInstance = new NewsApi(newsApiService);
        }
        return sInstance;
    }

    /**
     * http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&action=default&pullNum=1
     * 获取新闻详情
     *
     * @param id
     * @param action
     * @param pullNum
     * @return
     */
    public Observable<List<NewsDetailBean>> getNewsDetail(String id, @Actions String action, int pullNum) {
        return mService.getNewsDetail(id, action, pullNum);
    }
}
