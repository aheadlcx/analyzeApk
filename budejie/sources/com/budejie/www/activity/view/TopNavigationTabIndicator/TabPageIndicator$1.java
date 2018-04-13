package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.View;
import android.view.View.OnClickListener;

class TabPageIndicator$1 implements OnClickListener {
    final /* synthetic */ TabPageIndicator a;

    TabPageIndicator$1(TabPageIndicator tabPageIndicator) {
        this.a = tabPageIndicator;
    }

    public void onClick(View view) {
        TabPageIndicator$c tabPageIndicator$c = (TabPageIndicator$c) view;
        int currentItem = TabPageIndicator.a(this.a).getCurrentItem();
        int a = tabPageIndicator$c.a();
        TabPageIndicator.a(this.a).setCurrentItem(a);
        if (currentItem == a && TabPageIndicator.b(this.a) != null) {
            TabPageIndicator.b(this.a).a(a);
        }
        if (TabPageIndicator.c(this.a) != null) {
            TabPageIndicator.c(this.a).a(a);
        }
    }
}
