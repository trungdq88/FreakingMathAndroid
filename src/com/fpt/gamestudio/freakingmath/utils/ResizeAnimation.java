package com.fpt.gamestudio.freakingmath.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ThaoHQSE60963 on 4/2/14.
 */
public class ResizeAnimation extends Animation {
    private View mView;
    private float mToHeight;
    private float mFromHeight;

    private float mToWidth;
    private float mFromWidth;

    private int mTimeAnimation;

    private AtomicBoolean flag;

    public ResizeAnimation(View v, float fromWidth, float fromHeight, float toWidth, float toHeight, int timeAnimation, AtomicBoolean flag) {
        mToHeight = toHeight;
        mToWidth = toWidth;
        mFromHeight = fromHeight;
        mFromWidth = fromWidth;
        mView = v;
        this.flag = flag;
        mTimeAnimation = timeAnimation;
        setDuration(timeAnimation);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float height =
                (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
        float width = (mToWidth - mFromWidth) * interpolatedTime + mFromWidth;
        ViewGroup.LayoutParams p = mView.getLayoutParams();
        p.height = (int) height;
        p.width = (int) width;
        if (interpolatedTime == mTimeAnimation) {

        }
        mView.requestLayout();
    }
}