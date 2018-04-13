package com.tencent.open;

import android.os.Build.VERSION;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.a.f;

class d extends WebChromeClient {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage == null) {
            return false;
        }
        f.c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + consoleMessage.message() + " -- From  111 line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        if (VERSION.SDK_INT > 7) {
            this.a.a(consoleMessage == null ? "" : consoleMessage.message());
        }
        return true;
    }

    public void onConsoleMessage(String str, int i, String str2) {
        f.c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + str + " -- From 222 line " + i + " of " + str2);
        if (VERSION.SDK_INT == 7) {
            this.a.a(str);
        }
    }
}
