package com.yalantis.ucrop.view;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

class GestureCropImageView$ScaleListener extends SimpleOnScaleGestureListener {
    final /* synthetic */ GestureCropImageView this$0;

    private GestureCropImageView$ScaleListener(GestureCropImageView gestureCropImageView) {
        this.this$0 = gestureCropImageView;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        this.this$0.postScale(scaleGestureDetector.getScaleFactor(), GestureCropImageView.access$300(this.this$0), GestureCropImageView.access$400(this.this$0));
        return true;
    }
}
