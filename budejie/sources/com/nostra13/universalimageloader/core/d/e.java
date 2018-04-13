package com.nostra13.universalimageloader.core.d;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.nostra13.universalimageloader.core.d;

public class e implements OnScrollListener {
    private d a;
    private final boolean b;
    private final boolean c;
    private final OnScrollListener d;

    public e(d dVar, boolean z, boolean z2, OnScrollListener onScrollListener) {
        this.a = dVar;
        this.b = z;
        this.c = z2;
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.a.h();
                this.a.k();
                break;
            case 1:
                if (this.b) {
                    this.a.g();
                }
                this.a.j();
                break;
            case 2:
                if (this.c) {
                    this.a.g();
                }
                this.a.j();
                break;
        }
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }
}
