package cn.dreamtobe.kpswitch.b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class e {
    public static boolean a(View view, int i) {
        if (view.isInEditMode()) {
            return false;
        }
        Log.d("ViewUtil", String.format("refresh Height %d %d", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(i)}));
        if (view.getHeight() == i || Math.abs(view.getHeight() - i) == d.a(view.getContext())) {
            return false;
        }
        int b = c.b(view.getContext());
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new LayoutParams(-1, b));
        } else {
            layoutParams.height = b;
            view.requestLayout();
        }
        return true;
    }

    public static boolean a(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) != 0;
    }

    @TargetApi(19)
    public static boolean b(Activity activity) {
        if (VERSION.SDK_INT < 19 || (activity.getWindow().getAttributes().flags & 67108864) == 0) {
            return false;
        }
        return true;
    }

    @TargetApi(16)
    static boolean c(Activity activity) {
        if (VERSION.SDK_INT >= 16) {
            return ((ViewGroup) activity.findViewById(16908290)).getChildAt(0).getFitsSystemWindows();
        }
        return false;
    }
}
