package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class cs implements OnGlobalLayoutListener {
    final /* synthetic */ PagerSlidingTabStrip a;

    cs(PagerSlidingTabStrip pagerSlidingTabStrip) {
        this.a = pagerSlidingTabStrip;
    }

    @SuppressLint({"NewApi"})
    public void onGlobalLayout() {
        if (VERSION.SDK_INT < 16) {
            this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
            this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        PagerSlidingTabStrip.a(this.a, PagerSlidingTabStrip.a(this.a).getCurrentItem());
        PagerSlidingTabStrip.a(this.a, PagerSlidingTabStrip.b(this.a), 0);
    }
}
