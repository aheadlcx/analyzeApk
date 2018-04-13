package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.f.g;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.apmobilesecuritysdk.f.i;
import com.alipay.b.a.a.b.b;
import com.alipay.b.a.a.c.a.c;
import com.alipay.b.a.a.c.a.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class a {
    private Context a;
    private com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    private int c = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String b = b(context);
        return com.alipay.b.a.a.a.a.a(b) ? h.f(context) : b;
    }

    public static String a(Context context, String str) {
        try {
            String a = i.a(str);
            if (!com.alipay.b.a.a.a.a.a(a)) {
                return a;
            }
            a = g.a(context, str);
            i.a(str, a);
            if (!com.alipay.b.a.a.a.a.a(a)) {
                return a;
            }
            return "";
        } catch (Throwable th) {
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = new String[]{"2016-11-10 2016-11-11", "2016-12-11 2016-12-12"};
        int random = ((int) (((Math.random() * 24.0d) * 60.0d) * 60.0d)) * 1;
        int i = 0;
        while (i < 2) {
            try {
                String[] split = strArr[i].split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    parse2 = instance.getTime();
                    if (date.after(parse) && date.before(parse2)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    private boolean a(Map<String, String> map, String str) {
        long parseLong;
        boolean z;
        long j = 0;
        if (a()) {
            return com.alipay.b.a.a.a.a.a(a(this.a, str)) || com.alipay.b.a.a.a.a.a(b(this.a));
        } else {
            e.a();
            if (!com.alipay.b.a.a.a.a.a(e.b(this.a, map), i.c())) {
                return true;
            }
            try {
                parseLong = Long.parseLong(h.b(this.a));
                try {
                    b.a();
                    j = Long.parseLong(b.o());
                    z = false;
                } catch (Throwable th) {
                    z = true;
                    return Math.abs(j - parseLong) <= 3000 ? true : true;
                }
            } catch (Throwable th2) {
                parseLong = j;
                z = true;
                if (Math.abs(j - parseLong) <= 3000) {
                }
            }
            if (Math.abs(j - parseLong) <= 3000 && !r4) {
                String a = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
                String a2 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.g, "");
                return (!com.alipay.b.a.a.a.a.b(a) || com.alipay.b.a.a.a.a.a(a, i.d())) ? (com.alipay.b.a.a.a.a.b(a2) && !com.alipay.b.a.a.a.a.a(a2, i.e())) || !i.a(this.a, str) || com.alipay.b.a.a.a.a.a(a(this.a, str)) || com.alipay.b.a.a.a.a.a(b(this.a)) : true;
            }
        }
    }

    private c b(Map<String, String> map) {
        try {
            com.alipay.apmobilesecuritysdk.f.b c;
            Context context = this.a;
            d dVar = new d();
            String a = a(context, com.alipay.b.a.a.a.a.a(map, "appName", ""));
            String a2 = com.alipay.apmobilesecuritysdk.e.a.a();
            String e = h.e(context);
            dVar.c(a);
            dVar.d(a2);
            dVar.e(e);
            dVar.a("android");
            a2 = "";
            String str = "";
            e = "";
            a = "";
            com.alipay.apmobilesecuritysdk.f.c c2 = com.alipay.apmobilesecuritysdk.f.d.c(context);
            if (c2 != null) {
                str = c2.a();
                e = c2.c();
            }
            if (com.alipay.b.a.a.a.a.a(str)) {
                c = com.alipay.apmobilesecuritysdk.f.a.c(context);
                if (c != null) {
                    str = c.a();
                    e = c.c();
                }
            }
            c2 = com.alipay.apmobilesecuritysdk.f.d.b();
            if (c2 != null) {
                a2 = c2.a();
                a = c2.c();
            }
            if (com.alipay.b.a.a.a.a.a(a2)) {
                c = com.alipay.apmobilesecuritysdk.f.a.b();
                if (c != null) {
                    a2 = c.a();
                    a = c.c();
                }
            }
            dVar.g(str);
            dVar.f(a2);
            if (com.alipay.b.a.a.a.a.a(str)) {
                dVar.b(a2);
                dVar.h(a);
            } else {
                dVar.b(str);
                dVar.h(e);
            }
            dVar.a(e.a(context, map));
            return com.alipay.b.a.a.c.d.a(this.a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            String b = i.b();
            if (!com.alipay.b.a.a.a.a.a(b)) {
                return b;
            }
            com.alipay.apmobilesecuritysdk.f.c b2 = com.alipay.apmobilesecuritysdk.f.d.b(context);
            if (b2 != null) {
                i.a(b2);
                b = b2.a();
                if (com.alipay.b.a.a.a.a.b(b)) {
                    return b;
                }
            }
            com.alipay.apmobilesecuritysdk.f.b b3 = com.alipay.apmobilesecuritysdk.f.a.b(context);
            if (b3 != null) {
                i.a(b3);
                b = b3.a();
                if (com.alipay.b.a.a.a.a.b(b)) {
                    return b;
                }
            }
            return "";
        } catch (Throwable th) {
        }
    }

    public final int a(Map<String, String> map) {
        Object obj = 2;
        try {
            int i;
            com.alipay.apmobilesecuritysdk.c.a.a(this.a, com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, ""), com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.g, ""), a(this.a));
            String a = com.alipay.b.a.a.a.a.a(map, "appName", "");
            b(this.a);
            a(this.a, a);
            i.a();
            boolean a2 = a((Map) map, a);
            Context context = this.a;
            b.a();
            h.b(context, String.valueOf(b.o()));
            if (a2) {
                Context context2 = this.a;
                com.alipay.apmobilesecuritysdk.c.b bVar = new com.alipay.apmobilesecuritysdk.c.b();
                context2 = this.a;
                com.alipay.apmobilesecuritysdk.b.a.a().b();
                com.alipay.apmobilesecuritysdk.e.a.b();
                com.alipay.b.a.a.c.a.a b = b((Map) map);
                if (b != null) {
                    if (b.a) {
                        if (!com.alipay.b.a.a.a.a.a(b.c)) {
                            obj = 1;
                        }
                    } else if ("APPKEY_ERROR".equals(b.b)) {
                        obj = 3;
                    }
                }
                switch (obj) {
                    case 1:
                        h.a(this.a, "1".equals(b.e));
                        h.d(this.a, b.f == null ? "0" : b.f);
                        h.e(this.a, b.g);
                        h.a(this.a, b.h);
                        h.f(this.a, b.i);
                        i.c(e.b(this.a, map));
                        i.a(a, b.d);
                        i.b(b.c);
                        i.d(b.j);
                        String a3 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
                        if (!com.alipay.b.a.a.a.a.b(a3) || com.alipay.b.a.a.a.a.a(a3, i.d())) {
                            a3 = i.d();
                        } else {
                            i.e(a3);
                        }
                        i.e(a3);
                        a3 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.g, "");
                        if (!com.alipay.b.a.a.a.a.b(a3) || com.alipay.b.a.a.a.a.a(a3, i.e())) {
                            a3 = i.e();
                        } else {
                            i.f(a3);
                        }
                        i.f(a3);
                        i.a();
                        com.alipay.apmobilesecuritysdk.f.d.a(this.a, i.g());
                        Context context3 = this.a;
                        com.alipay.apmobilesecuritysdk.f.d.a();
                        com.alipay.apmobilesecuritysdk.f.a.a(this.a, new com.alipay.apmobilesecuritysdk.f.b(i.b(), i.c(), i.f()));
                        context3 = this.a;
                        com.alipay.apmobilesecuritysdk.f.a.a();
                        g.a(this.a, a, i.a(a));
                        context3 = this.a;
                        g.a();
                        h.a(this.a, a, System.currentTimeMillis());
                        break;
                    case 3:
                        i = 1;
                        break;
                    default:
                        if (b != null) {
                            com.alipay.apmobilesecuritysdk.c.a.a("Server error, result:" + b.b);
                        } else {
                            com.alipay.apmobilesecuritysdk.c.a.a("Server error, returned null");
                        }
                        if (com.alipay.b.a.a.a.a.a(a(this.a, a))) {
                            i = 4;
                            break;
                        }
                        break;
                }
                i = 0;
                context2 = this.a;
                a(this.a, a);
                h.f(this.a);
            } else {
                i = 0;
            }
            this.c = i;
            com.alipay.b.a.a.c.b.a a4 = com.alipay.b.a.a.c.d.a(this.a, this.b.c());
            context = this.a;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            obj = (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1) ? 1 : null;
            if (obj != null && h.d(context)) {
                new com.alipay.b.a.a.e.b(context.getFilesDir().getAbsolutePath() + "/log/ap", a4).a();
            }
        } catch (Throwable e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return this.c;
    }
}
