package cn.tatagou.sdk.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.util.p;
import java.util.Map;

public class TtgWebView extends WebView {
    private boolean a = false;

    public TtgWebView(Context context) {
        super(context);
        b();
        a();
    }

    public TtgWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
        a();
    }

    public TtgWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
        a();
    }

    private void a() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        WebSettings settings = getSettings();
        settings.setDomStorageEnabled(true);
        String path = getContext().getDir("cache", 0).getPath();
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(path);
        if (p.isNetworkOpen(TtgSDK.getContext())) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
    }

    public void destroy() {
        this.a = true;
        super.destroy();
        Log.d("TTG", "loadUrl: 4444444 destroy");
    }

    public void loadUrl(String str) {
        if (!this.a) {
            Log.d("TTG", "loadUrl: 111111111111111");
            super.loadUrl(str);
        }
    }

    public void loadUrl(String str, Map<String, String> map) {
        if (!this.a) {
            Log.d("TTG", "loadUrl: 333333333333333333");
            super.loadUrl(str, map);
        }
    }
}
