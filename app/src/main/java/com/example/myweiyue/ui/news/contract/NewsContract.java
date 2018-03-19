package com.example.myweiyue.ui.news.contract;

import com.example.myweiyue.bean.Channel;
import com.example.myweiyue.ui.base.BaseContract;

import java.util.List;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public interface NewsContract {
    interface View extends BaseContract.BaseView {
        void loadData(List<Channel> channels, List<Channel> otherChannels);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        /**
         * 初始化频道
         */
        void getChannel();
    }
}
