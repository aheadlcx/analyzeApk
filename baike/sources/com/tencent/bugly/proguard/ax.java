package com.tencent.bugly.proguard;

import com.tencent.bugly.proguard.y.a;

final class ax implements Runnable {
    private /* synthetic */ String a;

    ax(String str) {
        this.a = str;
    }

    public final void run() {
        synchronized (y.n) {
            try {
                if (y.g == null) {
                    y.g = new a(y.k);
                } else if (y.g.b == null || y.g.b.length() + ((long) y.e.length()) > y.g.e) {
                    y.g.a();
                }
                if (y.g.a) {
                    y.g.a(y.e.toString());
                    y.e.setLength(0);
                } else {
                    y.e.setLength(0);
                    y.e.append(this.a);
                }
                y.f = false;
            } catch (Throwable th) {
            }
        }
    }
}
