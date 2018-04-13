package qsbk.app.activity.base;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

class ab extends WebChromeClient {
    final /* synthetic */ BaseWebviewActivity a;

    ab(BaseWebviewActivity baseWebviewActivity) {
        this.a = baseWebviewActivity;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
    }
}
