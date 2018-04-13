package qsbk.app.live.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class ee implements OnTouchListener {
    final /* synthetic */ LivePushActivity a;

    ee(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.cc.setVisibility(8);
        return false;
    }
}
