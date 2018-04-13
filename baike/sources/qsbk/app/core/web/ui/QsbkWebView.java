package qsbk.app.core.web.ui;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.plugin.PluginProxy;
import qsbk.app.core.web.route.IWebResponse;
import qsbk.app.core.web.route.RouteProxy;
import qsbk.app.utils.RemixUtil;

public class QsbkWebView extends WebView {
    private static final String a = QsbkWebView.class.getSimpleName();
    private WebInterface b;
    private PluginProxy c;
    private RouteProxy d;
    private ExposeApi e;
    private BroadcastReceiver f;

    public QsbkWebView(WebInterface webInterface, Map<String, Class<? extends Plugin>> map) {
        this(webInterface, null, (Map) map);
    }

    public QsbkWebView(WebInterface webInterface, AttributeSet attributeSet, Map<String, Class<? extends Plugin>> map) {
        this(webInterface, attributeSet, 0, map);
    }

    public QsbkWebView(WebInterface webInterface, AttributeSet attributeSet, int i, Map<String, Class<? extends Plugin>> map) {
        super(webInterface.getCurActivity(), attributeSet, i);
        initSettings();
        init(webInterface, map);
    }

    public QsbkWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initSettings();
    }

    public QsbkWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initSettings();
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi", "JavascriptInterface"})
    public void init(WebInterface webInterface, Map<String, Class<? extends Plugin>> map) {
        this.b = webInterface;
        this.c = new PluginProxy(this.b, this, map);
        this.d = new RouteProxy(this);
        this.e = new ExposeApi(this.c, this.d);
        addJavascriptInterface(this.e, "exposeApi");
    }

    public void initSettings() {
        setInitialScale(1);
        setVerticalScrollBarEnabled(true);
        requestFocusFromTouch();
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(-1);
        settings.setTextZoom(100);
        if (getSettings() != null) {
            getSettings().setTextZoom(100);
        }
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
        String path = getContext().getApplicationContext().getDir("database", 0).getPath();
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(path);
        settings.setGeolocationDatabasePath(path);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(5242880);
        settings.setAppCachePath(getContext().getApplicationContext().getDir("database", 0).getPath());
        settings.setAppCacheEnabled(true);
        settings.setUserAgentString(settings.getUserAgentString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getCustomUserAgent());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.f == null) {
            this.f = new e(this);
            getContext().registerReceiver(this.f, intentFilter);
        }
        if (VERSION.SDK_INT >= 11) {
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (AppUtils.getInstance().isTestOnlyChannel() && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        generalEnableCrossDomain();
    }

    public String getCustomUserAgent() {
        String str = "default";
        String packageName = getContext().getPackageName();
        if (packageName != null) {
            if (packageName.startsWith("qsbk.app.doll")) {
                str = "Doll";
            } else if (packageName.startsWith(RemixUtil.REMIX_PACKAGE_NAME)) {
                str = "Remix";
            } else {
                str = "qiubai";
            }
        }
        return str + MqttTopic.TOPIC_LEVEL_SEPARATOR + DeviceUtils.getAppVersion() + "." + DeviceUtils.getAPPVersionCode();
    }

    public ExposeApi getExposeApi() {
        return this.e;
    }

    public void reqWeb(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (this.d != null) {
            this.d.reqWeb(str, str2, str3, iWebResponse);
        }
    }

    public void reqCallback(String str, String str2, String str3) {
        loadUrl("javascript:Interface.onNativeResp('" + str2 + "'" + Constants.ACCEPT_TIME_SEPARATOR_SP + "'" + str3 + "'" + ")");
    }

    public void onDestroy() {
        if (this.f != null) {
            getContext().unregisterReceiver(this.f);
            this.f = null;
        }
        this.c.onDestroy();
        clearHistory();
        removeAllViews();
    }

    @SuppressLint({"NewApi"})
    public void generalEnableCrossDomain() {
        WebSettings settings = getSettings();
        if (VERSION.SDK_INT >= 16) {
            enablecrossdomain41();
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setAllowFileAccessFromFileURLs(true);
            return;
        }
        enableCrossDomain();
    }

    public void enableCrossDomain() {
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

    public void enablecrossdomain41() {
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
            Log.d("wokao", "enablecrossdomain error");
            e.printStackTrace();
        }
    }
}
