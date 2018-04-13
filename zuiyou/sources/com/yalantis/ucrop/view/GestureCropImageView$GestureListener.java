package com.yalantis.ucrop.view;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class GestureCropImageView$GestureListener extends SimpleOnGestureListener {
    final /* synthetic */ GestureCropImageView this$0;

    private GestureCropImageView$GestureListener(GestureCropImageView gestureCropImageView) {
        this.this$0 = gestureCropImageView;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.this$0.zoomImageToPosition(this.this$0.getDoubleTapTargetScale(), motionEvent.getX(), motionEvent.getY(), 200);
        return super.onDoubleTap(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.this$0.postTranslate(-f, -f2);
        return true;
    }
}
