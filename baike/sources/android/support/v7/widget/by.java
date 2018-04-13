package android.support.v7.widget;

import android.view.View;

class by implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ ScrollingTabContainerView b;

    by(ScrollingTabContainerView scrollingTabContainerView, View view) {
        this.b = scrollingTabContainerView;
        this.a = view;
    }

    public void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        this.b.a = null;
    }
}
