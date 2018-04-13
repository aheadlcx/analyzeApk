package com.budejie.www.activity.detail;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import com.budejie.www.activity.video.k;
import com.budejie.www.widget.NewTitleView;

class c$2 implements OnScrollListener {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (!this.a.d) {
            return;
        }
        if (i == 1) {
            c.b(this.a, true);
        } else if (i == 0) {
            c.b(this.a, false);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.d) {
            if (k.a(c.c(this.a)).c) {
                k.a(c.c(this.a)).b(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), 1);
            }
            if (c.c(this.a).getRequestedOrientation() != 0) {
                boolean z;
                NewTitleView t;
                if (i == 0) {
                    View childAt = c.q(this.a).getChildAt(0);
                    if (childAt != null && childAt.getTop() == 0) {
                        z = true;
                        if (c.t(this.a) != null) {
                            t = c.t(this.a);
                            z = z && this.a.c;
                            t.a(z);
                        }
                    }
                }
                z = false;
                if (c.t(this.a) != null) {
                    t = c.t(this.a);
                    if (!z) {
                    }
                    t.a(z);
                }
            }
            c.a(this.a, absListView, i2);
            if (absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                c.c(this.a, true);
                if (this.a.b != null) {
                    this.a.b.a(false);
                    return;
                }
                return;
            }
            c.c(this.a, false);
            return;
        }
        c.a(this.a, absListView, i2);
    }
}
