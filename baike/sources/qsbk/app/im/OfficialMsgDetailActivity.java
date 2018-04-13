package qsbk.app.im;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings.RenderPriority;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.cafe.Jsnativeinteraction.ui.QsbkWebViewClient;
import qsbk.app.cafe.Jsnativeinteraction.ui.WebActivity;
import qsbk.app.cafe.plugin.JumpPlugin;
import qsbk.app.core.web.ui.QsbkWebView;
import qsbk.app.utils.UIHelper;

public class OfficialMsgDetailActivity extends WebActivity {
    private static final String f = OfficialMsgDetailActivity.class.getSimpleName();
    protected String a;
    protected String b;
    protected Intent c;
    protected FrameLayout d;
    protected FrameLayout e;
    private boolean g = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return q();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.OfficialMsg_Night);
        } else {
            setTheme(R.style.OfficialMsg);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        h();
        i();
        f();
        j();
        b(this.b);
        a(this.a);
    }

    protected void i() {
        this.m = (ProgressBar) findViewById(R.id.progressbar);
        this.k = (QsbkWebView) findViewById(R.id.webview);
        if (UIHelper.isNightTheme()) {
            View findViewById = findViewById(R.id.webview_mask);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        this.d = (FrameLayout) findViewById(R.id.fl_video);
        this.e = (FrameLayout) findViewById(R.id.id_fake_actionbar);
        this.c = getIntent();
        this.a = this.c.getStringExtra("url");
        this.b = this.c.getStringExtra("title");
    }

    protected int a() {
        return R.layout.official_msg_detail_activity;
    }

    protected void b(String str) {
        if (str != null) {
            if (str.length() > 14) {
                str = str.substring(0, 14) + "...";
            }
            CharSequence charSequence = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str;
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(charSequence);
        }
    }

    protected String q() {
        return null;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void j() {
        this.k.init(this, this.o);
        this.k.getSettings().setJavaScriptEnabled(true);
        this.k.getSettings().setDomStorageEnabled(true);
        this.k.getSettings().setLoadWithOverviewMode(true);
        this.k.getSettings().setUseWideViewPort(true);
        this.k.setWebViewClient(new QsbkWebViewClient());
        this.k.setVerticalScrollBarEnabled(true);
        this.k.requestFocusFromTouch();
        String path = getApplicationContext().getDir("database", 0).getPath();
        this.k.getSettings().setDatabaseEnabled(true);
        this.k.getSettings().setDatabasePath(path);
        this.k.getSettings().setRenderPriority(RenderPriority.HIGH);
        this.k.setInitialScale(1);
        this.k.setVerticalScrollBarEnabled(true);
        this.k.requestFocusFromTouch();
        this.k.setWebChromeClient(new il(this));
    }

    protected void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        CookieSyncManager.createInstance(this);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.removeSessionCookie();
        if (QsbkApp.currentUser == null) {
            this.k.loadUrl(str);
            CookieSyncManager.getInstance().sync();
            return;
        }
        instance.setCookie(str, "app_token=" + QsbkApp.currentUser.token);
        CookieSyncManager.getInstance().sync();
        Map hashMap = new HashMap();
        hashMap.put("Qbtoken", QsbkApp.currentUser.token);
        hashMap.put("user_id", QsbkApp.currentUser.userId);
        this.k.loadUrl(str, hashMap);
    }

    protected void f() {
        this.o.put("jump", JumpPlugin.class);
    }
}
