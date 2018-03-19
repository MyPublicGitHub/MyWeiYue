package com.example.myweiyue.ui.video;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myweiyue.R;
import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.ui.base.BaseFragment;
import com.example.myweiyue.ui.news.NewsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    public static VideoFragment newInstance(){
        Bundle bundle = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_video;
    }
}
