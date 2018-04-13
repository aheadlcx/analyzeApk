package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.View;

class TabPageIndicator$2 implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ TabPageIndicator b;

    TabPageIndicator$2(TabPageIndicator tabPageIndicator, View view) {
        this.b = tabPageIndicator;
        this.a = view;
    }

    public void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        TabPageIndicator.a(this.b, null);
    }
}
