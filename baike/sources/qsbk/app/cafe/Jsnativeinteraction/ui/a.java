package qsbk.app.cafe.Jsnativeinteraction.ui;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;
import qsbk.app.core.web.ui.QsbkWebChromeClient;
import qsbk.app.utils.LogUtil;

class a extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ WebActivity b;

    a(WebActivity webActivity, ExposeApi exposeApi) {
        this.b = webActivity;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            this.a.callByCallInfo(str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length()));
            jsPromptResult.confirm();
            return true;
        }
        LogUtil.d("message.length:" + str2.length());
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public void onProgressChanged(WebView webView, int i) {
        this.b.m.setProgress(i);
        this.b.m.setVisibility(i == 100 ? 4 : 0);
        if (i <= 10) {
            this.b.r();
        } else if (i < 100) {
            this.b.s();
        } else {
            this.b.t();
        }
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.b.setTitle(str);
    }
}
