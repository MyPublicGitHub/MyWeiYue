package com.example.myweiyue;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.ui.base.BaseActivity;
import com.example.myweiyue.ui.base.SupportFragment;
import com.example.myweiyue.ui.jandan.JanDanFragment;
import com.example.myweiyue.ui.news.NewsFragment;
import com.example.myweiyue.ui.personal.PersonalFragment;
import com.example.myweiyue.ui.video.VideoFragment;
import com.example.myweiyue.utils.StatusBarUtil;
import com.example.myweiyue.widget.BottomBar;
import com.example.myweiyue.widget.BottomBarTab;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        if (savedInstanceState == null) {
            mFragments[0] = NewsFragment.newInstance();
            mFragments[1] = VideoFragment.newInstance();
            mFragments[2] = JanDanFragment.newInstance();
            mFragments[3] = PersonalFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,mFragments[0],
                    mFragments[1], mFragments[2], mFragments[3]);
        } else {
            mFragments[0] = findFragment(NewsFragment.class);
            mFragments[1] = findFragment(VideoFragment.class);
            mFragments[2] = findFragment(VideoFragment.class);
            mFragments[3] = findFragment(JanDanFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_news, "新闻"))
                .addItem(new BottomBarTab(this, R.drawable.ic_video, "视频"))
                .addItem(new BottomBarTab(this, R.drawable.ic_jiandan, "煎蛋"))
                .addItem(new BottomBarTab(this, R.drawable.ic_my, "我"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                getSupportDelegate().showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
