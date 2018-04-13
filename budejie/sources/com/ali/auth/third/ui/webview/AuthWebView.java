package com.ali.auth.third.ui.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.ali.auth.third.core.util.CommonUtils;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"SetJavaScriptEnabled"})
public class AuthWebView extends WebView {
    private static final String TAG = AuthWebView.class.getSimpleName();
    private String appCacheDir;
    private HashMap<String, String> contextParameters = new HashMap();
    private Map<String, Object> nameToObj = new HashMap();

    public AuthWebView(Context context) {
        super(context);
        initSettings(context, true);
    }

    public AuthWebView(Context context, boolean z) {
        super(context);
        initSettings(context, z);
    }

    public AuthWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initSettings(context, true);
    }

    public final void addJavascriptInterface(Object obj, String str) {
    }

    @TargetApi(21)
    private void initSettings(Context context, boolean z) {
        WebSettings settings = getSettings();
        try {
            settings.setJavaScriptEnabled(true);
        } catch (Exception e) {
        }
        settings.setSavePassword(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setDomStorageEnabled(true);
        this.appCacheDir = context.getApplicationContext().getDir("cache", 0).getPath();
        settings.setAppCachePath(this.appCacheDir);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        if (CommonUtils.isNetworkAvailable(context)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setBuiltInZoomControls(false);
        try {
            removeJavascriptInterface("searchBoxJavaBridge_");
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
                settings.setMixedContentMode(0);
            } catch (Throwable th) {
            }
        }
    }

    public final void addBridgeObject(String str, Object obj) {
        this.nameToObj.put(str, obj);
    }

    public Object getBridgeObj(String str) {
        return this.nameToObj.get(str);
    }

    public HashMap<String, String> getContextParameters() {
        return this.contextParameters;
    }
}
