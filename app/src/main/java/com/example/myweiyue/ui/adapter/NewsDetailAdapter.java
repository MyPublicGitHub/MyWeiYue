package com.example.myweiyue.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myweiyue.R;
import com.example.myweiyue.bean.NewsDetailBean;
import com.example.myweiyue.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 * desc:
 */

public class NewsDetailAdapter extends BaseMultiItemQuickAdapter<NewsDetailBean.ItemBean, BaseViewHolder> {

    private Context mContext;

    public NewsDetailAdapter(List<NewsDetailBean.ItemBean> data, Context context) {
        super(data);
        mContext = context;
        addItemType(NewsDetailBean.ItemBean.TYPE_DOC_TITLEIMG, R.layout.item_news_detail_doc);
        addItemType(NewsDetailBean.ItemBean.TYPE_DOC_SLIDEIMG, R.layout.item_detail_doc_slideimg);
        addItemType(NewsDetailBean.ItemBean.TYPE_ADVERT_TITLEIMG, R.layout.item_detail_advert);
        addItemType(NewsDetailBean.ItemBean.TYPE_ADVERT_SLIDEIMG, R.layout.item_detail_advert_slideimg);
        addItemType(NewsDetailBean.ItemBean.TYPE_ADVERT_LONGIMG, R.layout.item_detail_advert_longimage);
        addItemType(NewsDetailBean.ItemBean.TYPE_SLIDE, R.layout.item_detail_slide);
        addItemType(NewsDetailBean.ItemBean.TYPE_PHVIDEO, R.layout.item_detail_phvideo);
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsDetailBean.ItemBean bean) {
        switch (holder.getItemViewType()) {
            case NewsDetailBean.ItemBean.TYPE_DOC_TITLEIMG:
                holder.setText(R.id.tv_title, bean.title);
                holder.setText(R.id.tv_source, bean.source);
                holder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_comment_size), bean.commentsall));
                ImageLoaderUtil.LoadImage(mContext, bean.thumbnail, (ImageView) holder.getView(R.id.iv_logo));
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_DOC_SLIDEIMG:
                holder.setText(R.id.tv_title, bean.title);
                holder.setText(R.id.tv_source, bean.source);
                holder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_comment_size), bean.commentsall));
                try {
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(0), (ImageView) holder.getView(R.id.iv_1));
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(1), (ImageView) holder.getView(R.id.iv_2));
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(2), (ImageView) holder.getView(R.id.iv_3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_ADVERT_TITLEIMG:
                holder.setText(R.id.tv_title, bean.title);
                ImageLoaderUtil.LoadImage(mContext, bean.thumbnail, (ImageView) holder.getView(R.id.iv_logo));
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
                holder.setText(R.id.tv_title, bean.title);
                try {
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(0), (ImageView) holder.getView(R.id.iv_1));
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(1), (ImageView) holder.getView(R.id.iv_2));
                    ImageLoaderUtil.LoadImage(mContext, bean.style.images.get(2), (ImageView) holder.getView(R.id.iv_3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_ADVERT_LONGIMG:
                holder.setText(R.id.tv_title, bean.title);
                ImageLoaderUtil.LoadImage(mContext, bean.thumbnail, (ImageView) holder.getView(R.id.iv_logo));
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_SLIDE:
                holder.setText(R.id.tv_title, bean.title);
                holder.setText(R.id.tv_source, bean.source);
                holder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_comment_size), bean.commentsall));
                ImageLoaderUtil.LoadImage(mContext, bean.thumbnail, (ImageView) holder.getView(R.id.iv_logo));
                holder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetailBean.ItemBean.TYPE_PHVIDEO:
                holder.setText(R.id.tv_title, bean.title);
                holder.setText(R.id.tv_source, bean.source);
                holder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_comment_size), bean.commentsall));
                holder.addOnClickListener(R.id.iv_close);
                ImageLoaderUtil.LoadImage(mContext, bean.thumbnail, (ImageView) holder.getView(R.id.iv_logo));
                break;
        }

    }
}
