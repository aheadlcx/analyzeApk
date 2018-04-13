package qsbk.app.core.web.ui;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import org.json.JSONObject;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;

class c extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ FullScreenWebActivity b;

    c(FullScreenWebActivity fullScreenWebActivity, ExposeApi exposeApi) {
        this.b = fullScreenWebActivity;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            String substring = str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length());
            try {
                JSONObject jSONObject = new JSONObject(substring);
                String optString = jSONObject.optString("method");
                String optString2 = jSONObject.optString("modul");
                if ("finish".equals(optString)) {
                    this.b.f = true;
                } else if ("close".equals(optString2)) {
                    jsPromptResult.confirm();
                    this.b.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a.callByCallInfo(substring);
            jsPromptResult.confirm();
            return true;
        }
        LogUtils.d("message.length:" + str2.length());
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public void onProgressChanged(WebView webView, int i) {
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.b.setTitle(str);
    }
}
