package com.budejie.www.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.g.e;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.PluginState;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.d.c;

public class DetailContentActivity extends SensorBaseActivity implements OnClickListener {
    DetailContentActivity a;
    Handler b = new Handler(this) {
        final /* synthetic */ DetailContentActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                this.a.e.loadUrl(this.a.j);
            }
        }
    };
    private Intent c;
    private ImageButton d;
    private WebView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private String i;
    private String j;
    private RelativeLayout k;
    private RelativeLayout l;
    private boolean m = false;
    private RelativeLayout n;
    private Button o;
    private String p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.detail);
        this.a = this;
        b();
        a();
        if (HomeGroup.o == 1) {
            sendBroadcast(new Intent("android.hide.sister.my.HANDLER_HIDE_MY_REDPACKET_TIPS"));
        }
    }

    public void a() {
        this.c = getIntent();
        this.i = this.c.getStringExtra("operator");
        this.j = this.c.getStringExtra("url");
        if (!(this.j == null || this.j.equals("") || this.j.equals("null"))) {
            this.j = NetWorkUtil.b(this.a, this.j);
        }
        if ("weibo".equals(this.i)) {
            this.f.setText(this.c.getStringExtra("name"));
        } else if ("help".equals(this.i)) {
            this.f.setText(R.string.help_page_title);
        } else if ("yingyongbao".equals(this.i)) {
            this.f.setText(R.string.yingyongbao);
        } else if (c.a.equals(this.i)) {
            this.f.setText(R.string.help_page_title);
        } else if ("html5_ad".equals(this.i)) {
            c();
            this.f.setText(this.c.getStringExtra("title"));
            this.m = true;
        } else if ("apply".equals(this.i)) {
            this.f.setText(R.string.apply_recommend);
        } else if ("htmlUrl".equals(this.i)) {
            this.f.setText(R.string.app_name);
        } else if ("my_coins".equals(this.i)) {
            this.p = this.c.getStringExtra("trade_history");
            this.f.setText(R.string.title_content_coins);
            this.g = (TextView) findViewById(R.id.title_right_btn);
            this.g.setVisibility(0);
            this.g.setText(R.string.title_content_coins_history);
            this.g.setOnClickListener(this);
            this.h = (TextView) findViewById(R.id.title_right_btn1);
            this.h.setVisibility(8);
            this.h.setText(R.string.title_content_coins_duihuan);
            this.h.setOnClickListener(this);
            if (!TextUtils.isEmpty(this.j)) {
                a(getApplicationContext(), this.j);
            }
        } else if ("coins_history".equals(this.i)) {
            this.f.setText(R.string.title_content_coins_history);
            if (!TextUtils.isEmpty(this.j)) {
                a(getApplicationContext(), this.j);
            }
        } else if ("trade_ruler".equals(this.i)) {
            this.f.setText(R.string.title_content_coins_ruler);
        } else if ("about".equals(this.i)) {
            this.f.setText(R.string.about_us);
        } else if ("coins_duiba".equals(this.i)) {
            this.f.setText(R.string.title_content_coins_duihuan);
            if (!TextUtils.isEmpty(this.j)) {
                a(getApplicationContext(), this.j);
            }
        }
        this.b.sendEmptyMessage(1);
    }

    public void a(Context context, String str) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this.e, true);
        }
        Object b = NetWorkUtil.b(context);
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split(h.b);
            for (String cookie : split) {
                instance.setCookie(str, cookie);
            }
        }
        CookieSyncManager.getInstance().sync();
    }

    public void b() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.d = (ImageButton) findViewById(R.id.title_left_close_btn);
        this.f = (TextView) findViewById(R.id.title_center_txt);
        this.e = (WebView) findViewById(R.id.webview);
        this.k = (RelativeLayout) findViewById(R.id.title);
        this.l = (RelativeLayout) findViewById(R.id.detail_layout);
        this.d.setOnClickListener(this);
        this.d.setVisibility(0);
        this.e.setVisibility(0);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setPluginState(PluginState.ON);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        this.e.requestFocus();
        this.e.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ DetailContentActivity b;

            {
                this.b = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                this.b.e.loadUrl(str);
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (!this.b.m) {
                }
            }
        });
        this.e.setDownloadListener(new e(this));
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4 && this.e.canGoBack()) {
            this.e.goBack();
            return true;
        }
        if (this.m) {
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onClick(View view) {
        if (view == this.d) {
            if (this.m) {
                finish();
            } else {
                finish();
            }
        } else if (view == this.o) {
            this.b.sendEmptyMessage(1);
        } else if (view == this.g) {
            MobclickAgent.onEvent((Context) this, "我的积分", "积分历史");
            if (!TextUtils.isEmpty(this.p)) {
                r0 = new Intent(this.a, DetailContentActivity.class);
                r0.putExtra("operator", "coins_history");
                r0.putExtra("url", this.p);
                this.a.startActivity(r0);
            }
        } else if (view == this.h) {
            MobclickAgent.onEvent((Context) this, "我的积分", "兑换");
            r0 = new Intent(this.a, DetailContentActivity.class);
            r0.putExtra("operator", "coins_duiba");
            r0.putExtra("url", "http://d.api.budejie.com/credit/duiba/login");
            this.a.startActivity(r0);
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.f.setTextColor(getResources().getColor(j.b));
        this.k.setBackgroundResource(j.a);
        this.l.setBackgroundResource(j.m);
    }

    private void c() {
        this.n = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.n.setVisibility(0);
        this.o = (Button) findViewById(R.id.refresh_btn);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(an.a(this.a, 30), an.a(this.a, 30));
        layoutParams.addRule(13);
        this.o.setLayoutParams(layoutParams);
        this.o.setOnClickListener(this.a);
        this.o.setBackgroundResource(R.drawable.detailcontent_home_set);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.e.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.e.goBack();
        return true;
    }
}
