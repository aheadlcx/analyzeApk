package qsbk.app.activity.base;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.CommonWebView;

public class BaseWebviewActivity extends BaseActionBarActivity {
    public static final String KEY_CUSTOMER_TITLE = "customer_title";
    public static final String KEY_CUSTOMER_URL = "customer_url";
    protected CommonWebView a;
    protected ProgressBar b;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        String stringExtra = getIntent().getStringExtra(KEY_CUSTOMER_TITLE);
        if (TextUtils.isEmpty(stringExtra)) {
            return "返回";
        }
        return stringExtra;
    }

    protected int a() {
        return R.layout.activity_base_webview_activity;
    }

    protected String g() {
        return getIntent().getStringExtra(KEY_CUSTOMER_URL);
    }

    protected void a(Bundle bundle) {
        this.a = (CommonWebView) findViewById(R.id.storeview);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().setAcceptCookie(true);
        setVisible(true);
        setActionbarBackable();
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("cookie");
        String g = g();
        this.a.enableCookie(g, sharePreferencesValue);
        this.a.enableLocalStorage();
        this.a.setWebChromeClient(i());
        this.a.setWebViewClient(new WebViewClient());
        if (VERSION.SDK_INT >= 21) {
            this.a.getSettings().setMixedContentMode(0);
        }
        a(this.a);
        this.a.loadUrl(g);
    }

    protected void a(CommonWebView commonWebView) {
    }

    protected WebChromeClient i() {
        return new ab(this);
    }

    public void onBackPressed() {
        if (this.a.canGoBack()) {
            this.a.goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        setVisible(false);
        this.a.onDestroy();
        super.onDestroy();
    }
}
