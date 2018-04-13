package mtopsdk.a.a;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;

public final class a {
    private static volatile boolean a = false;
    private static CookieManager b;

    static {
        a(f.a().b());
    }

    public static synchronized String a(String str) {
        String str2 = null;
        synchronized (a.class) {
            if (a) {
                try {
                    str2 = b.getCookie(str);
                } catch (Throwable th) {
                    m.b("mtopsdk.CookieManager", "get cookie failed. url=" + str, th);
                }
            }
        }
        return str2;
    }

    private static synchronized void a(Context context) {
        synchronized (a.class) {
            if (!(a || context == null)) {
                CookieSyncManager.createInstance(context);
                CookieManager instance = CookieManager.getInstance();
                b = instance;
                instance.setAcceptCookie(true);
                b.removeExpiredCookie();
                a = true;
            }
        }
    }

    public static synchronized void a(String str, String str2) {
        synchronized (a.class) {
            if (a) {
                try {
                    b.setCookie(str, str2);
                    CookieSyncManager.getInstance().sync();
                } catch (Throwable th) {
                    m.b("mtopsdk.CookieManager", "set cookie failed. url=" + str + " cookies=" + str2, th);
                }
            }
        }
    }
}
