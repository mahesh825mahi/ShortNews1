package com.org.max.shortnews1.ui.home;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.org.max.shortnews1.db.NewsDatabase;
import com.org.max.shortnews1.db.NewsEntity;
import java.util.List;
public class AddNewsViewModel extends AndroidViewModel {

    private NewsDatabase appDatabase;
    private final LiveData<List<NewsEntity>> newsList;

    public AddNewsViewModel(Application application) {
        super(application);

        appDatabase = NewsDatabase.getDatabase(this.getApplication());
        newsList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<NewsEntity>> getItemAndPersonList() {
        return newsList;
    }

    public void addBorrow(final List<NewsEntity> newsEntityList) {
        new addAsyncTask(appDatabase, newsEntityList).execute("");

    }

    private static class addAsyncTask extends AsyncTask<String, Void, Void> {
        private NewsDatabase db;
        private List<NewsEntity> newsEntityList;

        addAsyncTask(NewsDatabase appDatabase, List<NewsEntity> newsEntityList) {
            this.db = appDatabase;
            this.newsEntityList = newsEntityList;
        }

        @Override
        protected Void doInBackground(final String... value) {
            db.itemAndPersonModel().addBorrow(newsEntityList);
            return null;
        }

    }
}
