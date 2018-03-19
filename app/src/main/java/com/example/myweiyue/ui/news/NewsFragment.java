package com.example.myweiyue.ui.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import com.example.myweiyue.R;
import com.example.myweiyue.bean.Channel;
import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.component.DaggerHttpComponent;
import com.example.myweiyue.database.ChannelDao;
import com.example.myweiyue.event.NewChannelEvent;
import com.example.myweiyue.event.SelectChannelEvent;
import com.example.myweiyue.ui.adapter.ChannelPagerAdapter;
import com.example.myweiyue.ui.base.BaseFragment;
import com.example.myweiyue.ui.news.contract.NewsContract;
import com.example.myweiyue.ui.news.presenter.NewsPresenter;
import com.example.myweiyue.widget.CustomViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    private static final String TAG = "NewsFragment";

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.ib_edit)
    ImageButton mIbEdit;
    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;

    private ChannelPagerAdapter mChannelPagerAdapter;
    private List<Channel> mSelectedData;
    private List<Channel> mUnSelectedData;
    private int selectedIndex;
    private String selectedChannel;

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedIndex = position;
                selectedChannel = mSelectedData.get(position).getChannelName();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }

    @Override
    public void initData() {
        mSelectedData = new ArrayList<>();
        mUnSelectedData = new ArrayList<>();
        mPresenter.getChannel();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void loadData(List<Channel> channels, List<Channel> otherChannels) {
        if (channels != null) {
            mSelectedData.clear();
            mSelectedData.addAll(channels);
            mUnSelectedData.clear();
            mUnSelectedData.addAll(otherChannels);
            mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), channels);
            mViewpager.setAdapter(mChannelPagerAdapter);
            mViewpager.setOffscreenPageLimit(2);
            mViewpager.setCurrentItem(0, false);
            mSlidingTabLayout.setViewPager(mViewpager);
            T("loadData");
        } else {
            T("数据异常");
        }
    }
    @Subscriber
    private void updateChannel(NewChannelEvent event) {
        if (event != null) {
            if (event.mSelectedData != null && event.mUnSelectedData != null) {
                mSelectedData = event.mSelectedData;
                mUnSelectedData = event.mUnSelectedData;
                mChannelPagerAdapter.updateChannel(mSelectedData);
                mSlidingTabLayout.notifyDataSetChanged();

                ChannelDao.saveChannels(event.mAllData);

                List<String> intehers = new ArrayList<>();

                for (Channel channel : mSelectedData) {
                    intehers.add(channel.getChannelName());
                }
                if (TextUtils.isEmpty(event.mFirstChannelName)) {
                    if (!intehers.contains(selectedChannel)) {
                        selectedChannel = mSelectedData.get(selectedIndex).getChannelName();
                        mViewpager.setCurrentItem(selectedIndex, false);
                    } else {
                        setViewpagerPosition(intehers, selectedChannel);
                    }
                } else {
                    setViewpagerPosition(intehers, event.mFirstChannelName);
                }
            }
        }
    }
    @Subscriber
    private void selectChannelEvent(SelectChannelEvent selectChannelEvent) {
        if (selectChannelEvent == null) return;
        List<String> integers = new ArrayList<>();
        for (Channel channel : mSelectedData) {
            integers.add(channel.getChannelName());
        }
        setViewpagerPosition(integers, selectChannelEvent.channelName);
    }
    /**
     * 设置当前选中页
     * @param integers
     * @param channelName
     */
    private void setViewpagerPosition(List<String> integers, String channelName) {
        if (!TextUtils.isEmpty(channelName) && integers != null) {
            for (int i = 0; i < integers.size(); i++) {
                if (integers.get(i).equals(channelName)) {
                    selectedChannel = integers.get(i);
                    selectedIndex = i;
                    break;
                }
            }
            mViewpager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mViewpager.setCurrentItem(selectedIndex, false);
                }
            }, 100);
        }
    }

    @OnClick({R.id.ib_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_edit:
                break;
        }
    }

    public static NewsFragment newInstance() {
        Bundle bundle = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
