package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.metok.geofencing.a;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.l;

public class g {
    private final int a = -1;
    private final double b = 0.0d;
    private a c;
    private Context d;

    public g(Context context) {
        this.d = context;
        a();
    }

    private void a() {
        this.c = new a(this.d);
    }

    public void a(String str) {
        this.c.a(this.d, "com.xiaomi.xmsf", str);
    }

    public boolean a(j jVar) {
        if (jVar == null) {
            return false;
        }
        if (jVar.m() != null && jVar.o() > 0.0d) {
            l m = jVar.m();
            this.c.a(this.d, m.c(), m.a(), (float) jVar.o(), -1, "com.xiaomi.xmsf", jVar.a(), jVar.s().name());
        }
        return true;
    }
}
