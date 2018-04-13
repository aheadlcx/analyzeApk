package com.sina.weibo.sdk.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sina.weibo.R;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.WbUtils;
import com.sina.weibo.sdk.web.client.AuthWebViewClient;
import com.sina.weibo.sdk.web.client.BaseWebViewClient;
import com.sina.weibo.sdk.web.client.DefaultWebViewClient;
import com.sina.weibo.sdk.web.client.ShareWebViewClient;
import com.sina.weibo.sdk.web.param.AuthWebViewRequestParam;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;
import com.sina.weibo.sdk.web.param.DefaultWebViewRequestParam;
import com.sina.weibo.sdk.web.param.ShareWebViewRequestParam;
import com.sina.weibo.sdk.web.view.LoadingBar;
import cz.msebera.android.httpclient.protocol.HTTP;

public class WeiboSdkWebActivity extends Activity implements WebViewRequestCallback {
    public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";
    private TextView a;
    private TextView b;
    private WebView c;
    private LoadingBar d;
    private Button e;
    private TextView f;
    private LinearLayout g;
    private BaseWebViewRequestParam h;
    private BaseWebViewClient i;
    private int j = 0;

    private class a extends WebChromeClient {
        final /* synthetic */ WeiboSdkWebActivity a;

        private a(WeiboSdkWebActivity weiboSdkWebActivity) {
            this.a = weiboSdkWebActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.a.d.drawProgress(i);
            if (i == 100) {
                this.a.d.setVisibility(4);
            } else {
                this.a.d.setVisibility(0);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(this.a.h.getBaseData().getSpecifyTitle())) {
                this.a.b.setText(str);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.webo_web_layout);
        b();
        a();
    }

    private void a() {
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("type", -1);
        if (i == -1) {
            finish();
            return;
        }
        switch (i) {
            case 0:
                this.h = new DefaultWebViewRequestParam();
                this.i = new DefaultWebViewClient(this, this.h);
                break;
            case 1:
                this.h = new ShareWebViewRequestParam();
                this.i = new ShareWebViewClient(this, this, this.h);
                break;
            case 2:
                this.h = new AuthWebViewRequestParam();
                this.i = new AuthWebViewClient(this, this, this.h);
                break;
        }
        this.c.setWebViewClient(this.i);
        this.h.transformBundle(extras);
        c();
        if (this.h.hasExtraTask()) {
            this.h.doExtraTask(new b(this));
            return;
        }
        this.c.loadUrl(this.h.getRequestUrl());
    }

    private void b() {
        this.a = (TextView) findViewById(R.id.title_left_btn);
        this.b = (TextView) findViewById(R.id.title_text);
        this.c = (WebView) findViewById(R.id.web_view);
        this.d = (LoadingBar) findViewById(R.id.load_bar);
        this.a.setTextColor(ResourceManager.createColorStateList(-32256, 1728020992));
        this.a.setText(ResourceManager.getString(this, HTTP.CONN_CLOSE, "关闭", "关闭"));
        this.a.setOnClickListener(new c(this));
        this.c.setWebChromeClient(new a());
        this.e = (Button) findViewById(R.id.retry_btn);
        this.f = (TextView) findViewById(R.id.retry_title);
        this.g = (LinearLayout) findViewById(R.id.retry_layout);
        this.e.setOnClickListener(new d(this));
        this.f.setText(ResourceManager.getString(this, "A network error occurs, please tap the button to reload", "网络出错啦，请点击按钮重新加载", "網路出錯啦，請點擊按鈕重新載入"));
        this.e.setText(ResourceManager.getString(this, "channel_data_error", "重新加载", "重新載入"));
    }

    private void c() {
        if (!TextUtils.isEmpty(this.h.getBaseData().getSpecifyTitle())) {
            this.b.setText(this.h.getBaseData().getSpecifyTitle());
        }
        this.c.getSettings().setJavaScriptEnabled(true);
        this.c.getSettings().setSavePassword(false);
        this.c.getSettings().setUserAgentString(WbUtils.generateUA(this, this.h.getBaseData().getAuthInfo().getAppKey()));
        this.c.requestFocus();
        this.c.setScrollBarStyle(0);
        if (VERSION.SDK_INT >= 11) {
            this.c.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            removeJavascriptInterface(this.c);
        }
    }

    private void d() {
        finish();
    }

    public void removeJavascriptInterface(WebView webView) {
        if (VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    private void e() {
        this.g.setVisibility(0);
        this.c.setVisibility(8);
    }

    private void f() {
        this.g.setVisibility(8);
        this.c.setVisibility(0);
    }

    public void onPageStartedCallBack(WebView webView, String str, Bitmap bitmap) {
    }

    public void onPageFinishedCallBack(WebView webView, String str) {
        if (this.j == -1) {
            e();
        } else {
            f();
        }
    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView webView, String str) {
        return false;
    }

    public void onReceivedErrorCallBack(WebView webView, int i, String str, String str2) {
        Uri parse = Uri.parse(webView.getUrl());
        Uri parse2 = Uri.parse(str2);
        if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
            this.j = -1;
        }
    }

    public void onReceivedSslErrorCallBack(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
    }

    public void closePage() {
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.i.onBackKeyDown()) {
                return true;
            }
            if (this.c.canGoBack()) {
                this.c.goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
