package cn.v6.sixrooms.widgets.phone.photoview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

final class a extends SimpleOnGestureListener {
    final /* synthetic */ PhotoViewAttacher a;

    a(PhotoViewAttacher photoViewAttacher) {
        this.a = photoViewAttacher;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        if (this.a.r != null) {
            this.a.r.onLongClick((View) this.a.f.get());
        }
    }
}
