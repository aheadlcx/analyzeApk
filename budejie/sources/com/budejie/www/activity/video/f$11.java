package com.budejie.www.activity.video;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class f$11 implements OnTouchListener {
    final /* synthetic */ f a;

    f$11(f fVar) {
        this.a = fVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 6 && k.a(f.e(this.a)).f != null) {
            k.a(f.e(this.a)).f.p();
        }
        return false;
    }
}
