package com.org.max.shortnews1.ui.home;
import android.content.Context;
import com.google.gson.Gson;
import com.org.max.shortnews1.db.NewsEntity;
import com.org.max.shortnews1.model.News.News;
import com.org.max.shortnews1.network.ApiClient;
import com.org.max.shortnews1.utils.LogUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
/**
 * Created by Maheswaran on 8/10/2017.
 */
public class HomePresenter implements IHomePresenter {
    IHomeView homeView;
    Context ctx;
    //RxJava
    private Subscription subscription;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getNews() {

        homeView.showProgress();
        subscription = ApiClient.getInstance()
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtils.LOGI(TAG, "--->" + e.toString());
                        homeView.getNewsFailed("onError");
                        homeView.hideProgress();
                    }

                    @Override
                    public void onNext(List<News> newsList) {
                        homeView.hideProgress();
                        final List<NewsEntity> newsEntityList = new ArrayList<NewsEntity>();
                        for (News news : newsList) {
                            newsEntityList.add(new NewsEntity(news.getId(), news.getSubject(), news.getMessage(), Calendar.getInstance().getTime()));
                        }
                        homeView.getNewsSuccess(newsEntityList);

                        System.out.println(TAG + "--->" + new Gson().toJson(newsList));
                    }
                });
    }

    @Override
    public void destory() {
        this.homeView = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
