package qsbk.app.game;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import qsbk.app.core.web.js.IExposeApi;

class j extends WebChromeClient {
    final /* synthetic */ GameIndexActivity a;

    j(GameIndexActivity gameIndexActivity) {
        this.a = gameIndexActivity;
    }

    public void onProgressChanged(WebView webView, int i) {
        this.a.b.setProgress(i);
        this.a.b.setVisibility(i == 100 ? 8 : 0);
        super.onProgressChanged(webView, i);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        this.a.d.callByCallInfo(str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length()));
        jsPromptResult.confirm();
        return true;
    }
}
