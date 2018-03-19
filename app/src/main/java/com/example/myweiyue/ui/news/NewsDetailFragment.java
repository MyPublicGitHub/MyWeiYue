package com.example.myweiyue.ui.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.myweiyue.R;
import com.example.myweiyue.app.MyApp;
import com.example.myweiyue.bean.NewsDetailBean;
import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.component.DaggerHttpComponent;
import com.example.myweiyue.net.NewsApi;
import com.example.myweiyue.net.NewsUtils;
import com.example.myweiyue.ui.adapter.NewsDetailAdapter;
import com.example.myweiyue.ui.base.BaseFragment;
import com.example.myweiyue.ui.news.contract.NewsDetailContract;
import com.example.myweiyue.ui.news.presenter.NewsDetailPresenter;
import com.example.myweiyue.utils.ContextUtils;
import com.example.myweiyue.utils.DebugUtil;
import com.example.myweiyue.utils.ImageLoaderUtil;
import com.example.myweiyue.widget.CustomLoadMoreView;
import com.example.myweiyue.widget.NewsDetailPop;
import com.flyco.animation.SlideEnter.SlideRightEnter;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 */
public class NewsDetailFragment extends BaseFragment<NewsDetailPresenter> implements NewsDetailContract.View {

    private static final String TAG = "DetailFragment";

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;
    @BindView(R.id.tv_toast)
    TextView tvToast;
    @BindView(R.id.rl_top_toast)
    RelativeLayout rlTopToast;

