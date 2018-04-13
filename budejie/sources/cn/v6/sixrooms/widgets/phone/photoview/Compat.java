package cn.v6.sixrooms.widgets.phone.photoview;

import android.os.Build.VERSION;
import android.view.View;

public class Compat {
    public static void postOnAnimation(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            SDK16.postOnAnimation(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }
}
