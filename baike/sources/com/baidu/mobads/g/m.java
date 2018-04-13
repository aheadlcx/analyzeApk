package com.baidu.mobads.g;

import android.util.Log;
import com.baidu.mobads.a.b;
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
        this.b.a.b.m();
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            boolean z2;
            this.b.a.b.k = new e((String) iOAdEvent.getData().get("message"));
            double a = b.a();
            float f = this.b.a.b.j().getFloat("__badApkVersion__8.7004", 0.0f);
            if (((float) this.b.a.b.k.b()) == f) {
                z2 = true;
            } else {
                z2 = false;
            }
            Boolean valueOf = Boolean.valueOf(z2);
            if (a > this.b.a.b.k.b() || Math.floor(a) != Math.floor(this.b.a.b.k.b())) {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            Log.i("XAdApkLoader", "try to download apk badVer=" + f + ", isBad=" + valueOf + ", compatible=" + valueOf2);
            if (this.a < this.b.a.b.k.b() && this.b.a.b.k != null && this.b.a.b.k.a().booleanValue() && valueOf2.booleanValue() && !valueOf.booleanValue()) {
                this.b.a.b.a(this.b.a.b.k);
            } else if (this.b.a.b.n) {
                this.b.a.b.n = false;
                this.b.a.b.a(false, "Refused to download remote for version...");
            }
        } else if (this.b.a.b.n) {
            this.b.a.b.n = false;
            this.b.a.b.a(false, "remote update Network access failed");
        }
    }
}