    private View view_Focus;//顶部banner
    private Banner mBanner;
    private NewsDetailPop newsDetailPop;
    private NewsDetailPop mPop;
    private String newsID;
    private int mPosition;
    private List<NewsDetailBean.ItemBean> mBeanList;
    private List<NewsDetailBean.ItemBean> mBannerList;
    private NewsDetailAdapter newsDetailAdapter;
    private int upPullNum = 1;
    private int downPullNum = 1;
    private boolean isRemoveHeaderView = false;

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRemoveHeaderView = true;
                mPresenter.getData(newsID, NewsApi.ACTION_DOWN, downPullNum);
            }
        });
        mBeanList = new ArrayList<>();
        mBannerList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(newsDetailAdapter = new NewsDetailAdapter(mBeanList, getActivity()));
        newsDetailAdapter.setEnableLoadMore(true);
        newsDetailAdapter.setLoadMoreView(new CustomLoadMoreView());
        newsDetailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        newsDetailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getData(newsID, NewsApi.ACTION_UP, upPullNum);
            }
        }, mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetailBean.ItemBean itemBean = (NewsDetailBean.ItemBean) adapter.getItem(position);
                toRead(itemBean);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetailBean.ItemBean itemBean = (NewsDetailBean.ItemBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.ivClose:
                        view.getHeight();
                        int[] location = new int[2];
                        view.getLocationInWindow(location);
                        if (itemBean.style != null) {
                            if (ContextUtils.getSreenWidth(MyApp.getContext()) - 50 - location[1] < ContextUtils.dip2px(MyApp.getContext(), 80)) {
                                newsDetailPop.anchorView(view).gravity(Gravity.TOP).setBaceReason(itemBean.style.backreason, true, position).show();
                            } else {
                                newsDetailPop.anchorView(view).gravity(Gravity.BOTTOM).setBaceReason(itemBean.style.backreason, false, position).show();
                            }
                        }
                        break;
                }
            }
        });

        view_Focus = getView().inflate(getActivity(), R.layout.news_detail_headerview, null);
        mBanner = view_Focus.findViewById(R.id.banner);
        //设置banner的样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageLoaderUtil.LoadImage(getActivity(), path, imageView);
            }
        }).setDelayTime(3000).setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mBannerList.size() >= 1) {
                    bannerToRead(mBeanList.get(position));
                }
            }
        });
        newsDetailPop = new NewsDetailPop(getActivity()).alignCenter(false).widthScale(0.95f)
                .showAnim(new SlideRightEnter()).dismissAnim(new SlideRightExit())
                //.showAnim(new FlipRightEnter()).dismissAnim(new FlipHorizontalExit())
                .offset(-100, 0).dimEnabled(true);
        newsDetailPop.setOnClickListener(new NewsDetailPop.OnClickListener() {
            @Override
            public void onClick(int position) {
                newsDetailPop.dismiss();
                newsDetailAdapter.remove(position);
                showToast(0, false);
            }
        });
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            newsID = getArguments().getString("newsID");
            mPosition = getArguments().getInt("position");
            mPresenter.getData(newsID, NewsApi.ACTION_DEFAULT, 1);
        }
    }

    @Override
    public void onRetry() {
        initData();
    }

    @Override
    public void loadBannerData(NewsDetailBean bean) {
        List<String> titleList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        mBannerList.clear();
        for (NewsDetailBean.ItemBean itemBean : bean.item) {
            if (!TextUtils.isEmpty(itemBean.thumbnail)) {
                titleList.add(itemBean.thumbnail);
                mBannerList.add(itemBean);
                urlList.add(itemBean.thumbnail);
            }
        }
        if (urlList.size() > 0) {
            mBanner.setImages(urlList);
            mBanner.setBannerTitles(titleList);
            mBanner.start();
            if (newsDetailAdapter.getHeaderLayoutCount() < 1) {
                newsDetailAdapter.addHeaderView(view_Focus);
            }
        }
    }

    @Override
    public void loadTopData(NewsDetailBean newsDetailBean) {

    }

    @Override
    public void loadData(List<NewsDetailBean.ItemBean> itemBeanList) {
        if (itemBeanList == null || itemBeanList.size() == 0) {
            showFailed();
            mPtrFrameLayout.refreshComplete();
        } else {
            downPullNum++;
            if (isRemoveHeaderView){
                newsDetailAdapter.removeAllHeaderView();
            }
            newsDetailAdapter.setNewData(itemBeanList);
            showToast(itemBeanList.size(),true);
            mPtrFrameLayout.refreshComplete();
            showSuccess();
            DebugUtil.debug("loadData: " + itemBeanList.toString());
        }
        DebugUtil.debug("loadData: " + itemBeanList.toString());
    }

    @Override
    public void loadMoreData(List<NewsDetailBean.ItemBean> itemBeanList) {
        if (itemBeanList == null||itemBeanList.size()==0){
            newsDetailAdapter.loadMoreFail();
        }else {
            upPullNum++;
            newsDetailAdapter.addData(itemBeanList);
            newsDetailAdapter.loadMoreComplete();
        }
    }


    private void showToast(int num, boolean isRefresh) {
        if (isRefresh) {
            tvToast.setText(String.format(getResources().getString(R.string.news_toast), num + ""));
        } else {
            tvToast.setText("将为你减少此类内容");
        }
    }

    private void bannerToRead(NewsDetailBean.ItemBean itemBean) {
        if (itemBean != null) {
            switch (itemBean.type) {
                case NewsUtils.TYPE_DOC:
                    T("TYPE_DOC");
                    break;
                case NewsUtils.TYPE_SLIDE:

                    T("TYPE_SLIDE");
                    break;
                case NewsUtils.TYPE_ADVERT:
                    T("TYPE_ADVERT");
                    break;
                case NewsUtils.TYPE_PH_VIDEO:
                    T("TYPE_PH_VIDEO");
                    break;
            }
        }
    }

    private void toRead(NewsDetailBean.ItemBean itemBean) {
        if (itemBean != null) {
            switch (itemBean.getItemType()) {
                case NewsDetailBean.ItemBean.TYPE_DOC_TITLEIMG:
                case NewsDetailBean.ItemBean.TYPE_DOC_SLIDEIMG:
                    T("TYPE_DOC_TITLEIMG,TYPE_DOC_SLIDEIMG");
                    break;
                case NewsDetailBean.ItemBean.TYPE_ADVERT_TITLEIMG:
                case NewsDetailBean.ItemBean.TYPE_ADVERT_LONGIMG:
                case NewsDetailBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
                    T("TYPE_ADVERT_TITLEIMG,TYPE_ADVERT_LONGIMG,TYPE_ADVERT_SLIDEIMG");
                    break;
                case NewsDetailBean.ItemBean.TYPE_SLIDE:
                    T("TYPE_SLIDE");
                    break;
                case NewsDetailBean.ItemBean.TYPE_PHVIDEO:
                    T("TYPE_PHVIDEO");
                    break;
            }
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news_detail;
    }

    public static NewsDetailFragment newInstance(String newsID, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("newsID", newsID);
        bundle.putInt("position", position);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
