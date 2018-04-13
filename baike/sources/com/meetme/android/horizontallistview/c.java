package com.meetme.android.horizontallistview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class c implements OnTouchListener {
    final /* synthetic */ HorizontalListView a;

    c(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.a.f.onTouchEvent(motionEvent);
    }
}
