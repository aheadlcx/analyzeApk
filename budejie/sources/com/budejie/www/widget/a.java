package com.budejie.www.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.budejie.www.util.an;

public class a {
    private Activity a;
    private View b;
    private int c;
    private LayoutParams d = ((LayoutParams) this.b.getLayoutParams());

    public static void a(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            a aVar = new a(activity);
        }
    }

    private a(Activity activity) {
        this.a = activity;
        this.b = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new a$1(this));
    }

    private void a() {
        int b = b();
        if (b != this.c) {
            int height = this.b.getRootView().getHeight();
            int i = height - b;
            if (i > height / 4) {
                this.d.height = height - i;
            } else {
                this.d.height = height;
            }
            this.b.requestLayout();
            this.c = b;
        }
    }

    private int b() {
        Rect rect = new Rect();
        this.b.getWindowVisibleDisplayFrame(rect);
        if ("samsung".equals(Build.MANUFACTURER)) {
            return rect.bottom - rect.top;
        }
        return (rect.bottom - rect.top) + an.t(this.a);
    }
}
