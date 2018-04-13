package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

public class SixroomLayout extends LoadingLayout {
    private AnimationDrawable animationDrawable;
    private boolean isFooterPlayAnim = false;
    private RotateAnimation mFooterFlipAnim;
    private RotateAnimation mFooterReverseFlipAnim;

    public SixroomLayout(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        this.mLoadingView.setBackgroundResource(R.drawable.sixroom_loading);
        this.animationDrawable = (AnimationDrawable) this.mLoadingView.getBackground();
        this.mFooterFlipAnim = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.mFooterFlipAnim.setInterpolator(new LinearInterpolator());
        this.mFooterFlipAnim.setDuration(250);
        this.mFooterFlipAnim.setFillAfter(true);
        this.mFooterReverseFlipAnim = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mFooterReverseFlipAnim.setInterpolator(new LinearInterpolator());
        this.mFooterReverseFlipAnim.setDuration(250);
        this.mFooterReverseFlipAnim.setFillAfter(true);
    }

    protected int getDefaultDrawableResId() {
        return R.drawable.down_001;
    }

    protected void onLoadingDrawableSet(Drawable drawable) {
    }

    protected void onPullImpl(float f) {
        if (this.mPullView != null) {
            this.mPullView.setCurrentProgress(f);
        }
        if (f < 1.0f && this.isFooterPlayAnim) {
            this.mUpImageView.startAnimation(this.mFooterReverseFlipAnim);
            this.isFooterPlayAnim = false;
            this.mUpTextView.setText("上拉加载更多");
            this.mUpProgress.setVisibility(4);
            this.mUpImageView.setVisibility(0);
        }
    }

    protected void pullToRefreshImpl() {
    }

    protected void refreshingImpl() {
        this.mPullView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        this.animationDrawable.start();
        this.mUpImageView.clearAnimation();
        this.mUpImageView.setVisibility(4);
        this.mUpTextView.setText("加载中...");
        this.mUpProgress.setVisibility(0);
    }

    protected void releaseToRefreshImpl() {
        this.mUpImageView.startAnimation(this.mFooterFlipAnim);
        this.mUpTextView.setText("松开即可加载");
        this.isFooterPlayAnim = true;
    }

    protected void resetImpl() {
        this.mLoadingView.setVisibility(0);
        if (this.animationDrawable != null && this.animationDrawable.isRunning()) {
            this.mLoadingView.clearAnimation();
            this.animationDrawable.stop();
        }
        if (this.mPullView != null) {
            this.mPullView.over();
        }
        this.mLoadingView.setVisibility(8);
        this.mPullView.setVisibility(0);
        this.mUpImageView.setVisibility(0);
        this.mUpTextView.setText("上拉加载更多");
        this.mUpProgress.setVisibility(4);
    }
}
