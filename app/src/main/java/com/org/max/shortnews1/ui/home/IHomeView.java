package com.org.max.shortnews1.ui.home;

import com.org.max.shortnews1.db.NewsEntity;
import com.org.max.shortnews1.model.News.News;

import java.util.List;

/**
 * Created by Maheswaran on 8/10/2017.
 */

public interface IHomeView {

    void getNewsSuccess(List<NewsEntity> newsEntityList);
    void getNewsFailed(String failed);
    void showProgress();
    void hideProgress();
}
