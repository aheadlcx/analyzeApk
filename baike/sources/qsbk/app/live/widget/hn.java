package qsbk.app.live.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import org.json.JSONObject;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;
import qsbk.app.core.web.ui.QsbkWebChromeClient;

class hn extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ LiveStartUpDialog b;

    hn(LiveStartUpDialog liveStartUpDialog, ExposeApi exposeApi) {
        this.b = liveStartUpDialog;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (!str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        String substring = str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length());
        try {
            if ("close".equals(new JSONObject(substring).optString("method"))) {
                this.b.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a.callByCallInfo(substring);
        jsPromptResult.confirm();
        return true;
    }
}
