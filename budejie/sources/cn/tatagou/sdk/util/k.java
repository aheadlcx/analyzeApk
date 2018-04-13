package cn.tatagou.sdk.util;

import android.os.Handler;

public class k {
    public static void runRunnable(Handler handler, Runnable runnable, long j) {
        if (handler != null && runnable != null) {
            handler.postDelayed(runnable, j);
        }
    }

    public static void closeRunnable(Handler handler, Runnable runnable) {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
