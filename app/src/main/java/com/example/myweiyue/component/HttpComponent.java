package com.example.myweiyue.component;


import com.example.myweiyue.ui.news.NewsDetailFragment;
import com.example.myweiyue.ui.news.NewsFragment;

import dagger.Component;

/**
 * desc: .
 * author: Will .
 * date: 2017/9/2 .
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

//    void inject(VideoFragment videoFragment);

    void inject(NewsDetailFragment newsDetailFragment);
//
//    void inject(JdDetailFragment jdDetailFragment);
//
//    void inject(ImageBrowseActivity imageBrowseActivity);
//
//    void inject(VideoDetailFragment detailFragment);
//
//    void inject(ArticleReadActivity articleReadActivity);

    void inject(NewsFragment newsFragment);

}
