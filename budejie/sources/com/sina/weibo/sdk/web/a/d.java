package com.sina.weibo.sdk.web.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.web.b;
import com.sina.weibo.sdk.web.c;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class d extends b {
    private Activity c;
    private boolean d = false;

    public d(Activity activity, b bVar, com.sina.weibo.sdk.web.b.b bVar2) {
        super(bVar, bVar2);
        this.c = activity;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (this.b != null) {
            this.b.a(webView, str, bitmap);
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

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.a(webView, str);
        }
        return a(str);
    }

    private boolean a(String str) {
        if (!str.startsWith("sinaweibo://browser/close")) {
            return false;
        }
        Bundle a = k.a(str);
        if (!(this.a.c() == null || TextUtils.isEmpty(this.a.c().getCallback()))) {
            String callback = this.a.c().getCallback();
            c a2 = c.a();
            if (!(a2.a(callback) == null || a.isEmpty())) {
                a2.b(callback);
            }
        }
        CharSequence string = a.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
        String string2 = a.getString("msg");
        if (TextUtils.isEmpty(string)) {
            a(this.c);
        } else if ("0".equals(string)) {
            b(this.c);
        } else {
            a(this.c, string2);
        }
        if (this.b != null) {
            this.b.a();
        }
        return true;
    }

    private void a(Activity activity, int i, String str) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null && !this.d) {
            Intent intent = new Intent("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY");
            String string = extras.getString("packageName");
            intent.setFlags(131072);
            intent.setPackage(string);
            intent.putExtras(extras);
            intent.putExtra("_weibo_appPackage", activity.getPackageName());
            intent.putExtra("_weibo_resp_errcode", i);
            intent.putExtra("_weibo_resp_errstr", str);
            try {
                activity.startActivityForResult(intent, 765);
            } catch (ActivityNotFoundException e) {
            }
            this.d = true;
        }
    }

    public void a(Activity activity) {
        a(activity, 1, "send cancel!!!");
    }

    public void b(Activity activity) {
        a(activity, 0, "send ok!!!");
    }

    public void a(Activity activity, String str) {
        a(activity, 2, str);
    }

    public void a() {
        super.a();
        a(this.c);
    }

    public boolean b() {
        a();
        if (this.b != null) {
            this.b.a();
        }
        return true;
    }
}
