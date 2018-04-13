package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;
import com.sina.weibo.sdk.statistic.i;

public final class b extends WebViewClient {
    Activity a;
    Handler b;
    boolean c;
    private boolean d;
    private a e;
    private Runnable f = new f(this);

    public b(Activity activity) {
        this.a = activity;
        this.b = new Handler(this.a.getMainLooper());
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.c = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        com.alipay.sdk.app.statistic.a.a("net", c.q, "证书错误");
        if (this.d) {
            sslErrorHandler.proceed();
            this.d = false;
            return;
        }
        this.a.runOnUiThread(new c(this, sslErrorHandler));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return l.a(webView, str, this.a);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.e == null) {
            this.e = new a(this.a, a.a);
        }
        this.e.a();
        this.b.postDelayed(this.f, i.MIN_UPLOAD_INTERVAL);
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        a();
        this.b.removeCallbacks(this.f);
    }

    private void a() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }
}
