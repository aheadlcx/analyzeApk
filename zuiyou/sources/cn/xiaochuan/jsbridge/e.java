package cn.xiaochuan.jsbridge;

import android.content.Intent;
import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.a.a.a.b;

public abstract class e extends WebChromeClient {
    private ValueCallback<Uri> a;
    private int b = 7;

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return super.onConsoleMessage(consoleMessage);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsConfirm(webView, str, str2, jsResult);
    }

    public void a(int i, Intent intent) {
        if (this.a != null) {
            Object data = (intent == null || i != -1) ? null : intent.getData();
            this.a.onReceiveValue(data);
            this.a = null;
        }
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i > 70 && i / 10 > this.b) {
            this.b++;
            b.a(webView, "ZuiyouJSBridge.min.js");
        }
    }
}
