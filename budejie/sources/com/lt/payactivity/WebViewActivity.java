package com.lt.payactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.widget.a;

public class WebViewActivity extends Activity {
    ProgressWebView a;
    String b = "";
    TextView c;
    TextView d;
    TextView e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        setContentView(R.layout.lt_webview_activity);
        a.a((Activity) this);
        a();
        this.c.setText(getResources().getString(R.string.goback));
        this.d.setText(getResources().getString(R.string.colse));
        this.e.setText(getResources().getString(R.string.lt_webview_title));
        this.b = getIntent().getStringExtra("url");
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.getSettings().setCacheMode(2);
        this.a.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (str != null && str.startsWith("http://")) {
                    this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.a.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
            }
        });
        this.a.loadUrl(this.b);
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.a = (ProgressWebView) findViewById(R.id.webview);
        this.c = (TextView) findViewById(R.id.btn_goback);
        this.d = (TextView) findViewById(R.id.btn_close);
        this.e = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.btn_goback).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.a == null || !this.a.a.canGoBack()) {
                    this.a.b();
                } else {
                    this.a.a.goBack();
                }
            }
        });
        findViewById(R.id.btn_close).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
    }

    private void b() {
        String cookie = CookieManager.getInstance().getCookie(this.b);
        if (TextUtils.isEmpty(cookie)) {
            finish();
            return;
        }
        String a = a(cookie);
        cookie = b(cookie);
        aa.b("test", "phoneNum=" + a + ",state=" + cookie);
        if (!TextUtils.isEmpty(a)) {
            an.i(this, a);
        }
        com.lt.a.a((Context) this).a(true);
        Intent intent = new Intent();
        intent.putExtra("phoneNum", a);
        intent.putExtra("state", cookie);
        setResult(10001, intent);
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("phonenum=");
        String str2 = "";
        if (indexOf < 0) {
            return str2;
        }
        str2 = str.substring(indexOf);
        try {
            return str2.substring("phonenum=".length(), str2.indexOf(h.b));
        } catch (Exception e) {
            return str2;
        }
    }

    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("state=");
        String str2 = "";
        if (indexOf < 0) {
            return str2;
        }
        str2 = str.substring(indexOf);
        try {
            return str2.substring("state=".length(), str2.indexOf(h.b));
        } catch (Exception e) {
            return str2;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.a.canGoBack()) {
            this.a.goBack();
        } else {
            b();
        }
        return true;
    }
}
