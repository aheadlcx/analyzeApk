package qsbk.app.core.web.ui;

import android.webkit.WebView;

class d extends QsbkWebViewClient {
    final /* synthetic */ FullScreenWebActivity a;

    d(FullScreenWebActivity fullScreenWebActivity) {
        this.a = fullScreenWebActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.setTitle(webView.getTitle());
        this.a.a.loadUrl("javascript:(function(){    var imgUrl = window.shareImg;    var title = window.shareTitle;    var content = window.shareContent;    var shareUrl = window.shareUrl;    GetWebShareInfo.onWebShareInfo(title, content, imgUrl, shareUrl);})()");
    }
}
