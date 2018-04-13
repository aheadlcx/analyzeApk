package qsbk.app.activity;

import android.webkit.WebView;
import qsbk.app.cafe.Jsnativeinteraction.ui.QsbkWebViewClient;

class acs extends QsbkWebViewClient {
    final /* synthetic */ SimpleWebActivity a;

    acs(SimpleWebActivity simpleWebActivity) {
        this.a = simpleWebActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.k.loadUrl("javascript:(function(){    var imgUrl = window.shareImg;    var title = window.shareTitle;    var content = window.shareContent;    _temp_webresult.onResult(title, content, imgUrl);})()");
    }
}
