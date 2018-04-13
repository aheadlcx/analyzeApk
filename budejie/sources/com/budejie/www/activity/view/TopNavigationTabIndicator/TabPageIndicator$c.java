package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.View.MeasureSpec;
import android.widget.TextView;

class TabPageIndicator$c extends TextView {
    final /* synthetic */ TabPageIndicator a;
    private int b;

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (TabPageIndicator.d(this.a) > 0 && getMeasuredWidth() > TabPageIndicator.d(this.a)) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(TabPageIndicator.d(this.a), 1073741824), i2);
        }
    }

    public int a() {
        return this.b;
    }
}
