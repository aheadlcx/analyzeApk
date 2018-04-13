package com.alibaba.baichuan.android.trade.adapter.alipay;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AliPayResult;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alipay.sdk.util.h;
import com.taobao.dp.http.ResCode;
import java.util.ArrayList;

class a implements Runnable {
    final /* synthetic */ WebView a;
    final /* synthetic */ AlibcTradeCallback b;
    final /* synthetic */ String c;
    final /* synthetic */ Uri d;
    final /* synthetic */ AlibcAlipay e;

    a(AlibcAlipay alibcAlipay, WebView webView, AlibcTradeCallback alibcTradeCallback, String str, Uri uri) {
        this.e = alibcAlipay;
        this.a = webView;
        this.b = alibcTradeCallback;
        this.c = str;
        this.d = uri;
    }

    public void run() {
        if (this.a.getContext() instanceof Activity) {
            Object a = this.e.a(this.b, this.a, this.c);
            if (a == null || TextUtils.isEmpty(a)) {
                this.e.a(UserTrackerConstants.ERRCODE_PAY_FAIL, "result is null");
                return;
            }
            String a2 = this.e.a(a.replace("{", "").replace(h.d, ""), "resultStatus=", ";memo");
            if (TextUtils.equals(AlibcAlipay.PAY_SUCCESS_CODE, a2)) {
                ArrayList a3 = this.e.a(this.d);
                AliPayResult a4 = this.e.a(a3);
                if (a4 == null || (a4.payFailedOrders == null && a4.paySuccessOrders == null)) {
                    AlibcLogger.i("AlibcAlipay", "QueryOrderResultTask.asyncExecute()--pay success but empty order： message:为空的订单");
                    if (this.b != null) {
                        this.b.onFailure(ResCode.UPDATE_SECURITY_GUARD_SDK, "alipay支付失败，信息为:empty orders");
                        return;
                    }
                    return;
                }
                AlibcLogger.i("AlibcAlipay", "QueryOrderResultTask.asyncExecute()--pay success" + a3);
                this.e.a();
                this.e.a(this.b, this.a, a4);
                return;
            }
            this.e.a(this.b, a2, this.a);
        }
    }
}
