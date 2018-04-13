package qsbk.app.im;

import android.app.Activity;
import android.webkit.JsPromptResult;
import android.webkit.WebView;
import android.widget.FrameLayout;
import qsbk.app.core.web.js.IExposeApi;
import qsbk.app.widget.FullVideoChromeClient;

class il extends FullVideoChromeClient {
    final IExposeApi a = this.b.k.getExposeApi();
    final /* synthetic */ OfficialMsgDetailActivity b;

    il(OfficialMsgDetailActivity officialMsgDetailActivity) {
        this.b = officialMsgDetailActivity;
    }

    public Activity getActivity() {
        return this.b;
    }

    public WebView getWebView() {
        return this.b.k;
    }

    public FrameLayout getVideoContaner() {
        return this.b.d;
    }

    public void setActionbarVisible(boolean z) {
        if (z) {
            this.b.e.setVisibility(0);
        } else {
            this.b.e.setVisibility(4);
        }
    }

    public void onProgressChanged(WebView webView, int i) {
        this.b.m.setProgress(i);
        this.b.m.setVisibility(i == 100 ? 4 : 0);
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        String substring = str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length());
        if (this.a != null) {
            this.b.g = true;
            this.a.callByCallInfo(substring);
        }
        jsPromptResult.confirm();
        return true;
    }
}
