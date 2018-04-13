package com.budejie.www.activity.labelsubscription;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(11)
final class HorizontalListView$b {
    static {
        if (VERSION.SDK_INT < 11) {
            throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
        }
    }

    public static void a(Scroller scroller, float f) {
        if (scroller != null) {
            scroller.setFriction(f);
        }
    }
}
