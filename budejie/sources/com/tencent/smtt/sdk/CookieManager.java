package com.tencent.smtt.sdk;

import android.os.Build.VERSION;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.utils.v;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CookieManager {
    private static CookieManager b;
    ArrayList<CookieManager$a> a;
    private boolean c = false;

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (b == null) {
            synchronized (CookieManager.class) {
                if (b == null) {
                    b = new CookieManager();
                }
            }
        }
        return b;
    }

    synchronized void a() {
        this.c = true;
        if (!(this.a == null || this.a.size() == 0)) {
            bi b = bi.b();
            Iterator it;
            CookieManager$a cookieManager$a;
            if (b != null && b.c()) {
                it = this.a.iterator();
                while (it.hasNext()) {
                    cookieManager$a = (CookieManager$a) it.next();
                    switch (cookieManager$a.a) {
                        case 1:
                            setCookie(cookieManager$a.b, cookieManager$a.c, cookieManager$a.d);
                            break;
                        case 2:
                            setCookie(cookieManager$a.b, cookieManager$a.c);
                            break;
                        default:
                            break;
                    }
                }
            }
            it = this.a.iterator();
            while (it.hasNext()) {
                cookieManager$a = (CookieManager$a) it.next();
                switch (cookieManager$a.a) {
                    case 1:
                        if (VERSION.SDK_INT < 21) {
                            break;
                        }
                        v.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, new Object[]{cookieManager$a.b, cookieManager$a.c, cookieManager$a.d});
                        break;
                    case 2:
                        android.webkit.CookieManager.getInstance().setCookie(cookieManager$a.b, cookieManager$a.c);
                        break;
                    default:
                        break;
                }
            }
            this.a.clear();
        }
    }

    public boolean acceptCookie() {
        bi b = bi.b();
        return (b == null || !b.c()) ? android.webkit.CookieManager.getInstance().acceptCookie() : b.d().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        boolean booleanValue;
        bi b = bi.b();
        Object invokeStaticMethod;
        if (b != null && b.c()) {
            invokeStaticMethod = b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, new Object[]{webView.getView()});
            booleanValue = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : true;
        } else if (VERSION.SDK_INT < 21) {
            booleanValue = true;
        } else {
            invokeStaticMethod = v.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{WebView.class}, new Object[]{webView.getView()});
            booleanValue = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : false;
        }
        return booleanValue;
    }

    public void flush() {
        bi b = bi.b();
        if (b != null && b.c()) {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        bi b = bi.b();
        if (b != null && b.c()) {
            return b.d().a(str);
        }
        String str2 = null;
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public boolean hasCookies() {
        bi b = bi.b();
        return (b == null || !b.c()) ? android.webkit.CookieManager.getInstance().hasCookies() : b.d().h();
    }

    public void removeAllCookie() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            b.d().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        bi b = bi.b();
        if (b != null && b.c()) {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, new Object[]{valueCallback});
        } else if (VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{ValueCallback.class}, new Object[]{valueCallback});
        }
    }

    public void removeExpiredCookie() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookie() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        bi b = bi.b();
        if (b != null && b.c()) {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, new Object[]{valueCallback});
        } else if (VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{ValueCallback.class}, new Object[]{valueCallback});
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            try {
                android.webkit.CookieManager.getInstance().setAcceptCookie(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(z)});
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        bi b = bi.b();
        if (b != null && b.c()) {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, new Object[]{webView.getView(), Boolean.valueOf(z)});
        } else if (VERSION.SDK_INT >= 21) {
            v.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{WebView.class, Boolean.TYPE}, new Object[]{webView.getView(), Boolean.valueOf(z)});
        }
    }

    public synchronized void setCookie(String str, String str2) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            if (this.c) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!bi.b().e()) {
                CookieManager$a cookieManager$a = new CookieManager$a(this);
                cookieManager$a.a = 2;
                cookieManager$a.b = str;
                cookieManager$a.c = str2;
                cookieManager$a.d = null;
                if (this.a == null) {
                    this.a = new ArrayList();
                }
                this.a.add(cookieManager$a);
            }
        } else {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, new Object[]{str, str2});
        }
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        bi b = bi.b();
        if (b == null || !b.c()) {
            if (!bi.b().e()) {
                CookieManager$a cookieManager$a = new CookieManager$a(this);
                cookieManager$a.a = 1;
                cookieManager$a.b = str;
                cookieManager$a.c = str2;
                cookieManager$a.d = valueCallback;
                if (this.a == null) {
                    this.a = new ArrayList();
                }
                this.a.add(cookieManager$a);
            }
            if (this.c && VERSION.SDK_INT >= 21) {
                v.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, new Object[]{str, str2, valueCallback});
            }
        } else {
            b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, new Object[]{str, str2, valueCallback});
        }
    }

    public void setCookies(Map<String, String[]> map) {
        bi b = bi.b();
        boolean a = (b == null || !b.c()) ? false : b.d().a(map);
        if (!a) {
            for (String str : map.keySet()) {
                for (String cookie : (String[]) map.get(str)) {
                    setCookie(str, cookie);
                }
            }
        }
    }
}
