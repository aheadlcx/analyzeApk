package com.budejie.www.adapter.g.b;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class b$3 implements OnPageChangeListener {
    final /* synthetic */ b a;

    b$3(b bVar) {
        this.a = bVar;
    }

    public void onPageSelected(int i) {
        this.a.d.setSelectIndicator(i);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }
}
