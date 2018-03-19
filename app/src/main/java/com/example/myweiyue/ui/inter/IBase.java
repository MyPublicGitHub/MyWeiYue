package com.example.myweiyue.ui.inter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myweiyue.component.ApplicationComponent;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */

public interface IBase {

    void bindView(View view,Bundle savedInstanceState);

    View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState);

    View getView();

    void initInjector(ApplicationComponent applicationComponent);

    void initData();

    int getContentLayout();
}
