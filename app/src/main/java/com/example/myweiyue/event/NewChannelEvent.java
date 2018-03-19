package com.example.myweiyue.event;

import com.example.myweiyue.bean.Channel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 冯涛 on 2018/1/25.
 * E-mail:716774214@qq.com
 * desc:
 */

public class NewChannelEvent {
    public List<Channel> mSelectedData;
    public List<Channel> mUnSelectedData;
    public List<Channel> mAllData;

    /**
     * 添加一个频道名称
     */
    public String mFirstChannelName;

    public NewChannelEvent(List<Channel> channels, String firstChannelName) {
        mAllData = channels;
        mFirstChannelName = firstChannelName;

        mSelectedData = new ArrayList<>();
        mUnSelectedData = new ArrayList<>();
        Iterator<Channel> iterator = channels.iterator();

        while (iterator.hasNext()) {
            Channel channel = iterator.next();
            if (channel.getItemType() == Channel.TYPE_MY || channel.getItemType() == Channel.TYPE_OTHER) {
                iterator.remove();
            } else if (channel.getItemType() == Channel.TYPE_MY_CHANNEL) {
                mSelectedData.add(channel);
            } else {
                mUnSelectedData.add(channel);
            }
        }
    }
}
