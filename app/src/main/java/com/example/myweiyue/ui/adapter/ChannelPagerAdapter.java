package com.example.myweiyue.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myweiyue.bean.Channel;
import com.example.myweiyue.ui.base.BaseFragment;
import com.example.myweiyue.ui.news.NewsDetailFragment;

import java.util.List;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 */

public class ChannelPagerAdapter extends FragmentStatePagerAdapter {

    private List<Channel> mChannels;

    public ChannelPagerAdapter(FragmentManager fm, List<Channel> channels) {
        super(fm);
        mChannels = channels;
    }

    public void updateChannel(List<Channel> channels) {
        mChannels = channels;
        notifyDataSetChanged();
    }

    @Override
    public BaseFragment getItem(int position) {
        return NewsDetailFragment.newInstance(mChannels.get(position).getChannelId(), position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).getChannelName();
    }

    @Override
    public int getCount() {
        return mChannels == null ? 0 : mChannels.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
