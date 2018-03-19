package com.example.myweiyue.net;

import com.example.myweiyue.bean.NewsDetailBean;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 * desc:
 */

public class NewsUtils {
    //顶部banner新闻
    public static final String TYPE_BANNER = "focus";
    //置顶新闻
    public static final String TYPE_TOP = "top";
    //常规新闻
    public static final String TYPE_LIST = "list";

    //文章类型
    public static final String TYPE_DOC = "doc";
    //广告类型
    public static final String TYPE_ADVERT = "advert";
    //图片类型
    public static final String TYPE_SLIDE = "slide";
    //视频类型
    public static final String TYPE_PH_VIDEO = "phvideo";

    //显示形式单图
    public static final String VIEW_TITLE_IMG = "titleimg";
    //显示形式多图
    public static final String VIEW_SLIDE_IMG = "slideimg";
    //显示形式多图
    public static final String VIEW_LONG_IMG = "longimg";

    public static boolean isBannerNews(NewsDetailBean bean) {
        return bean.equals(TYPE_BANNER);
    }

    public static boolean isTobNews(NewsDetailBean bean) {
        return bean.equals(TYPE_TOP);
    }

    public static boolean isListNews(NewsDetailBean bean) {
        return bean.equals(TYPE_LIST);
    }

    /**
     * 文字类型
     * @param type
     * @return
     */
    public static boolean isDocNews(String type) {
        return type.equals(TYPE_DOC);
    }

    public static boolean isAdvertNews(String type) {
        return type.equals(TYPE_ADVERT);
    }

    public static boolean isSlideNews(String type) {
        return type.equals(TYPE_SLIDE);
    }

    public static boolean isPhVideoNews(String bean) {
        return bean.equals(TYPE_PH_VIDEO);
    }

    public static boolean isTitleImgNews(String view) {
        return view.equals(VIEW_TITLE_IMG);
    }

    public static boolean isSlideImgNews(String type) {
        return type.equals(VIEW_SLIDE_IMG);
    }

    public static boolean isLongImgNews(String type) {
        return type.equals(VIEW_LONG_IMG);
    }
}
