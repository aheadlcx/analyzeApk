package com.liulishuo.filedownloader;

import android.content.Context;
import com.liulishuo.filedownloader.d.b;
import com.liulishuo.filedownloader.g.e;

public class m implements t {
    private final t a;

    private static final class a {
        private static final m a = new m();
    }

    public static m a() {
        return a.a;
    }

    public static com.liulishuo.filedownloader.services.e.a b() {
        if (a().a instanceof n) {
            return (com.liulishuo.filedownloader.services.e.a) a().a;
        }
        return null;
    }

    private m() {
        this.a = e.a().d ? new n() : new o();
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, b bVar, boolean z3) {
        return this.a.a(str, str2, z, i, i2, i3, z2, bVar, z3);
    }

    public boolean a(int i) {
        return this.a.a(i);
    }

    public byte b(int i) {
        return this.a.b(i);
    }

    public boolean c() {
        return this.a.c();
    }

    public void a(Context context) {
        this.a.a(context);
    }

    public boolean c(int i) {
        return this.a.c(i);
    }
}
