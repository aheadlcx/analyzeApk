package com.alibaba.baichuan.android.trade.c.a.b;

import android.webkit.WebView;
import com.alibaba.baichuan.android.trade.adapter.alipay.AlibcAlipay;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

public class j {
    private static final String a = j.class.getSimpleName();
    private String[] b;
    private AlibcTradeCallback c;

    public j(AlibcTradeCallback alibcTradeCallback, String[] strArr) {
        this.c = alibcTradeCallback;
        this.b = strArr;
    }

    private boolean b(WebView webView, String str) {
        return AlibcAlipay.getInstance().openAlipay(this.c, webView, str);
    }

    public boolean a(WebView webView, String str) {
        AlibcLogger.i("PayOverrideHandler", "PayOverrideHandler.handle()--进入支付流程: url:" + str + " webview:" + (webView != null ? webView.toString() : null));
        return b(webView, str);
    }
}
