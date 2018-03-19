package com.example.myweiyue.ui.jandan;


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
public class JanDanFragment extends BaseFragment {


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
        return R.layout.fragment_jan_dan;
    }

    public static JanDanFragment newInstance() {
        Bundle bundle = new Bundle();
        JanDanFragment fragment = new JanDanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
