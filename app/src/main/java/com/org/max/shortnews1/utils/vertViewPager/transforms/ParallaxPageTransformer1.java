package com.org.max.shortnews1.utils.vertViewPager.transforms;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Maheswaran on 8/3/2017.
 */

public class ParallaxPageTransformer1 implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1);

        } else if (position <= 1) { // [-1,1]

            view.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1);
        }


    }
}