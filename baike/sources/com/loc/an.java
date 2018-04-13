package com.loc;

import android.content.Context;
import java.util.List;

public final class an {
    private af a;

    public an(Context context) {
        try {
            this.a = new af(context, af.a(am.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str, Class<? extends ao> cls) {
        this.a.a(ao.c(str), (Class) cls);
    }

    public final List<? extends ao> a(int i, Class<? extends ao> cls) {
        List<? extends ao> list = null;
        try {
            list = this.a.b(ao.c(i), cls);
        } catch (Throwable th) {
            w.a(th, "LogDB", "ByState");
        }
        return list;
    }

    public final void a(ao aoVar) {
        if (aoVar != null) {
            String c = ao.c(aoVar.b());
            List a = this.a.a(c, aoVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.a.b((Object) aoVar);
                return;
            }
            Object obj = (ao) a.get(0);
            if (aoVar.a() == 0) {
                obj.b(obj.c() + 1);
            } else {
                obj.b(0);
            }
            this.a.a(c, obj, true);
        }
    }

    public final void a(String str, Class<? extends ao> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            w.a(th, "LogDB", "delLog");
        }
    }

    public final void b(ao aoVar) {
        try {
            this.a.a(ao.c(aoVar.b()), (Object) aoVar);
        } catch (Throwable th) {
            w.a(th, "LogDB", "updateLogInfo");
        }
    }

    public final void b(String str, Class<? extends ao> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
