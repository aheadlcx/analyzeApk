package qsbk.app.video;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class s implements OnTouchListener {
    final /* synthetic */ VideoCropView a;

    s(VideoCropView videoCropView) {
        this.a = videoCropView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a(view, motionEvent, this.a.l, (this.a.g.getRight() - this.a.n) + view.getWidth(), true);
        return true;
    }
}
