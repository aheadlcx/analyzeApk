package com.loc;

import android.content.Context;
import java.util.List;

final class dh implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    dh(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public final void run() {
        try {
            af afVar = new af(this.a, bb.b());
            List<bc> b = afVar.b(bc.a(this.b), bc.class);
            if (b != null && b.size() > 0) {
                for (bc bcVar : b) {
                    if (!this.c.equalsIgnoreCase(bcVar.d())) {
                        ay.c(this.a, afVar, bcVar.a());
                    }
                }
            }
        } catch (Throwable th) {
            w.a(th, "FileManager", "clearUnSuitableV");
        }
    }
}
