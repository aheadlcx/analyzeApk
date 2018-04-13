package com.budejie.www.activity.view;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class TabWidget$1 implements OnPageChangeListener {
    final /* synthetic */ TabWidget a;

    TabWidget$1(TabWidget tabWidget) {
        this.a = tabWidget;
    }

    public void onPageSelected(int i) {
        if (TabWidget.a(this.a) != 0) {
            if (i == (Integer.MAX_VALUE - (Integer.MAX_VALUE % TabWidget.a(this.a))) - (TabWidget.a(this.a) * 100) || i == TabWidget.a(this.a) * 100) {
                TabWidget.b(this.a);
                return;
            }
            TabWidget.a(this.a, i);
            TabPageIndicator e = TabWidget.e(this.a);
            int c = TabWidget.c(this.a);
            int a = (TabWidget.a(this.a) == TabWidget.d(this.a) || TabWidget.d(this.a) == 0) ? TabWidget.a(this.a) : TabWidget.d(this.a);
            e.setSelectIndicator(c % a);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }
}
