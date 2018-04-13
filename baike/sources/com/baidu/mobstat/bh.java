package com.baidu.mobstat;

import android.app.Activity;
import android.view.View;
import android.view.View.AccessibilityDelegate;

class bh extends AccessibilityDelegate {
    final /* synthetic */ bf a;
    private AccessibilityDelegate b;
    private Activity c;
    private View d;
    private String e;

    public bh(bf bfVar, Activity activity, View view, String str, AccessibilityDelegate accessibilityDelegate) {
        this.a = bfVar;
        this.b = accessibilityDelegate;
        this.c = activity;
        this.d = view;
        this.e = str;
    }

    public void sendAccessibilityEvent(View view, int i) {
        if (view == this.d && i == 1) {
            this.a.b(this.c, this.d, this.e);
        }
        if (this.b != null) {
            this.b.sendAccessibilityEvent(view, i);
        }
    }
}
