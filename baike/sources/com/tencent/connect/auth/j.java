package com.tencent.connect.auth;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class j implements OnTouchListener {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 1:
                if (!view.hasFocus()) {
                    view.requestFocus();
                    break;
                }
                break;
        }
        return false;
    }
}
