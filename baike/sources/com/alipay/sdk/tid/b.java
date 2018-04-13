package com.alipay.sdk.tid;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.a;

public final class b {
    private static b c;
    public String a;
    public String b;

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
                Context context = com.alipay.sdk.sys.b.a().a;
                a aVar = new a(context);
                String a = a.a(context).a();
                String b = a.a(context).b();
                c.a = aVar.a(a, b);
                c.b = aVar.b(a, b);
                if (TextUtils.isEmpty(c.b)) {
                    b bVar2 = c;
                    String toHexString = Long.toHexString(System.currentTimeMillis());
                    if (toHexString.length() > 10) {
                        toHexString = toHexString.substring(toHexString.length() - 10);
                    }
                    bVar2.b = toHexString;
                }
                aVar.a(a, b, c.a, c.b);
            }
            bVar = c;
        }
        return bVar;
    }
}
