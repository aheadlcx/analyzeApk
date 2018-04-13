package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ViewPagerScrollerUtil extends Scroller {
    private static final Interpolator a = new h();
    public boolean noDuration;

    public void setNoDuration(boolean z) {
        this.noDuration = z;
    }

    public ViewPagerScrollerUtil(Context context) {
        this(context, a);
    }

    public ViewPagerScrollerUtil(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        if (this.noDuration) {
            super.startScroll(i, i2, i3, i4, 0);
        } else {
            super.startScroll(i, i2, i3, i4, i5);
        }
    }
}
