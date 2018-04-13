package cn.v6.sixrooms.widgets.phone;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class j implements OnTouchListener {
    final /* synthetic */ FullScreenChatPage a;

    j(FullScreenChatPage fullScreenChatPage) {
        this.a = fullScreenChatPage;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.a.m != null) {
            this.a.m.onTouch(motionEvent);
        }
        return false;
    }
}
