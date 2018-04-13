package cn.v6.sixrooms.pay.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.ToastUtils;

final class bp extends WebViewClient {
    final /* synthetic */ WeixinPayActivity a;

    bp(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.a.a("跳转微信中");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (!this.a.u) {
            this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
            this.a.b();
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtils.e("WeixinPayActivity", "url=" + str);
        if (!SixRoomsUtils.isWeixinAvilible(this.a)) {
            ToastUtils.showToast("请安装微信");
            this.a.b();
        } else if (!(!str.startsWith("weixin://wap/pay?") || this.a.u || this.a.k == this.a.l)) {
            this.a.l = this.a.k;
            this.a.u = true;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            this.a.startActivity(intent);
        }
        return true;
    }
}
