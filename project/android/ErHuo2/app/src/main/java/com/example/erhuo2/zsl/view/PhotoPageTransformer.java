package com.example.erhuo2.zsl.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class PhotoPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;

    private float mMinAlpha = 0.3f;
    private float leftScaleY = 0.9f;
    private float rightScaleY = 0.5f;
    private float leftTransY = 0.05f; //(1-0.9)/2
    private float rightTransY = 0.25f;

    public void transformPage(View view, float position) {
        int pageHeight = view.getHeight();
//        LogUtils.e("transformPage position: " + view + "," + position);
        if (position < -1) { // [-Infinity,-1)  This page is way off-screen to the left.
            view.setAlpha(mMinAlpha);
            view.setScaleY(leftScaleY);
            view.setTranslationY(pageHeight * leftTransY);
        } else if (position <= 1) {
            if (position <= 0) { //[-1，0) 左滑  a页滑动至b页：a页从 0.0~-1，b页从1 ~ 0.0
                //1到mMinAlpha
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
                view.setScaleY(leftScaleY + (1 - leftScaleY) * (1 + position));
                //0到leftTransY的变化
                view.setTranslationY(-pageHeight * (leftTransY * position));
            } else //[0,1]  1~0
            {
                //minAlpha到1的变化
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
                view.setScaleY(rightScaleY + (1 - rightScaleY) * (1 - position));
                //rightTransY到0的变化
                view.setTranslationY(pageHeight * (rightTransY * position));
            }
        } else {  // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(mMinAlpha);
            view.setScaleY(rightScaleY);
            view.setTranslationY(pageHeight * rightTransY);
        }
    }

}
