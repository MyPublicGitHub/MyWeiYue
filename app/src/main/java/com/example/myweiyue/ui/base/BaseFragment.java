package com.example.myweiyue.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myweiyue.R;
import com.example.myweiyue.app.MyApp;
import com.example.myweiyue.ui.inter.IBase;
import com.example.myweiyue.utils.DialogHelper;
import com.example.myweiyue.utils.ToastUtils;
import com.example.myweiyue.widget.MultiStateView;
import com.example.myweiyue.widget.SimpleMultiStateView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 冯涛 on 2018/1/23.
 * E-mail:716774214@qq.com
 */

public abstract class BaseFragment<T1 extends BaseContract.BasePresenter>
        extends SupportFragment
        implements IBase, BaseContract.BaseView {

    protected Context mContext;
    protected View mRootView;
    protected Dialog mLoadingDialog = null;
    Unbinder unbinder;

    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    @Nullable
    @Inject
    protected T1 mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        } else {
            mRootView = createView(inflater, container, savedInstanceState);
        }
        mContext = mRootView.getContext();
        mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), viewGroup, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInjector(MyApp.getInstance().getApplicationComponent());
        attachView();
        bindView(view, savedInstanceState);
        initStateView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onRetry() {

    }

    protected void showLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    protected void showLoadingDialog(String text) {
        if (mLoadingDialog != null) {
            TextView textView = (TextView) mLoadingDialog.findViewById(R.id.tv_load_dialog);
            textView.setText(text);
            mLoadingDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void initStateView() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                    .setRetryResource(R.layout.view_retry)
                    .setNoNetResource(R.layout.view_no_net)
                    .build()
                    .setonReLoadListener(new MultiStateView.onReLoadListener() {
                        @Override
                        public void onReload() {
                            onRetry();
                        }
                    });
        }
    }

    @Override
    public void showLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void showSuccess() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void showFailed() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    protected void T(String msg) {
        ToastUtils.showShort(MyApp.getContext(), msg);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
