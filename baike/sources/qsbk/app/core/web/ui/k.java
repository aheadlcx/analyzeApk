package qsbk.app.core.web.ui;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;

class k extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ WebFragment b;

    k(WebFragment webFragment, ExposeApi exposeApi) {
        this.b = webFragment;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            this.a.callByCallInfo(str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length()));
            jsPromptResult.confirm();
            return true;
        }
        LogUtils.d("message.length:" + str2.length());
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }
}
