package qsbk.app.core.web.ui;

import android.webkit.WebView;

class i extends QsbkWebViewClient {
    final /* synthetic */ WebActivity a;

    i(WebActivity webActivity) {
        this.a = webActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.setTitle(webView.getTitle());
        this.a.a.loadUrl("javascript:(function(){    var imgUrl = window.shareImg;    var title = window.shareTitle;    var content = window.shareContent;    var shareUrl = window.shareUrl;    GetWebShareInfo.onWebShareInfo(title, content, imgUrl, shareUrl);})()");
    }
}
