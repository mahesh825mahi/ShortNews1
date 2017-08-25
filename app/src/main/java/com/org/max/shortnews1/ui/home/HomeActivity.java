package com.org.max.shortnews1.ui.home;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.org.max.shortnews1.R;
import com.org.max.shortnews1.adapter.NewsAdapter;
import com.org.max.shortnews1.db.NewsEntity;
import com.org.max.shortnews1.ui.detail.DetailActivity;
import com.org.max.shortnews1.utils.Connectivity;
import com.org.max.shortnews1.utils.ToastUtils;
import com.org.max.shortnews1.utils.helper.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
public class HomeActivity extends LifecycleActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener, NewsAdapter.MessageAdapterListener, IHomeView {
    private String TAG = HomeActivity.class.getName();
    private Context ctx;

    private RecyclerView recyclerView;
    private List<NewsEntity> newsList = new ArrayList<>();
    private NewsAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomePresenter homePresenter;
    //To get the LiveData
    private AddNewsViewModel getAddNewsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ctx = HomeActivity.this;

        homePresenter = new HomePresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mAdapter = new NewsAdapter(this, newsList, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Live Data
        getAddNewsViewModel = ViewModelProviders.of(this).get(AddNewsViewModel.class);
        getAddNewsViewModel.getItemAndPersonList().observe(HomeActivity.this, new android.arch.lifecycle.Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> itemAndPeople) {
                newsList.clear();
                newsList.addAll(itemAndPeople);
                mAdapter.notifyDataSetChanged();
            }
        });

        //Start
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        getNews();
                                    }
                                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        /*if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }*/

        homePresenter.destory();

        super.onDestroy();
    }

    private void getNews() {

        if (Connectivity.isConnected(ctx)) {
            homePresenter.getNews();
        } else {
            hideProgress();
            ToastUtils.showToast(ctx, getString(R.string.please_check_your_internet_connection));
        }
    }

    @Override
    public void onRefresh() {
        getNews();
    }

    @Override
    public void onIconClicked(int position) {

    }

    @Override
    public void onIconImportantClicked(int position) {

    }

    @Override
    public void onMessageRowClicked(int position) {
        startActivity(new Intent(this, DetailActivity.class));
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public void onRowLongClicked(int position) {

    }


    @Override
    public void getNewsSuccess(List<NewsEntity> newsEntityList) {
        getAddNewsViewModel.addBorrow(newsEntityList);
    }

    @Override
    public void getNewsFailed(String failed) {
        ToastUtils.showToast(this, failed);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void populateData() {

    }
}
