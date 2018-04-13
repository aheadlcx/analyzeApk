package com.baidu.mobads.g;

import android.util.Log;
import com.baidu.mobads.openad.c.b;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class m implements IOAdEventListener {
    final /* synthetic */ double a;
    final /* synthetic */ l b;

    m(l lVar, double d) {
        this.b = lVar;
        this.a = d;
    }

    public void run(IOAdEvent iOAdEvent) {
        boolean z = true;
        g.f(this.b.a.b);
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            boolean z2;
            g.a(this.b.a.b, new e((String) iOAdEvent.getData().get(b.EVENT_MESSAGE)));
            double a = com.baidu.mobads.a.b.a();
            float f = g.g(this.b.a.b).getFloat("__badApkVersion__8.7018", 0.0f);
            if (((float) g.h(this.b.a.b).b()) == f) {
                z2 = true;
            } else {
                z2 = false;
            }
            Boolean valueOf = Boolean.valueOf(z2);
            if (a > g.h(this.b.a.b).b() || Math.floor(a) != Math.floor(g.h(this.b.a.b).b())) {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            Log.i("XAdApkLoader", "try to download apk badVer=" + f + ", isBad=" + valueOf + ", compatible=" + valueOf2);
            if (this.a < g.h(this.b.a.b).b() && g.h(this.b.a.b) != null && g.h(this.b.a.b).a().booleanValue() && valueOf2.booleanValue() && !valueOf.booleanValue()) {
                g.b(this.b.a.b, g.h(this.b.a.b));
            } else if (g.c(this.b.a.b)) {
                g.a(this.b.a.b, false);
                g.a(this.b.a.b, false, "Refused to download remote for version...");
            }
        } else if (g.c(this.b.a.b)) {
            g.a(this.b.a.b, false);
            g.a(this.b.a.b, false, "remote update Network access failed");
        }
    }
}
