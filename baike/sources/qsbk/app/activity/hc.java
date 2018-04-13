package qsbk.app.activity;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class hc implements OnGlobalLayoutListener {
    final /* synthetic */ CircleTopicActivity a;

    hc(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onGlobalLayout() {
        this.a.b.setExtraScrollHeight(-this.a.Z.getMeasuredHeight());
    }
}
