package qsbk.app.im;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.FrameLayout;
import qsbk.app.widget.FullVideoChromeClient;

class dd extends FullVideoChromeClient {
    final /* synthetic */ GameWebViewActivity a;

    dd(GameWebViewActivity gameWebViewActivity) {
        this.a = gameWebViewActivity;
    }

    public Activity getActivity() {
        return this.a;
    }

    public WebView getWebView() {
        return this.a.k;
    }

    public FrameLayout getVideoContaner() {
        return this.a.d;
    }

    public void setActionbarVisible(boolean z) {
        if (z) {
            this.a.e.setVisibility(0);
        } else {
            this.a.e.setVisibility(4);
        }
    }

    public void onProgressChanged(WebView webView, int i) {
        this.a.m.setProgress(i);
        this.a.m.setVisibility(i == 100 ? 4 : 0);
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.a.b(str);
    }
}
