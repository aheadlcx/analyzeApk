package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.login.LoginConstants;
import com.budejie.www.R;
import com.budejie.www.g.e;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.pro.x;
import java.util.Timer;
import java.util.TimerTask;

public class AccountActivity extends OauthWeiboBaseAct implements OnClickListener {
    private String a = "AccountActivity";
    private AccountActivity b;
    private Toast c;
    private FrameLayout d;
    private LinearLayout e;
    private TextView f;
    private TextView g;
    private WebView h;
    private String i;
    private String j;
    private String k;
    private long l;
    private String m;
    private Intent n;
    private Dialog o;
    private SharedPreferences p;
    private RelativeLayout q;
    private boolean r = false;
    private WebChromeClient s = new WebChromeClient(this) {
        final /* synthetic */ AccountActivity a;

        {
            this.a = r1;
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            this.a.o.dismiss();
        }
    };
    private WebViewClient t = new WebViewClient(this) {
        final /* synthetic */ AccountActivity b;

        {
            this.b = r1;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.contains(x.aF)) {
                if ("sina".equals(this.b.m)) {
                    this.b.b.setResult(710);
                } else if ("tenct".equals(this.b.m)) {
                    this.b.b.setResult(711);
                }
                this.b.b.finish();
            } else if ("tenct".equals(this.b.m)) {
                webView.loadUrl(str);
                if (str.startsWith("sister")) {
                    this.b.u.sendMessage(this.b.u.obtainMessage(1, str));
                }
            } else if (str.contains("state=baisibudejie")) {
                webView.loadUrl(str);
                this.b.o.dismiss();
            } else if (str.contains("http://qzs.qq.com/open/mobile/login")) {
                this.b.a(str);
            } else if (str.startsWith("http://admin.spriteapp.com/weibo/response.php")) {
                this.b.b(str);
            } else if (str.startsWith("sister")) {
                this.b.u.sendMessage(this.b.u.obtainMessage(1, str));
            } else {
                this.b.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
            return true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (str.contains(x.aF)) {
                if ("sina".equals(this.b.m)) {
                    this.b.b.setResult(710);
                } else if ("tenct".equals(this.b.m)) {
                    this.b.b.setResult(711);
                }
                this.b.b.finish();
            } else if (str.startsWith("http://admin.spriteapp.com/weibo/response.php")) {
                this.b.b(str);
                return;
            } else if (str.contains("http://qzs.qq.com/open/mobile/login")) {
                this.b.a(str);
                return;
            }
            super.onPageStarted(webView, str, bitmap);
        }
    };
    private Handler u = new Handler(this) {
        final /* synthetic */ AccountActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                String str = (String) message.obj;
                Intent intent;
                if ("sina".equals(this.a.m)) {
                    intent = new Intent();
                    intent.setData(Uri.parse(str));
                    this.a.b.setResult(710, intent);
                } else if ("tenct".equals(this.a.m)) {
                    intent = new Intent();
                    intent.setData(Uri.parse(str));
                    this.a.b.setResult(711, intent);
                }
                this.a.b.finish();
            } else if (message.what == 2) {
                this.a.d();
            } else if (message.what == 3) {
                this.a.c = an.a(this.a.b, this.a.b.getString(R.string.tenct_oauth_failed), -1);
                this.a.c.show();
                new Timer().schedule(new TimerTask(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b.setResult(710);
                        this.a.a.b.finish();
                    }
                }, 2000);
            } else if (message.what == 4) {
                this.a.c = an.a(this.a.b, this.a.b.getString(R.string.tenct_oauth_failed), -1);
                this.a.c.show();
                new Timer().schedule(new TimerTask(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b.setResult(711);
                        this.a.a.b.finish();
                    }
                }, 2000);
            } else if (message.what == 5) {
            } else {
                if (message.what == 6) {
                    this.a.c();
                } else if (message.what == 7) {
                    this.a.c = an.a(this.a.b, this.a.b.getString(R.string.weibo_oauth_failed), -1);
                    this.a.c.show();
                    this.a.b.finish();
                }
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.account);
        this.b = this;
        a();
        this.l = System.currentTimeMillis();
        if (an.a((Context) this)) {
            this.u.sendEmptyMessage(2);
            return;
        }
        this.c = an.a((Activity) this, getString(R.string.nonet), -1);
        this.c.show();
        if ("sina".equals(this.m)) {
            this.b.setResult(710);
        } else if ("tenct".equals(this.m)) {
            this.b.setResult(711);
        }
        finish();
    }

    private void a() {
        this.q = (RelativeLayout) findViewById(R.id.title);
        this.o = new Dialog(this, R.style.dialogTheme);
        this.o.setContentView(R.layout.loaddialog);
        this.d = (FrameLayout) findViewById(R.id.container);
        this.e = (LinearLayout) findViewById(R.id.left_layout);
        this.f = (TextView) findViewById(R.id.title_left_btn);
        this.g = (TextView) findViewById(R.id.title_center_txt);
        this.h = (WebView) findViewById(R.id.accountWeb);
        this.e.setVisibility(0);
        this.h.setVisibility(0);
        this.h.getSettings().setJavaScriptEnabled(true);
        this.h.setWebViewClient(this.t);
        this.h.setWebChromeClient(this.s);
        this.h.setDownloadListener(new e(this));
        this.f.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.p = getSharedPreferences("weiboprefer", 0);
        this.n = getIntent();
        this.m = this.n.getStringExtra("weibo");
        if (!isFinishing()) {
            this.o.show();
        }
        if ("sina".equals(this.m)) {
            this.g.setText(R.string.bind_sinaweibo);
        } else if ("tenct".equals(this.m)) {
            this.g.setText(R.string.bind_tenctweibo);
        }
        this.i = getIntent().getStringExtra("txt");
        this.j = getIntent().getStringExtra("url");
        this.k = getIntent().getStringExtra("title");
    }

    private void b() {
        if ("sina".equals(this.m)) {
            this.u.sendEmptyMessage(6);
        } else if ("tenct".equals(this.m)) {
            auth(Long.valueOf(Util.getConfig().getProperty("APP_KEY")).longValue(), Util.getConfig().getProperty("APP_KEY_SEC"));
        }
    }

    private void c() {
    }

    private void d() {
        new Thread(this) {
            final /* synthetic */ AccountActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.b();
                } catch (Exception e) {
                    this.a.u.sendEmptyMessage(7);
                }
            }
        }.start();
    }

    private void a(String str) {
        String[] split = str.split("&");
        Bundle bundle = new Bundle();
        if (split != null && split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].split(LoginConstants.EQUAL);
                bundle.putString(split2[0], split2[1]);
            }
        }
        this.u.sendMessage(this.u.obtainMessage(1, bundle));
    }

    private void b(String str) {
        this.u.sendMessage(this.u.obtainMessage(1, str));
    }

    public void onClick(View view) {
        if (view == this.f || view == this.e) {
            if ("sina".equals(this.m)) {
                setResult(710);
            } else if ("tenct".equals(this.m)) {
                setResult(711);
            }
            finish();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if ("sina".equals(this.m)) {
            setResult(710);
        } else if ("tenct".equals(this.m)) {
            setResult(711);
        }
        finish();
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.g.setTextColor(getResources().getColor(j.b));
        this.q.setBackgroundResource(j.a);
        onRefreshTitleFontTheme(this.f, true);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            setResult(711);
            this.b.finish();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.r) {
            setResult(1);
            this.b.finish();
            return;
        }
        this.r = true;
    }

    public void bindTencent() {
        super.bindTencent();
        setResult(711);
        this.b.finish();
    }
}
