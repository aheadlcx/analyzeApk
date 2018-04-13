package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.f.d;
import com.alipay.apmobilesecuritysdk.f.g;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.apmobilesecuritysdk.f.i;
import com.alipay.b.a.a.c.a.b;
import com.alipay.b.a.a.c.a.c;
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
        return com.alipay.b.a.a.a.a.a(b) ? h.c(context) : b;
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
                String[] split = strArr[i].split(" ");
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

    private b b(Map<String, String> map) {
        try {
            com.alipay.apmobilesecuritysdk.f.b c;
            Context context = this.a;
            c cVar = new c();
            String str = "";
            String str2 = "";
            String str3 = "";
            String a = com.alipay.apmobilesecuritysdk.e.a.a();
            String a2 = com.alipay.b.a.a.a.a.a(map, "rpcVersion", "");
            String a3 = a(context, com.alipay.b.a.a.a.a.a(map, "appName", ""));
            com.alipay.apmobilesecuritysdk.f.c c2 = d.c(context);
            com.alipay.apmobilesecuritysdk.f.c b = d.b();
            if (c2 != null) {
                str = c2.a();
                str3 = c2.c();
            }
            if (com.alipay.b.a.a.a.a.a(str)) {
                c = com.alipay.apmobilesecuritysdk.f.a.c(context);
                if (c != null) {
                    str = c.a();
                    str3 = c.c();
                }
            }
            if (b != null) {
                str2 = b.a();
            }
            if (com.alipay.b.a.a.a.a.a(str2)) {
                c = com.alipay.apmobilesecuritysdk.f.a.b();
                if (c != null) {
                    str2 = c.a();
                }
            }
            cVar.a(AlibcConstants.PF_ANDROID);
            cVar.c(str);
            cVar.b(str2);
            cVar.d(a3);
            cVar.e(a);
            cVar.g(str3);
            cVar.f(a2);
            cVar.a(e.a(context, map));
            return com.alipay.b.a.a.c.d.a(this.a, this.b.c()).a(cVar);
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
            com.alipay.apmobilesecuritysdk.f.c b2 = d.b(context);
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
        Object obj2 = 1;
        try {
            Object obj3;
            int i;
            com.alipay.apmobilesecuritysdk.c.a.a(this.a, com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, ""), com.alipay.b.a.a.a.a.a(map, "utdid", ""), a(this.a));
            String a = com.alipay.b.a.a.a.a.a(map, "appName", "");
            b(this.a);
            a(this.a, a);
            i.a();
            int i2;
            if (!a()) {
                e.a();
                if ((!com.alipay.b.a.a.a.a.a(e.b(this.a, map), i.c()) ? 1 : null) != null) {
                    i2 = 1;
                } else {
                    String a2 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
                    String a3 = com.alipay.b.a.a.a.a.a(map, "utdid", "");
                    if (com.alipay.b.a.a.a.a.b(a2) && !com.alipay.b.a.a.a.a.a(a2, i.d())) {
                        i2 = 1;
                    } else if (com.alipay.b.a.a.a.a.b(a3) && !com.alipay.b.a.a.a.a.a(a3, i.e())) {
                        i2 = 1;
                    } else if (!i.a(this.a, a)) {
                        i2 = 1;
                    } else if (com.alipay.b.a.a.a.a.a(a(this.a, a))) {
                        i2 = 1;
                    } else if (com.alipay.b.a.a.a.a.a(b(this.a))) {
                        i2 = 1;
                    } else {
                        obj3 = null;
                    }
                }
            } else if (com.alipay.b.a.a.a.a.a(a(this.a, a))) {
                obj3 = 1;
            } else if (com.alipay.b.a.a.a.a.a(b(this.a))) {
                i2 = 1;
            } else {
                obj3 = null;
            }
            if (obj3 == null) {
                i = 0;
            } else {
                Context context = this.a;
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
                        h.a(this.a, "1".equals(b.h));
                        h.b(this.a, b.j == null ? "0" : b.j);
                        i.c(e.b(this.a, map));
                        i.a(a, b.d);
                        i.b(b.c);
                        i.d(b.e);
                        String a4 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
                        if (!com.alipay.b.a.a.a.a.b(a4) || com.alipay.b.a.a.a.a.a(a4, i.d())) {
                            a4 = i.d();
                        } else {
                            i.e(a4);
                        }
                        i.e(a4);
                        a4 = com.alipay.b.a.a.a.a.a(map, "utdid", "");
                        if (!com.alipay.b.a.a.a.a.b(a4) || com.alipay.b.a.a.a.a.a(a4, i.e())) {
                            a4 = i.e();
                        } else {
                            i.f(a4);
                        }
                        i.f(a4);
                        i.a();
                        d.a(this.a, i.g());
                        Context context2 = this.a;
                        d.a();
                        com.alipay.apmobilesecuritysdk.f.a.a(this.a, new com.alipay.apmobilesecuritysdk.f.b(i.b(), i.c(), i.f()));
                        context2 = this.a;
                        com.alipay.apmobilesecuritysdk.f.a.a();
                        g.a(this.a, a, i.a(a));
                        context2 = this.a;
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
            }
            this.c = i;
            com.alipay.b.a.a.c.b.a a5 = com.alipay.b.a.a.c.d.a(this.a, this.b.c());
            Context context3 = this.a;
            ConnectivityManager connectivityManager = (ConnectivityManager) context3.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1)) {
                obj2 = null;
            }
            if (obj2 != null && h.b(context3)) {
                new com.alipay.b.a.a.e.b(context3.getFilesDir().getAbsolutePath() + "/log/ap", a5).a();
            }
        } catch (Throwable e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return this.c;
    }
}
