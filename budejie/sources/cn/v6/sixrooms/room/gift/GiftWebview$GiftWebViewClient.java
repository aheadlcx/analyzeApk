package cn.v6.sixrooms.room.gift;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class GiftWebview$GiftWebViewClient extends WebViewClient {
    final /* synthetic */ GiftWebview this$0;

    GiftWebview$GiftWebViewClient(GiftWebview giftWebview) {
        this.this$0 = giftWebview;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        GiftWebview.access$200(this.this$0);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        GiftWebview.access$200(this.this$0);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        if (GiftWebview.access$700(this.this$0) != 3) {
            GiftWebview.access$702(this.this$0, 2);
            if (GiftWebview.access$100(this.this$0) != null) {
                GiftWebview.access$100(this.this$0).animStart();
            }
        }
        super.onPageFinished(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        GiftWebview.access$702(this.this$0, 1);
        GiftWebview.access$800(this.this$0);
        super.onPageStarted(webView, str, bitmap);
    }
}
