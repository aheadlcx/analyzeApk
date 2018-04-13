package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class a extends WebChromeClient {
    final /* synthetic */ About a;

    a(About about) {
        this.a = about;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        new Builder(this.a).setTitle("温馨提示：").setMessage(str2).setPositiveButton(17039370, new b(this, jsResult)).setCancelable(false).create().show();
        return true;
    }
}
