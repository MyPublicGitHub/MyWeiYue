package com.example.myweiyue.ui.news.presenter;

import com.example.myweiyue.bean.NewsDetailBean;
import com.example.myweiyue.net.BaseObserver;
import com.example.myweiyue.net.NewsApi;
import com.example.myweiyue.net.NewsUtils;
import com.example.myweiyue.net.RxSchedulers;
import com.example.myweiyue.ui.base.BasePresenter;
import com.example.myweiyue.ui.news.contract.NewsDetailContract;
import com.example.myweiyue.utils.DebugUtil;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {

    private static final String TAG = "NewsDetailPresenter";

    NewsApi mNewsApi;

    @Inject
    public NewsDetailPresenter(NewsApi newsApi) {
        mNewsApi = newsApi;
    }

    @Override
    public void getData(String id, final String action, int pullNum) {
        DebugUtil.debug(TAG,"id:"+id+",action:"+action+",pullNum:"+pullNum);
        mNewsApi.getNewsDetail(id, action, pullNum)
                .compose(RxSchedulers.<List<NewsDetailBean>>applySchedulers())
                .map(new Function<List<NewsDetailBean>, NewsDetailBean>() {
                    @Override
                    public NewsDetailBean apply(List<NewsDetailBean> newsDetailBeans) throws Exception {
                        DebugUtil.debug(TAG,"apply:"+newsDetailBeans.toString());
                        for (NewsDetailBean bean : newsDetailBeans) {
                            if (NewsUtils.isBannerNews(bean))
                                mView.loadBannerData(bean);
                            if (NewsUtils.isTobNews(bean))
                                mView.loadTopData(bean);
                        }
                        return newsDetailBeans.get(0);
                    }
                })
                .map(new Function<NewsDetailBean, List<NewsDetailBean.ItemBean>>() {
                    @Override
                    public List<NewsDetailBean.ItemBean> apply(NewsDetailBean newsDetailBean) throws Exception {
                        Iterator<NewsDetailBean.ItemBean> iterator = newsDetailBean.item.iterator();
                        while (iterator.hasNext()) {
                            try {
                                NewsDetailBean.ItemBean bean = iterator.next();
                                if (NewsUtils.isDocNews(bean.type)) {//是文档类型doc
                                    if (bean.style.view != null) {
                                        if (NewsUtils.isTitleImgNews(bean.style.view)) {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_DOC_TITLEIMG;
                                        } else {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_DOC_SLIDEIMG;
                                        }
                                    }
                                } else if (NewsUtils.isAdvertNews(bean.type)) {//广告
                                    if (bean.style != null) {
                                        if (NewsUtils.isTitleImgNews(bean.style.view)) {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        } else if (NewsUtils.isSlideImgNews(bean.style.view)) {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_ADVERT_LONGIMG;
                                        }
                                    } else {
                                        iterator.remove();
                                    }
                                } else if (NewsUtils.isSlideNews(bean.type)) {//gif
                                    if (NewsUtils.isDocNews(bean.link.type)) {
                                        if (NewsUtils.isSlideImgNews(bean.style.view)) {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_DOC_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsDetailBean.ItemBean.TYPE_DOC_TITLEIMG;
                                        }
                                    } else {
                                        bean.itemType = NewsDetailBean.ItemBean.TYPE_SLIDE;
                                    }
                                } else if (NewsUtils.isPhVideoNews(bean.type)) {//视频
                                    bean.itemType = NewsDetailBean.ItemBean.TYPE_PHVIDEO;
                                } else {
                                    // 凤凰新闻 类型比较多，目前只处理能处理的类型
                                    iterator.remove();
                                }
                            } catch (Exception e) {
                                iterator.remove();
                                e.printStackTrace();
                            }
                        }

                        return newsDetailBean.item;
                    }
                })
                .compose(mView.<List<NewsDetailBean.ItemBean>>bindToLife())
                .subscribe(new BaseObserver<List<NewsDetailBean.ItemBean>>() {
                    @Override
                    public void onSuccess(List<NewsDetailBean.ItemBean> itemBeans) {
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(itemBeans);
                        } else {
                            mView.loadMoreData(itemBeans);
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(null);
                        } else {
                            mView.loadMoreData(null);
                        }
                    }
                });
    }
}
