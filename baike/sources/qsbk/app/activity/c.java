package qsbk.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class c extends WebViewClient {
    final /* synthetic */ About a;

    c(About about) {
        this.a = about;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtil.d("load local html override: " + this.a.b.getDelta());
        new Intent().setAction("android.intent.action.VIEW");
        webView.loadDataWithBaseURL("file:///android_asset/", this.a.e, "text/html", "utf-8", null);
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    public void onPageFinished(WebView webView, String str) {
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "Oh no! " + str, Integer.valueOf(0)).show();
    }
}
