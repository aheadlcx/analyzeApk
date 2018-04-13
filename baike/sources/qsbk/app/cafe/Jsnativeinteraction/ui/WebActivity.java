package qsbk.app.cafe.Jsnativeinteraction.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.ConfigManager;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.route.IWebResponse;
import qsbk.app.core.web.ui.QsbkWebView;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.utils.UIHelper;

public abstract class WebActivity extends BaseActionBarActivity implements WebInterface {
    private static final String a = WebActivity.class.getSimpleName();
    private Plugin b;
    protected QsbkWebView k;
    protected View l;
    protected ProgressBar m;
    protected FrameLayout n;
    protected HashMap<String, Class<? extends Plugin>> o = new HashMap();

    protected abstract void f();

    protected /* synthetic */ CharSequence getCustomTitle() {
        return q();
    }

    protected int a() {
        return R.layout.cafe_web_layout;
    }

    protected String q() {
        return null;
    }

    protected WebViewClient g() {
        return new QsbkWebViewClient();
    }

    protected void r() {
    }

    protected void s() {
    }

    protected void t() {
    }

    protected void a(Bundle bundle) {
        f();
        this.k = (QsbkWebView) findViewById(R.id.webview);
        this.l = findViewById(R.id.webview_mask);
        this.l.setVisibility(UIHelper.isNightTheme() ? 0 : 4);
        this.m = (ProgressBar) findViewById(R.id.progressbar);
        this.n = (FrameLayout) findViewById(R.id.id_fake_actionbar);
        if (needShowActionBar()) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
        this.k.init(this, this.o);
        this.k.setWebViewClient(g());
        if (ConfigManager.getInstance().isTestOnlyChannel() && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.k.setWebChromeClient(new a(this, this.k.getExposeApi()));
        this.k.setDownloadListener(new SimpleWebViewDownloadListener(this.k));
    }

    protected void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.k.loadUrl(str);
    }

    protected void a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.k.loadUrl(str, map);
    }

    protected void onDestroy() {
        try {
            if (this.k != null) {
                this.k.loadUrl("");
            }
            this.k.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i) {
        if (i != 4 || this.k == null || !this.k.canGoBack()) {
            return false;
        }
        this.k.goBack();
        return true;
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.b = plugin;
        startActivityForResult(intent, i);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.b != null) {
            this.b.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void setFocusPlugin(Plugin plugin) {
        this.b = plugin;
    }

    public Activity getCurActivity() {
        return this;
    }

    public void reqWeb(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (this.k != null) {
            this.k.reqWeb(str, str2, str3, iWebResponse);
        }
    }

    public void reqCallback(String str, String str2) {
        if (this.k != null) {
            this.k.reqCallback(null, str, str2);
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.k, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            Class.forName("android.webkit.WebView").getMethod("onResume", (Class[]) null).invoke(this.k, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
        }
    }
}
