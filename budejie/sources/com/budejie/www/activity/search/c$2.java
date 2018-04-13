package com.budejie.www.activity.search;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.budejie.www.util.an;

class c$2 implements OnTouchListener {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            an.b(this.a.getActivity());
        }
        return false;
    }
}
