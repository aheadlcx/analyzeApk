package com.marshalchen.ultimaterecyclerview.swipe;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

class SwipeLayout$e extends SimpleOnGestureListener {
    final /* synthetic */ SwipeLayout a;

    SwipeLayout$e(SwipeLayout swipeLayout) {
        this.a = swipeLayout;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (SwipeLayout.g(this.a) && SwipeLayout.a(this.a, motionEvent)) {
            this.a.j();
        }
        return super.onSingleTapUp(motionEvent);
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (SwipeLayout.h(this.a) != null) {
            View currentBottomView = this.a.getCurrentBottomView();
            View surfaceView = this.a.getSurfaceView();
            if (currentBottomView == null || motionEvent.getX() <= ((float) currentBottomView.getLeft()) || motionEvent.getX() >= ((float) currentBottomView.getRight()) || motionEvent.getY() <= ((float) currentBottomView.getTop()) || motionEvent.getY() >= ((float) currentBottomView.getBottom())) {
                currentBottomView = surfaceView;
            }
            SwipeLayout.h(this.a).a(this.a, currentBottomView == surfaceView);
        }
        return true;
    }
}
