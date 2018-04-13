package com.baidu.mobads.g;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.mobads.g.g.c;

class n implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ Handler b;
    final /* synthetic */ g c;

    n(g gVar, c cVar, Handler handler) {
        this.c = gVar;
        this.a = cVar;
        this.b = handler;
    }

    public void run() {
        Editor edit;
        try {
            synchronized (g.class) {
                this.c.b(this.a, this.b);
            }
            try {
                edit = this.c.j().edit();
                edit.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            } catch (Throwable th) {
                this.c.m.e("XAdApkLoader", th);
            }
        } catch (Throwable th2) {
            try {
                String str = "Load APK Failed: " + th2.toString();
                this.c.m.e("XAdApkLoader", str);
                this.c.b(false);
                edit = this.c.j().edit();
                edit.putString("previousProxyVersion", this.c.a());
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            } catch (Throwable th22) {
                this.c.m.e("XAdApkLoader", th22);
            }
        }
    }
}
