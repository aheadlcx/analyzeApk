package com.prolificinteractive.materialcalendarview;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class j implements OnPageChangeListener {
    final /* synthetic */ MaterialCalendarView a;

    j(MaterialCalendarView materialCalendarView) {
        this.a = materialCalendarView;
    }

    public void onPageSelected(int i) {
        this.a.e.setPreviousMonth(this.a.p);
        this.a.p = this.a.o.getItem(i);
        this.a.c();
        this.a.a(this.a.p);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }
}
