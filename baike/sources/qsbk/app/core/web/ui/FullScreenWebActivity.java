package qsbk.app.core.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.R;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.web.NativeJsPluginManager;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.route.IWebResponse;
import qsbk.app.live.share.LiveShareActivity;

public class FullScreenWebActivity extends BaseActivity implements WebInterface {
    private static final String c = FullScreenWebActivity.class.getSimpleName();
    private static long g = 0;
    protected QsbkWebView a;
    protected String b;
    private Plugin d;
    private boolean e = false;
    private boolean f = false;

    public static void launch(Context context, String str, long j) {
        launch(context, str);
        g = j;
    }

    public static void launch(Context context, String str) {
        launch(context, str, null);
    }

    public static void launch(Context context, String str, String str2) {
        Intent intent = new Intent(context, FullScreenWebActivity.class);
        intent.putExtra("link", str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        if (g != 0) {
            postDelayed(new a(this), g);
        }
    }

    protected int getLayoutId() {
        return R.layout.core_fullscreen_webview_activity;
    }

    protected void initView() {
        setOnUpClickListener(new b(this));
        this.a = (QsbkWebView) findViewById(R.id.webview);
        this.a.init(this, NativeJsPluginManager.getInstance().getPluginMap());
        this.a.setWebViewClient(a());
        this.a.setBackgroundColor(0);
        if (AppUtils.getInstance().isTestOnlyChannel() && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.a.setWebChromeClient(new c(this, this.a.getExposeApi()));
        this.a.addJavascriptInterface(new FullScreenWebActivity$GetWebShareInfo(this), "GetWebShareInfo");
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getStringExtra("link");
        }
        a(this.b);
    }

    protected WebViewClient a() {
        return new d(this);
    }

    private boolean b() {
        try {
            String host = new URL(this.b).getHost();
            if (host.endsWith(UrlConstants.QSBK_DOMAIN) || host.endsWith(UrlConstants.DOMAIN)) {
                return true;
            }
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void a(String str) {
        a(str, null);
    }

    protected void a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.e("WebActivity", "url is empty!!!");
            finish();
            return;
        }
        if (map == null) {
            map = new HashMap();
        }
        map.put("app_ver", DeviceUtils.getAppVersion());
        map.put("device_info", DeviceUtils.getDeviceIdInfo());
        map.put("model", Build.FINGERPRINT);
        if (AppUtils.getInstance().getUserInfoProvider().isLogin() && b()) {
            map.put("qbtoken", AppUtils.getInstance().getUserInfoProvider().getToken());
            map.put("user_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
            map.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
            map.put("app", Constants.APP_FLAG + "");
        }
        this.a.loadUrl(str, map);
    }

    protected void onPause() {
        super.onPause();
        try {
            if (this.a != null) {
                this.a.getClass().getMethod("onPause", new Class[0]).invoke(this.a, (Object[]) null);
                this.e = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            if (this.e) {
                if (this.a != null) {
                    this.a.getClass().getMethod("onResume", new Class[0]).invoke(this.a, (Object[]) null);
                }
                this.e = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        CookieSyncManager.createInstance(this);
        CookieManager instance = CookieManager.getInstance();
        if (VERSION.SDK_INT >= 21) {
            instance.removeAllCookies(null);
        } else {
            instance.removeAllCookie();
        }
        if (this.a != null) {
            this.a.onDestroy();
            ((LinearLayout) findViewById(R.id.ll_web)).removeView(this.a);
            this.a.destroy();
        }
        g = 0;
        super.onDestroy();
    }

    public boolean onKeyDown(int i) {
        if (i != 4 || this.a == null || !this.a.canGoBack()) {
            return false;
        }
        this.a.goBack();
        return true;
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.d = plugin;
        startActivityForResult(intent, i);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == LiveShareActivity.SHARE_NO_LOGIN && i2 == -1) {
            a(this.a.getUrl());
        }
        if (this.d != null) {
            this.d.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void setFocusPlugin(Plugin plugin) {
        this.d = plugin;
    }

    public Activity getCurActivity() {
        return this;
    }

    public void reqWeb(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (this.a != null) {
            this.a.reqWeb(str, str2, str3, iWebResponse);
        }
    }

    public void reloadUrl() {
        a(this.b);
    }

    public void reloadUrl(String str) {
        a(str);
    }

    public String getUrl() {
        return this.b;
    }

    public void onBackPressed() {
        if (this.f || this.a == null || !this.a.canGoBack()) {
            finish();
        } else {
            this.a.goBack();
        }
    }
}
