package cn.v6.sixrooms.widgets.phone.indicator;

import android.view.View;

final class c implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ TabPageIndicator b;

    c(TabPageIndicator tabPageIndicator, View view) {
        this.b = tabPageIndicator;
        this.a = view;
    }

    public final void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        this.b.d = null;
    }
}
