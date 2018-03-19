package com.example.myweiyue.ui.news.contract;

import com.example.myweiyue.bean.NewsDetailBean;
import com.example.myweiyue.ui.base.BaseContract;

import java.util.List;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public interface NewsDetailContract {
    interface View extends BaseContract.BaseView {
        /**
         * 加载Banner资源
         */
        void loadBannerData(NewsDetailBean newsDetailBean);

        /**
         * 加载置顶新闻
         */
        void loadTopData(NewsDetailBean newsDetailBean);

        /**
         * 加载新闻数据
         *
         * @param itemBeanList
         */
        void loadData(List<NewsDetailBean.ItemBean> itemBeanList);

        /**
         * 加载更多新闻数据
         *
         * @param itemBeanList
         */
        void loadMoreData(List<NewsDetailBean.ItemBean> itemBeanList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        /**
         * 获取新闻详细信息
         *
         * @param id      频道ID值
         * @param action  用户操作方式
         *                1：下拉 down
         *                2：上拉 up
         *                3：默认 default
         * @param pullNum 操作次数 累加
         */
        void getData(String id, String action, int pullNum);
    }
}
