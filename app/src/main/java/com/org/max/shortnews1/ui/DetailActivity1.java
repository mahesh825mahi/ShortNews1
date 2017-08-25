package com.org.max.shortnews1.ui;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.Toast;
import com.org.max.shortnews1.R;
import com.org.max.shortnews1.db.NewsEntity;
import com.org.max.shortnews1.ui.home.AddNewsViewModel;
import com.org.max.shortnews1.utils.swipeScreen.SimpleGestureFilter;
import java.util.ArrayList;
import java.util.List;
public class DetailActivity1 extends LifecycleActivity implements SimpleGestureFilter.SimpleGestureListener {
    private String TAG = DetailActivity1.class.getName();
    private SimpleGestureFilter detector;
    private Context ctx;
    private List<NewsEntity> newsList = new ArrayList<>();

    //To get the LiveData
    private AddNewsViewModel getAddNewsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ctx = DetailActivity1.this;
        // Detect touched area
        detector = new SimpleGestureFilter(this, this);

        // Pass results to ViewPagerAdapter Class

        //Live Data
        getAddNewsViewModel = ViewModelProviders.of(this).get(AddNewsViewModel.class);

        getAddNewsViewModel.getItemAndPersonList().observe(DetailActivity1.this, new android.arch.lifecycle.Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> itemAndPeople) {
                newsList.clear();
                newsList.addAll(itemAndPeople);


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT:
                //Intent in = new Intent(this, SettingsActivity.class);
                //startActivity(in);
                onBackPressed();
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                //Intent inte = new Intent(this, DetailsActivity.class);
                //startActivity(inte);
                //finish();
                //overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP:
                str = "Swipe Up";

                break;
        }
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }
}