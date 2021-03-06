package com.org.max.shortnews1.utils.vertViewPager;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.org.max.shortnews1.utils.vertViewPager.transforms.DefaultTransformer;
public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        this(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new DefaultTransformer());
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;

        event.setLocation(swappedX, swappedY);

        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(event));
        //If not intercept, touch event should not be swapped.
        swapTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }
}
