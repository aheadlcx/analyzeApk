package cn.v6.sixrooms.widgets.phone;

import android.support.v4.view.ViewPager.OnPageChangeListener;

final class g implements OnPageChangeListener {
    final /* synthetic */ ExpressionKeyboard a;

    g(ExpressionKeyboard expressionKeyboard) {
        this.a = expressionKeyboard;
    }

    public final void onPageSelected(int i) {
        ExpressionKeyboard.a(this.a, i);
        if (i == 0) {
            this.a.interrupt = false;
        } else {
            this.a.interrupt = true;
        }
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
