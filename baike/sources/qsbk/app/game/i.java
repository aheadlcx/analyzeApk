package qsbk.app.game;

import android.content.Intent;
import android.net.Uri;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

class i extends WebViewClient {
    final /* synthetic */ GameIndexActivity a;

    i(GameIndexActivity gameIndexActivity) {
        this.a = gameIndexActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        SharePreferenceUtils.setSharePreferencesValue("cookie", CookieManager.getInstance().getCookie(str));
        super.onPageFinished(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri parse = Uri.parse(str);
        LogUtil.d("uri");
        if (parse.getPath().endsWith(".apk")) {
            this.a.startActivity(new Intent("android.intent.action.VIEW", parse));
        } else {
            webView.loadUrl(str);
        }
        return true;
    }
}
