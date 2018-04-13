package cn.xiaochuan.push;

import android.os.Handler;
import android.os.HandlerThread;

public final class c {
    private static volatile a a;

    public static class a extends HandlerThread {
        final Handler a;

        private a(String str, int i) {
            super(str, i);
            start();
            this.a = new Handler(getLooper());
        }

        public void a(Runnable runnable) {
            this.a.post(runnable);
        }

        public void a(Runnable runnable, long j) {
            this.a.postDelayed(runnable, j);
        }

        public void b(Runnable runnable) {
            this.a.removeCallbacks(runnable);
        }
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a("Background", 10);
                }
            }
        }
        return a;
    }
}
