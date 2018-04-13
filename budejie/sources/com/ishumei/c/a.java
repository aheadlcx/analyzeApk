package com.ishumei.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.ishumei.f.c;
import java.util.HashMap;
import java.util.Map;

public class a {
    private static a b = null;
    private Map<Long, Integer> a = new HashMap();
    private Handler c = null;
    private HandlerThread d = null;
    private HandlerThread e = null;
    private Handler f = null;
    private Handler g = null;

    private a() {
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public int a() {
        return ((Integer) this.a.get(Long.valueOf(Thread.currentThread().getId()))).intValue();
    }

    public void a(Runnable runnable, int i, boolean z, long j, boolean z2) {
        switch (i) {
            case 1:
                if (z2) {
                    this.f.removeCallbacks(runnable);
                }
                if (z) {
                    this.f.postAtFrontOfQueue(runnable);
                    return;
                } else {
                    this.f.postDelayed(runnable, j);
                    return;
                }
            case 2:
                if (z2) {
                    this.g.removeCallbacks(runnable);
                }
                if (z) {
                    this.g.postAtFrontOfQueue(runnable);
                    return;
                } else {
                    this.g.postDelayed(runnable, j);
                    return;
                }
            case 3:
                if (z2) {
                    this.c.removeCallbacks(runnable);
                }
                if (z) {
                    this.c.postAtFrontOfQueue(runnable);
                    return;
                } else {
                    this.c.postDelayed(runnable, j);
                    return;
                }
            default:
                c.d("TaskExecutor", "execute failed: known thread flag.");
                return;
        }
    }

    public void c() {
        this.c = new Handler(Looper.getMainLooper());
        this.d = new HandlerThread("request thread");
        this.e = new HandlerThread("callback thread");
        this.d.start();
        this.e.start();
        this.f = new Handler(this.d.getLooper());
        this.g = new Handler(this.e.getLooper());
        this.a.put(Long.valueOf(this.c.getLooper().getThread().getId()), Integer.valueOf(3));
        this.a.put(Long.valueOf(this.f.getLooper().getThread().getId()), Integer.valueOf(1));
        this.a.put(Long.valueOf(this.g.getLooper().getThread().getId()), Integer.valueOf(2));
    }
}
