package qsbk.app.activity.security;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.sys.a;

class p extends WebViewClient {
    final /* synthetic */ FindPasswordActivity a;

    p(FindPasswordActivity findPasswordActivity) {
        this.a = findPasswordActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str + a.b + this.a.a);
        return false;
    }
}
