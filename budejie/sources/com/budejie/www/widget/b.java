package com.budejie.www.widget;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;

public class b implements OnScrollListener {
    private static final String a = b.class.getSimpleName();
    private final CarouselContainer b;
    private final int c;
    private boolean d;
    private a e;
    private boolean f;

    public interface a {
        void a();
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView.getAdapter() != null && this.f && absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1 && this.e != null) {
            this.f = false;
            this.e.a();
        }
        if (this.b != null && !this.b.a()) {
            if (i != 0) {
                if (this.d) {
                    this.b.b(this.c, (float) (-this.b.getAllowedVerticalScrollLength()));
                }
            } else if (absListView.getChildAt(i) != null) {
                this.b.b(this.c, Math.max((float) absListView.getChildAt(i).getTop(), (float) (-this.b.getAllowedVerticalScrollLength())));
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.d = false;
                Log.i(a, "SCROLL_STATE_IDLE");
                return;
            case 1:
                this.d = true;
                Log.i(a, "SCROLL_STATE_TOUCH_SCROLL");
                return;
            case 2:
                this.d = true;
                Log.i(a, "SCROLL_STATE_FLING");
                return;
            default:
                return;
        }
    }
}
