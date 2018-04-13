package qsbk.app.core.web.ui;

import android.webkit.JsPromptResult;
import android.webkit.WebView;
import org.json.JSONObject;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.js.IExposeApi;

class g extends QsbkWebChromeClient {
    final /* synthetic */ ExposeApi a;
    final /* synthetic */ WebActivity b;

    g(WebActivity webActivity, ExposeApi exposeApi) {
        this.b = webActivity;
        this.a = exposeApi;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith(IExposeApi.PREFIX_JSPROMPT_CALL)) {
            String substring = str2.substring(IExposeApi.PREFIX_JSPROMPT_CALL.length());
            try {
                String optString = new JSONObject(substring).optString("method");
                if ("finish".equals(optString)) {
                    this.b.k = true;
                } else if ("close".equals(optString)) {
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
        if (i < 100 && this.b.f.getVisibility() == 8) {
            this.b.f.setVisibility(0);
            this.b.j.lock();
        }
        this.b.f.setProgress(i);
        if (i == 100) {
            this.b.f.setVisibility(8);
            this.b.j.unlock();
        }
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.b.setTitle(str);
    }
}
