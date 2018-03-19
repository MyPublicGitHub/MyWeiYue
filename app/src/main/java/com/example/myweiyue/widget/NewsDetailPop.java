package com.example.myweiyue.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.myweiyue.R;
import com.flyco.dialog.widget.popup.base.BasePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 冯涛 on 2018/1/25.
 * E-mail:716774214@qq.com
 * desc:
 */

public class NewsDetailPop extends BasePopup<NewsDetailPop> {
    private static final String TAG = "NewsDetailPop";
    @BindView(R.id.ivUp)
    ImageView ivUp;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvNoLike)
    TextView tvNoLike;
    @BindView(R.id.ivDown)
    ImageView ivDown;

    List<String> mBackReason;
    List<Integer> mSelected;
    private int mPosition;
    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    public NewsDetailPop(Context context) {
        super(context);
    }

    public NewsDetailPop setBaceReason(List<String> backReason, boolean isTop, int position) {
        mBackReason = backReason;
        mPosition = position;
        mSelected = new ArrayList<>();
        mSelected.clear();
        if (mAdapter != null) {
            mAdapter.setNewData(backReason);
        }
        if (isTop) {
            ivUp.setVisibility(View.GONE);
            ivDown.setVisibility(View.VISIBLE);
        } else {
            ivUp.setVisibility(View.VISIBLE);
            ivDown.setVisibility(View.GONE);
        }
        return this;
    }

    @Override
    public View onCreatePopupView() {
        View view = View.inflate(mContext, R.layout.pop_news_detail, null);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_news_detail_pop_detail, mBackReason) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tvBackReason, item);
                if (mSelected.contains(helper.getAdapterPosition())) {
                    helper.getView(R.id.tvBackReason).setBackground(mContext.getResources().getDrawable(R.drawable.pop_tv_selected_bg));
                    helper.setTextColor(R.id.tvBackReason, mContext.getResources().getColor(android.R.color.holo_red_light));
                } else {
                    helper.getView(R.id.tvBackReason).setBackground(mContext.getResources().getDrawable(R.drawable.pop_tv_bg));
                    helper.setTextColor(R.id.tvBackReason, mContext.getResources().getColor(android.R.color.black));
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSelected.contains(position)) {
                    mSelected.remove(position);
                } else {
                    mSelected.add(position);
                }
                if (mSelected.size() > 0) {
                    tvNoLike.setText("确定");
                } else {
                    tvNoLike.setText("不感兴趣");
                }
                mAdapter.notifyItemChanged(position);
            }
        });
        return view;
    }

    @Override
    public void setUiBeforShow() {

    }

    @OnClick(R.id.tvNoLike)
    public void onViewClicked() {
        mOnClickListener.onClick(mPosition);
    }
}
