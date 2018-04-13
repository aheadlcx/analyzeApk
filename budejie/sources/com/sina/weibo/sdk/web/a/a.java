package com.sina.weibo.sdk.web.a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.auth.d;
import com.sina.weibo.sdk.auth.e;
import com.sina.weibo.sdk.web.b;
import com.sina.weibo.sdk.web.c;
import com.umeng.analytics.pro.x;

public class a extends b {
    private Context c;
    private boolean d = false;

    public a(b bVar, Context context, com.sina.weibo.sdk.web.b.b bVar2) {
        super(bVar, bVar2);
        this.c = context;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.a(webView, str);
        }
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("sms:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.c.startActivity(intent);
            return true;
        } else if (!str.startsWith("sinaweibo://browser/close")) {
            return false;
        } else {
            if (this.a.c() == null || TextUtils.isEmpty(this.a.c().getCallback())) {
                return true;
            }
            String callback = this.a.c().getCallback();
            c a = c.a();
            if (a.a(callback) != null) {
                a.a(callback).cancel();
            }
            a.b(callback);
            return true;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null) {
            this.b.a(webView, str, bitmap);
        }
        if (!str.startsWith(this.a.c().getAuthInfo().getRedirectUrl()) || this.d) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.d = true;
        b(str);
        webView.stopLoading();
        if (this.b != null) {
            this.b.a();
        }
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.b(webView, str);
        }
    }

    @RequiresApi(api = 23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (this.b != null) {
            this.b.a(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.b != null) {
            this.b.a(webView, i, str, str2);
        }
    }

    private void b(String str) {
        Bundle a = j.a(str);
        String string = a.getString(x.aF);
        String string2 = a.getString("error_code");
        String string3 = a.getString("error_description");
        d dVar = null;
        if (!(this.a.c() == null || TextUtils.isEmpty(this.a.c().getCallback()))) {
            String callback = this.a.c().getCallback();
            c a2 = c.a();
            dVar = a2.a(callback);
            a2.b(callback);
        }
        if (string == null && string2 == null) {
            if (dVar != null) {
                com.sina.weibo.sdk.auth.b a3 = com.sina.weibo.sdk.auth.b.a(a);
                com.sina.weibo.sdk.auth.a.a(this.c, a3);
                dVar.onSuccess(a3);
            }
        } else if (dVar != null) {
            dVar.onFailure(new e(string2, string3));
        }
    }

    public void a() {
        super.a();
        if (this.a.c() != null && !TextUtils.isEmpty(this.a.c().getCallback())) {
            String callback = this.a.c().getCallback();
            c a = c.a();
            if (a.a(callback) != null) {
                a.a(callback).cancel();
            }
            a.b(callback);
        }
    }

    public boolean b() {
        a();
        if (this.b != null) {
            this.b.a();
        }
        return true;
    }
}
