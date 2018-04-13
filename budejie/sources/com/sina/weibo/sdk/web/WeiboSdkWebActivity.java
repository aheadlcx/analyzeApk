package com.sina.weibo.sdk.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R$styleable;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.a.i;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.web.b.b;
import com.sina.weibo.sdk.web.b.c;
import com.sina.weibo.sdk.web.b.d;
import com.sina.weibo.sdk.web.view.LoadingBar;

public class WeiboSdkWebActivity extends Activity implements b {
    private TextView a;
    private TextView b;
    private WebView c;
    private LoadingBar d;
    private Button e;
    private TextView f;
    private LinearLayout g;
    private b h;
    private com.sina.weibo.sdk.web.a.b i;
    private int j = 0;

    private class a extends WebChromeClient {
        final /* synthetic */ WeiboSdkWebActivity a;

        private a(WeiboSdkWebActivity weiboSdkWebActivity) {
            this.a = weiboSdkWebActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.a.d.a(i);
            if (i == 100) {
                this.a.d.setVisibility(4);
            } else {
                this.a.d.setVisibility(0);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(this.a.h.c().getSpecifyTitle())) {
                this.a.b.setText(str);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(c());
        b();
    }

    private void b() {
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("type", -1);
        if (i == -1) {
            finish();
            return;
        }
        switch (i) {
            case 0:
                this.h = new c();
                this.i = new com.sina.weibo.sdk.web.a.c(this, this.h);
                break;
            case 1:
                this.h = new d();
                this.i = new com.sina.weibo.sdk.web.a.d(this, this, this.h);
                break;
            case 2:
                this.h = new com.sina.weibo.sdk.web.b.a();
                this.i = new com.sina.weibo.sdk.web.a.a(this, this, this.h);
                break;
        }
        this.c.setWebViewClient(this.i);
        this.h.d(extras);
        d();
        if (this.h.a()) {
            this.h.a(new com.sina.weibo.sdk.web.b.b.a(this) {
                final /* synthetic */ WeiboSdkWebActivity a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    this.a.c.loadUrl(this.a.h.b());
                }

                public void b(String str) {
                }
            });
            return;
        }
        this.c.loadUrl(this.h.b());
    }

    private View c() {
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(-1);
        View relativeLayout2 = new RelativeLayout(this);
        this.a = new TextView(this);
        this.a.setTextSize(17.0f);
        this.a.setTextColor(g.a(-32256, 1728020992));
        this.a.setText(g.a(this, "Close", "关闭", "关闭"));
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WeiboSdkWebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.i.a();
                this.a.e();
            }
        });
        this.b = new TextView(this);
        this.b.setTextSize(18.0f);
        this.b.setTextColor(-11382190);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.a.setPadding(i.a(10, this), 0, i.a(10, this), 0);
        layoutParams2.addRule(13);
        relativeLayout2.addView(this.a, layoutParams);
        relativeLayout2.addView(this.b, layoutParams2);
        relativeLayout.addView(relativeLayout2, new RelativeLayout.LayoutParams(-1, i.a(55, this)));
        this.c = new WebView(this);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.topMargin = i.a(55, this);
        relativeLayout.addView(this.c, layoutParams3);
        this.d = new LoadingBar(this);
        layoutParams3 = new RelativeLayout.LayoutParams(-1, i.a(3, this));
        layoutParams3.topMargin = i.a(55, this);
        relativeLayout.addView(this.d, layoutParams3);
        relativeLayout2 = new View(this);
        relativeLayout2.setBackgroundResource(getResources().getIdentifier("weibosdk_common_shadow_top", "drawable", getPackageName()));
        layoutParams = new RelativeLayout.LayoutParams(-1, i.a(3, this));
        layoutParams.topMargin = i.a(55, this);
        relativeLayout.addView(relativeLayout2, layoutParams);
        this.g = new LinearLayout(this);
        this.g.setOrientation(1);
        relativeLayout2 = new ImageView(this);
        relativeLayout2.setImageResource(getResources().getIdentifier("weibosdk_empty_failed", "drawable", getPackageName()));
        this.g.addView(relativeLayout2);
        this.f = new TextView(this);
        this.f.setTextSize(14.0f);
        this.f.setTextColor(-4342339);
        layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = i.a(18, this);
        layoutParams3.bottomMargin = i.a(20, this);
        this.g.addView(this.f, layoutParams3);
        this.e = new Button(this);
        this.e.setTextSize(16.0f);
        this.e.setTextColor(-8882056);
        layoutParams3 = new LinearLayout.LayoutParams(i.a(R$styleable.Theme_Custom_myinfo_night_model_bg, this), i.a(46, this));
        layoutParams3.gravity = 17;
        this.g.addView(this.e, layoutParams3);
        this.e.setBackgroundResource(getResources().getIdentifier("retry_btn_selector", "drawable", getPackageName()));
        layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        relativeLayout.addView(this.g, layoutParams3);
        this.g.setVisibility(8);
        this.c.setWebChromeClient(new a());
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WeiboSdkWebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j = 0;
                this.a.g();
                this.a.c.reload();
            }
        });
        this.f.setText(g.a(this, "A network error occurs, please tap the button to reload", "网络出错啦，请点击按钮重新加载", "網路出錯啦，請點擊按鈕重新載入"));
        this.e.setText(g.a(this, "channel_data_error", "重新加载", "重新載入"));
        return relativeLayout;
    }

    private void d() {
        if (!TextUtils.isEmpty(this.h.c().getSpecifyTitle())) {
            this.b.setText(this.h.c().getSpecifyTitle());
        }
        this.c.getSettings().setJavaScriptEnabled(true);
        this.c.getSettings().setSavePassword(false);
        this.c.getSettings().setUserAgentString(k.a(this, this.h.c().getAuthInfo().getAppKey()));
        this.c.requestFocus();
        this.c.setScrollBarStyle(0);
        if (VERSION.SDK_INT >= 11) {
            this.c.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            a(this.c);
        }
    }

    private void e() {
        finish();
    }

    public void a(WebView webView) {
        if (VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    private void f() {
        this.g.setVisibility(0);
        this.c.setVisibility(8);
    }

    private void g() {
        this.g.setVisibility(8);
        this.c.setVisibility(0);
    }

    public void a(WebView webView, String str, Bitmap bitmap) {
    }

    public void b(WebView webView, String str) {
        if (this.j == -1) {
            f();
        } else {
            g();
        }
    }

    public boolean a(WebView webView, String str) {
        return false;
    }

    public void a(WebView webView, int i, String str, String str2) {
        Object url = webView.getUrl();
        try {
            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(str2)) {
                Uri parse = Uri.parse(url);
                Uri parse2 = Uri.parse(str2);
                if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
                    this.j = -1;
                }
            }
        } catch (Exception e) {
        }
    }

    public void a() {
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.i.b()) {
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
