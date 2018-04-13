package qsbk.app.live.ui;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

protected class LiveBaseActivity$SingleTapGestureListener extends SimpleOnGestureListener {
    final /* synthetic */ LiveBaseActivity a;

    public LiveBaseActivity$SingleTapGestureListener(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.a.B();
        return true;
    }
}
