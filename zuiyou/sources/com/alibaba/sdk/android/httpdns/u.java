package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.NotificationCompat;
import com.umeng.analytics.a;
import java.net.SocketTimeoutException;

public class u {
    private static SharedPreferences a = null;
    /* renamed from: a */
    private static v f8a = v.ENABLE;
    private static long c = 0;
    /* renamed from: c */
    private static boolean f9c = false;
    private static boolean e = false;
    private static int f = 0;
    private static int g = 0;

    static synchronized String a(p pVar) {
        String str = null;
        synchronized (u.class) {
            if (pVar == p.QUERY_HOST || pVar == p.SNIFF_HOST) {
                if (f8a == v.ENABLE || f8a == v.PRE_DISABLE) {
                    str = c.f6b[f];
                } else if (pVar != p.QUERY_HOST) {
                    str = c.f6b[f];
                }
            } else if (pVar != p.QUERY_SCHEDULE_CENTER && pVar == p.SNIFF_SCHEDULE_CENTER) {
            }
        }
        return str;
    }

    static synchronized void a(Context context) {
        synchronized (u.class) {
            if (!f9c) {
                synchronized (u.class) {
                    if (!f9c) {
                        if (context != null) {
                            a = context.getSharedPreferences("httpdns_config_cache", 0);
                        }
                        e = a.getBoolean(NotificationCompat.CATEGORY_STATUS, false);
                        f = a.getInt("activiate_ip_index", 0);
                        g = f;
                        c = a.getLong("disable_modified_time", 0);
                        if (System.currentTimeMillis() - c >= a.i) {
                            b(false);
                        }
                        if (e) {
                            f8a = v.DISABLE;
                        } else {
                            f8a = v.ENABLE;
                        }
                        f9c = true;
                    }
                }
            }
        }
    }

    static synchronized void a(String str, String str2) {
        synchronized (u.class) {
            if (!(f8a == v.ENABLE || str2 == null || !str2.equals(c.f6b[f]))) {
                f.e((f8a == v.DISABLE ? "Disable " : "Pre_disable ") + "mode finished. Enter enable mode.");
                f8a = v.ENABLE;
                b(false);
                t.a().c();
                g = f;
            }
        }
    }

    static synchronized void a(String str, String str2, Throwable th) {
        synchronized (u.class) {
            if (a(th) && str2 != null && str2.equals(c.f6b[f])) {
                d();
                if (g == f) {
                    t.a().a(false);
                    q.a().a();
                }
                if (f8a == v.ENABLE) {
                    f8a = v.PRE_DISABLE;
                    f.e("enter pre_disable mode");
                } else if (f8a == v.PRE_DISABLE) {
                    f8a = v.DISABLE;
                    f.e("enter disable mode");
                    b(true);
                    t.a().f(str);
                }
            }
        }
    }

    private static boolean a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (!(th instanceof e)) {
            return false;
        }
        e eVar = (e) th;
        return eVar.getErrorCode() == 403 && eVar.getMessage().equals("ServiceLevelDeny");
    }

    static void b(int i) {
        if (a != null && i >= 0 && i < c.f6b.length) {
            f = i;
            Editor edit = a.edit();
            edit.putInt("activiate_ip_index", i);
            edit.putLong("activiated_ip_index_modified_time", System.currentTimeMillis());
            edit.commit();
        }
    }

    static synchronized void b(boolean z) {
        synchronized (u.class) {
            if (e != z) {
                e = z;
                if (a != null) {
                    Editor edit = a.edit();
                    edit.putBoolean(NotificationCompat.CATEGORY_STATUS, e);
                    edit.putLong("disable_modified_time", System.currentTimeMillis());
                    edit.commit();
                }
            }
        }
    }

    static synchronized boolean c() {
        boolean z;
        synchronized (u.class) {
            z = e;
        }
        return z;
    }

    private static void d() {
        if (f == c.f6b.length - 1) {
            f = 0;
        } else {
            f++;
        }
        b(f);
    }

    static synchronized void e() {
        synchronized (u.class) {
            b(0);
            g = f;
            t.a().a(true);
        }
    }

    static synchronized void f() {
        synchronized (u.class) {
            t.a().a(true);
        }
    }
}
