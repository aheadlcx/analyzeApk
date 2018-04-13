package com.alibaba.mtl.log.b;

import android.text.TextUtils;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.util.List;

public class a {
    private static StringBuilder a = new StringBuilder();
    private static volatile long e;
    private static long f;
    private static long g;
    private static long h;
    private static long i;
    private static long j = 0;
    private static long k = 0;
    private static long l = 0;
    private static long m = 0;
    private static long n = 0;
    private static long o = 0;
    private static long p = 0;
    private static long q = 0;
    private static long r = 0;
    private static long s = 0;
    private static long t = 0;
    private static int u;
    /* renamed from: u */
    private static long f32u = 0;
    private static int v = 0;
    /* renamed from: v */
    private static long f33v = 0;
    private static int w = 0;
    /* renamed from: w */
    private static long f34w = 0;
    private static long x = 0;
    private static long y = 0;

    public static synchronized void l(String str) {
        synchronized (a.class) {
            if (!d(str)) {
                if ("65501".equalsIgnoreCase(str)) {
                    y++;
                } else if ("65133".equalsIgnoreCase(str)) {
                    f34w++;
                } else if ("65502".equalsIgnoreCase(str)) {
                    x++;
                } else if ("65503".equalsIgnoreCase(str)) {
                    f33v++;
                }
                e++;
            }
        }
    }

    public static synchronized void m(String str) {
        synchronized (a.class) {
            if (!d(str)) {
                f++;
                D();
            }
        }
    }

    public static synchronized void a(List<com.alibaba.mtl.log.model.a> list, int i) {
        synchronized (a.class) {
            if (list != null) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < list.size()) {
                    com.alibaba.mtl.log.model.a aVar = (com.alibaba.mtl.log.model.a) list.get(i2);
                    if (aVar != null) {
                        if (!"6005".equalsIgnoreCase(aVar.T)) {
                            i3++;
                        }
                        a.append(aVar.X);
                        if (i2 != list.size() - 1) {
                            a.append(",");
                        }
                    }
                    i2++;
                    i3 = i3;
                }
                i.a("CoreStatics", "[uploadInc]:", Long.valueOf(g), "count:", Integer.valueOf(i));
                g += (long) i;
                i.a("CoreStatics", "[uploadInc]:", Long.valueOf(g));
                if (i3 != i) {
                    i.a("CoreStatics", (Object) "Mutil Process Upload Error");
                }
            }
        }
    }

    public static synchronized void d(int i) {
        synchronized (a.class) {
            u += i;
        }
    }

    public static synchronized void t() {
        synchronized (a.class) {
            h++;
        }
    }

    public static synchronized void u() {
        synchronized (a.class) {
            i++;
        }
    }

    public static synchronized void v() {
        synchronized (a.class) {
            n++;
        }
    }

    public static synchronized void w() {
        synchronized (a.class) {
            o++;
        }
    }

    public static synchronized void x() {
        synchronized (a.class) {
            p++;
        }
    }

    public static synchronized void y() {
        synchronized (a.class) {
            q++;
        }
    }

    public static synchronized void z() {
        synchronized (a.class) {
            r++;
        }
    }

    public static synchronized void A() {
        synchronized (a.class) {
            s++;
        }
    }

    public static synchronized void B() {
        synchronized (a.class) {
            t++;
        }
    }

    public static synchronized void C() {
        synchronized (a.class) {
            f32u++;
        }
    }

    public static synchronized void c(boolean z) {
        synchronized (a.class) {
        }
    }

    private static void D() {
        String u = l.u();
        if (IXAdSystemUtils.NT_WIFI.equalsIgnoreCase(u)) {
            m++;
        } else if ("3G".equalsIgnoreCase(u)) {
            k++;
        } else if ("4G".equalsIgnoreCase(u)) {
            l++;
        } else if ("2G".equalsIgnoreCase(u)) {
            j++;
        } else {
            v++;
        }
    }

    public static synchronized void E() {
        synchronized (a.class) {
            w++;
            if (!(e == 0 && g == 0)) {
                if (com.alibaba.mtl.log.a.o || w >= 6) {
                    c(true);
                }
            }
        }
    }

    private static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "6005".equalsIgnoreCase(str.trim());
    }
}
