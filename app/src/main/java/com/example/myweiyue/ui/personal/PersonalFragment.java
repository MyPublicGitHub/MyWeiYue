package com.example.myweiyue.ui.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myweiyue.R;
import com.example.myweiyue.component.ApplicationComponent;
import com.example.myweiyue.ui.base.BaseFragment;

/**
 */
public class PersonalFragment extends BaseFragment {

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
        return R.layout.fragment_personal;
    }

    public static PersonalFragment newInstance() {
        Bundle bundle = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
