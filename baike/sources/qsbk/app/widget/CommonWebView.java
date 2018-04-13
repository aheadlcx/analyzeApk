package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import qsbk.app.ConfigManager;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.plugin.PluginProxy;
import qsbk.app.core.web.route.RouteProxy;
import qsbk.app.core.web.ui.WebInterface;

public class CommonWebView extends WebView {
    private static final String a = CommonWebView.class.getSimpleName();
    private BroadcastReceiver b;
    private Context c;
    private PluginProxy d;
    private RouteProxy e;
    private ExposeApi f;
    private WebInterface g;

    public CommonWebView(Context context) {
        this(context, null);
    }

    public CommonWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ExposeApi getExposeApi() {
        return this.f;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            return false;
        }
        String substring = str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length());
        if (this.f != null) {
            this.f.callByCallInfo(substring);
        }
        jsPromptResult.confirm();
        return true;
    }

    @SuppressLint({"JavascriptInterface"})
    public void initPluginProxy(WebInterface webInterface, HashMap<String, Class<? extends Plugin>> hashMap) {
        this.g = webInterface;
        this.d = new PluginProxy(this.g, this, hashMap);
        this.e = new RouteProxy(this);
        this.f = new ExposeApi(this.d, this.e);
        addJavascriptInterface(this.f, "_qbnative");
        addJavascriptInterface(this.f, "_qbnative_enable");
    }

    private void a() {
        setInitialScale(1);
        setVerticalScrollBarEnabled(true);
        requestFocusFromTouch();
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        try {
            Method method = WebSettings.class.getMethod("setNavDump", new Class[]{Boolean.TYPE});
            Log.d(a, "CordovaWebView is running on device made by: " + Build.MANUFACTURER);
            if (VERSION.SDK_INT < 11 && Build.MANUFACTURER.contains("HTC")) {
                method.invoke(settings, new Object[]{Boolean.valueOf(true)});
            }
        } catch (NoSuchMethodException e) {
            Log.d(a, "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8");
        } catch (IllegalArgumentException e2) {
            Log.d(a, "Doing the NavDump failed with bad arguments");
        } catch (IllegalAccessException e3) {
            Log.d(a, "This should never happen: IllegalAccessException means this isn't Android anymore");
        } catch (InvocationTargetException e4) {
            Log.d(a, "This should never happen: InvocationTargetException means this isn't Android anymore.");
        }
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        if (VERSION.SDK_INT > 15) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        String path = this.c.getApplicationContext().getDir("database", 0).getPath();
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(path);
        settings.setGeolocationDatabasePath(path);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(5242880);
        settings.setAppCachePath(this.c.getApplicationContext().getDir("database", 0).getPath());
        settings.setAppCacheEnabled(true);
        getSettings().getUserAgentString();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.b == null) {
            this.b = new bm(this);
            this.c.registerReceiver(this.b, intentFilter);
        }
        if (VERSION.SDK_INT >= 11) {
        }
        if (ConfigManager.getInstance().isTestOnlyChannel() && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    private void a(Context context) {
        this.c = context;
        a();
        b();
    }

    public void onDestroy() {
        if (this.b != null) {
            this.c.unregisterReceiver(this.b);
        }
        clearHistory();
        removeAllViews();
    }

    @TargetApi(16)
    private void b() {
        WebSettings settings = getSettings();
        if (VERSION.SDK_INT >= 16) {
            d();
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setAllowFileAccessFromFileURLs(true);
            return;
        }
        c();
    }

    private void c() {
        try {
            Field declaredField = WebView.class.getDeclaredField("mWebViewCore");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Method declaredMethod = obj.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[]{"http"});
            declaredMethod.invoke(obj, new Object[]{"https"});
        } catch (Exception e) {
            Log.d("wokao", "enablecrossdomain error");
            e.printStackTrace();
        }
    }

    private void d() {
        try {
            Field declaredField = WebView.class.getDeclaredField("mProvider");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Field declaredField2 = obj.getClass().getDeclaredField("mWebViewCore");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj.getClass().getDeclaredField("mNativeClass");
            declaredField3.setAccessible(true);
            obj = declaredField3.get(obj);
            Method declaredMethod = obj2.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", new Class[]{Integer.TYPE, String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj2, new Object[]{obj, "http"});
            declaredMethod.invoke(obj2, new Object[]{obj, "https"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableCookie(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(this.c);
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            instance.setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
    }

    public void enableLocalStorage() {
        getSettings().setDomStorageEnabled(true);
    }

    @TargetApi(11)
    public void enableHardwareAccelerated() {
        if (VERSION.SDK_INT >= 11) {
            setLayerType(2, null);
        }
    }
}
