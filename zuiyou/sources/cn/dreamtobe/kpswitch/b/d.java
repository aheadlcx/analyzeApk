package cn.dreamtobe.kpswitch.b;

import android.content.Context;
import android.util.Log;

public class d {
    private static boolean a = false;
    private static int b = 50;

    public static synchronized int a(Context context) {
        int identifier;
        synchronized (d.class) {
            if (!a) {
                identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (identifier > 0) {
                    b = context.getResources().getDimensionPixelSize(identifier);
                    a = true;
                    Log.d("StatusBarHeightUtil", String.format("Get status bar height %d", new Object[]{Integer.valueOf(b)}));
                }
            }
            identifier = b;
        }
        return identifier;
    }
}
