package qsbk.app.video;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class t implements OnTouchListener {
    final /* synthetic */ VideoCropView a;

    t(VideoCropView videoCropView) {
        this.a = videoCropView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a(view, motionEvent, (this.a.f.getLeft() + this.a.n) - view.getWidth(), this.a.m, false);
        return true;
    }
}
