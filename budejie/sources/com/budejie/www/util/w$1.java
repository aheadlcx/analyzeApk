package com.budejie.www.util;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.adapter.alipay.AlibcAlipay;
import com.alipay.PayResult;
import com.alipay.sdk.app.PayTask;
import com.budejie.www.busevent.AliPayAction;
import de.greenrobot.event.EventBus;

class w$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ w b;

    w$1(w wVar, String str) {
        this.b = wVar;
        this.a = str;
    }

    public void run() {
        CharSequence resultStatus = new PayResult(new PayTask(w.a(this.b)).pay(this.a, true)).getResultStatus();
        if (TextUtils.equals(resultStatus, AlibcAlipay.PAY_SUCCESS_CODE)) {
            EventBus.getDefault().post(AliPayAction.OK);
        } else if (TextUtils.equals(resultStatus, "6001")) {
            EventBus.getDefault().post(AliPayAction.CANCEL);
        }
    }
}
