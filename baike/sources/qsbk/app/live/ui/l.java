package qsbk.app.live.ui;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;
import qsbk.app.core.web.ui.QsbkWebChromeClient;

class l extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ LiveBaseActivity b;

    l(LiveBaseActivity liveBaseActivity, ExposeApi exposeApi) {
        this.b = liveBaseActivity;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        this.a.callByCallInfo(str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length()));
        jsPromptResult.confirm();
        return true;
    }
}
