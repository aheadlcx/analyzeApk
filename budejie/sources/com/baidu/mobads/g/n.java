package com.baidu.mobads.g;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;

class n implements Runnable {
    final /* synthetic */ g$c a;
    final /* synthetic */ Handler b;
    final /* synthetic */ g c;

    n(g gVar, g$c g_c, Handler handler) {
        this.c = gVar;
        this.a = g_c;
        this.b = handler;
    }

    public void run() {
        Editor edit;
        try {
            synchronized (g.class) {
                g.a(this.c, this.a, this.b);
            }
            try {
                edit = g.g(this.c).edit();
                edit.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            } catch (Throwable th) {
                g.e(this.c).e("XAdApkLoader", th);
            }
        } catch (Throwable th2) {
            try {
                String str = "Load APK Failed: " + th2.toString();
                g.e(this.c).e(new Object[]{"XAdApkLoader", str});
                g.c(this.c, false);
                edit = g.g(this.c).edit();
                edit.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            } catch (Throwable th22) {
                g.e(this.c).e("XAdApkLoader", th22);
            }
        }
    }
}
