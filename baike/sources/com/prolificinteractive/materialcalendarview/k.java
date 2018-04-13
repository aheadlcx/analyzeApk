package com.prolificinteractive.materialcalendarview;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

class k implements PageTransformer {
    final /* synthetic */ MaterialCalendarView a;

    k(MaterialCalendarView materialCalendarView) {
        this.a = materialCalendarView;
    }

    public void transformPage(View view, float f) {
        view.setAlpha((float) Math.sqrt((double) (1.0f - Math.abs(f))));
    }
}
