package com.huawei.hms.support.log;

import android.content.Context;
import com.huawei.hms.support.log.a.a.a;

public class b {
    private static final c a = new a(new com.huawei.hms.support.log.a.a());
    private int b = 4;
    private String c;

    public void a(Context context, int i, String str) {
        this.b = i;
        this.c = str;
        a.a(context, "HMSCore");
    }

    public boolean a(int i) {
        if (i < this.b) {
            return false;
        }
        return true;
    }

    public void a(int i, String str, String str2) {
        if (a(i)) {
            d a = a(i, str, str2, null);
            a.a(a.a() + a.b(), i, str, str2);
        }
    }

    public void a(String str, String str2) {
        d a = a(4, str, str2, null);
        a.a(a.a() + '\n' + a.b(), 4, str, str2);
    }

    private d a(int i, String str, String str2, Throwable th) {
        d dVar = new d(8, this.c, i, str);
        dVar.a((Object) str2);
        dVar.a(th);
        return dVar;
    }
}
