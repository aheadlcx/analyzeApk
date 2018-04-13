package com.budejie.www.activity.labelsubscription;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class HorizontalListView$1 implements OnTouchListener {
    final /* synthetic */ HorizontalListView a;

    HorizontalListView$1(HorizontalListView horizontalListView) {
        this.a = horizontalListView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return HorizontalListView.a(this.a).onTouchEvent(motionEvent);
    }
}
