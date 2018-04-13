package com.ali.auth.third.core.webview;

import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.ali.auth.third.core.WebViewProxy;
import com.ali.auth.third.core.context.KernelContext;

public class DefaultWebViewProxy implements WebViewProxy {
    private static volatile DefaultWebViewProxy instance = null;

    private DefaultWebViewProxy() {
    }

    public static DefaultWebViewProxy getInstance() {
        if (instance == null) {
            synchronized (DefaultWebViewProxy.class) {
                if (instance == null) {
                    instance = new DefaultWebViewProxy();
                }
            }
        }
        return instance;
    }

    public void setAcceptCookie(boolean z) {
        CookieManager.getInstance().setAcceptCookie(z);
    }

    public void setCookie(String str, String str2) {
        CookieManager.getInstance().setCookie(str, str2);
    }

    public String getCookie(String str) {
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        return instance.getCookie(".taobao.com");
    }

    public void flush() {
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.createInstance(KernelContext.getApplicationContext()).sync();
        }
    }

    public void removeSessionCookie() {
        CookieManager.getInstance().removeSessionCookie();
    }

    public void removeAllCookie() {
        CookieManager.getInstance().removeAllCookie();
    }

    public void removeExpiredCookie() {
        CookieManager.getInstance().removeExpiredCookie();
    }
}
