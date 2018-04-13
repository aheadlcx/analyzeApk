package com.budejie.www.activity.labelsubscription;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(14)
final class HorizontalListView$c {
    static {
        if (VERSION.SDK_INT < 14) {
            throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
        }
    }

    public static float a(Scroller scroller) {
        return scroller.getCurrVelocity();
    }
}
