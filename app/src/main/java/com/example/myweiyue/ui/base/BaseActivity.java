package com.example.myweiyue.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myweiyue.R;
import com.example.myweiyue.app.MyApp;
import com.example.myweiyue.ui.inter.IBase;
import com.example.myweiyue.utils.DialogHelper;
import com.example.myweiyue.utils.StatusBarUtil;
import com.example.myweiyue.utils.ToastUtils;
import com.example.myweiyue.widget.MultiStateView;
import com.example.myweiyue.widget.SimpleMultiStateView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by 冯涛 on 2018/1/20.
 * E-mail:716774214@qq.com
 */

public abstract class BaseActivity<T1 extends BaseContract.BasePresenter>
        extends SupportActivity
        implements IBase, BaseContract.BaseView, BGASwipeBackHelper.Delegate {

    protected View mRootView;
    protected Dialog mLoadingDialog = null;
    Unbinder unbinder;

    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    @Nullable
    @Inject
    protected T1 mPresenter;
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        initInjector(MyApp.getInstance().getApplicationComponent());
        attachView();
        bindView(mRootView, savedInstanceState);
        initStateView();
        initData();
        mLoadingDialog = DialogHelper.getLoadingDialog(this);
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(getContentLayout(), viewGroup);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
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

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        //必须在Application的onCreate方法中执行BGASwipeBackManager.getInstance().init(this)来初始化
        // 下面几项可以不配置，这里只是为了讲述接口用法。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(false);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    /**
     * 时候支持滑动返回，可以在子类中重写该方法  关闭滑动返回
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 设置状态栏的颜色和透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
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

    protected SimpleMultiStateView getStateView() {
        return mSimpleMultiStateView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
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
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }
}
