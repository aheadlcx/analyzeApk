package qsbk.app.push;

import android.app.Activity;
import android.webkit.JsPromptResult;
import android.webkit.WebView;
import android.widget.FrameLayout;
import qsbk.app.widget.FullVideoChromeClient;

class i extends FullVideoChromeClient {
    final /* synthetic */ PushMessageShow a;

    i(PushMessageShow pushMessageShow) {
        this.a = pushMessageShow;
    }

    public Activity getActivity() {
        return this.a;
    }

    public WebView getWebView() {
        return this.a.a;
    }

    public FrameLayout getVideoContaner() {
        return this.a.b;
    }

    public void setActionbarVisible(boolean z) {
        if (z) {
            this.a.getSupportActionBar().show();
        } else {
            this.a.getSupportActionBar().hide();
        }
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (this.a.a.onJsPrompt(webView, str, str2, str3, jsPromptResult)) {
            return true;
        }
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }
}
