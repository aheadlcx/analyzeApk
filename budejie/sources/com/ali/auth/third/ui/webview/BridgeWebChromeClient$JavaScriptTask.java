package com.ali.auth.third.ui.webview;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.ali.auth.third.core.trace.SDKLogger;
import com.tencent.stat.DeviceInfo;

class BridgeWebChromeClient$JavaScriptTask implements Runnable {
    public String script;
    public WebView webView;

    public BridgeWebChromeClient$JavaScriptTask(WebView webView, String str) {
        this.webView = webView;
        this.script = str;
    }

    @TargetApi(19)
    public void run() {
        try {
            if (BridgeWebChromeClient.access$000()) {
                try {
                    this.webView.evaluateJavascript(this.script, null);
                    return;
                } catch (Throwable e) {
                    SDKLogger.e(DeviceInfo.TAG_IMEI, "fail to evaluate the script " + e.getMessage(), e);
                }
            }
            String str = "javascript:" + this.script;
            if (this.webView instanceof AuthWebView) {
                ((AuthWebView) this.webView).loadUrl(str);
            } else {
                this.webView.loadUrl(str);
            }
        } catch (Exception e2) {
        }
    }
}
