package com.org.max.shortnews1.network;
import com.org.max.shortnews1.model.News.News;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
public interface ApiInterface {

    @GET("inbox.json")
    Observable<List<News> > getNews();

}
