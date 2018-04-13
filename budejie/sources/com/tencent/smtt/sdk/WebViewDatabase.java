package com.tencent.smtt.sdk;

import android.content.Context;

public class WebViewDatabase {
    private static WebViewDatabase a;
    private Context b;

    protected WebViewDatabase(Context context) {
        this.b = context;
    }

    private static synchronized WebViewDatabase a(Context context) {
        WebViewDatabase webViewDatabase;
        synchronized (WebViewDatabase.class) {
            if (a == null) {
                a = new WebViewDatabase(context);
            }
            webViewDatabase = a;
        }
        return webViewDatabase;
    }

    public static WebViewDatabase getInstance(Context context) {
        return a(context);
    }

    public void clearFormData() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearFormData();
        } else {
            b.d().g(this.b);
        }
    }

    public void clearHttpAuthUsernamePassword() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearHttpAuthUsernamePassword();
        } else {
            b.d().e(this.b);
        }
    }

    @Deprecated
    public void clearUsernamePassword() {
        bi b = bi.b();
        if (b == null || !b.c()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearUsernamePassword();
        } else {
            b.d().c(this.b);
        }
    }

    public boolean hasFormData() {
        bi b = bi.b();
        return (b == null || !b.c()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasFormData() : b.d().f(this.b);
    }

    public boolean hasHttpAuthUsernamePassword() {
        bi b = bi.b();
        return (b == null || !b.c()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasHttpAuthUsernamePassword() : b.d().d(this.b);
    }

    @Deprecated
    public boolean hasUsernamePassword() {
        bi b = bi.b();
        return (b == null || !b.c()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasUsernamePassword() : b.d().b(this.b);
    }
}
