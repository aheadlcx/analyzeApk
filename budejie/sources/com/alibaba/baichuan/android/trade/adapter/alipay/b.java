package com.alibaba.baichuan.android.trade.adapter.alipay;

import android.app.Activity;
import android.webkit.WebView;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.android.trade.utils.a.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AlibcTradeCallback b;
    final /* synthetic */ WebView c;
    final /* synthetic */ AlibcAlipay d;

    b(AlibcAlipay alibcAlipay, String str, AlibcTradeCallback alibcTradeCallback, WebView webView) {
        this.d = alibcAlipay;
        this.a = str;
        this.b = alibcTradeCallback;
        this.c = webView;
    }

    public void run() {
        a a = com.alibaba.baichuan.android.trade.utils.a.b.a(this.a);
        AlibcLogger.i("AlibcAlipay", "alipay支付失败，信息为：" + (a != null ? a.c : null));
        this.d.a(UserTrackerConstants.ERRCODE_PAY_FAIL, a != null ? a.c : UserTrackerConstants.EM_PAY_FAILURE);
        if (this.b != null) {
            this.b.onFailure(KernelMessageConstants.GENERIC_SYSTEM_ERROR, "alipay支付失败，信息为：" + a.c);
        }
        if (this.c != null && AlibcContext.MY_ORDERS_URL != null) {
            if (!this.c.getUrl().contains(AlibcContext.MY_ORDERS_URL)) {
                this.c.loadUrl(String.format(AlibcContext.MY_ORDERS_URL + AlibcMyOrdersPage.TAB_CODE, new Object[]{AlibcMyOrdersPage.ORDER_TYPE[1]}));
            } else if (this.c.getUrl().contains(AlibcContext.MY_ORDERS_URL)) {
                this.c.reload();
            } else if (this.c.getContext() instanceof Activity) {
                ((Activity) this.c.getContext()).finish();
            }
        }
    }
}
