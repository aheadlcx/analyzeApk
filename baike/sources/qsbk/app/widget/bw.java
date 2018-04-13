package qsbk.app.widget;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class bw implements OnTouchListener {
    final /* synthetic */ GroupRecommendQiushiCell a;

    bw(GroupRecommendQiushiCell groupRecommendQiushiCell) {
        this.a = groupRecommendQiushiCell;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.a.b.dispatchTouchEvent(motionEvent);
    }
}
