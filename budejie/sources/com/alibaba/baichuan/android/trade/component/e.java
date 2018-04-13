package com.alibaba.baichuan.android.trade.component;

import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;
import com.alibaba.baichuan.android.trade.callback.AlibcFailureCallback;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.utils.d;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;

class e implements Runnable {
    final /* synthetic */ HashMap a;
    final /* synthetic */ String b;
    final /* synthetic */ AlibcTaokeParams c;
    final /* synthetic */ String d;
    final /* synthetic */ AlibcFailureCallback e;
    final /* synthetic */ AlibcTaokeComponent f;

    e(AlibcTaokeComponent alibcTaokeComponent, HashMap hashMap, String str, AlibcTaokeParams alibcTaokeParams, String str2, AlibcFailureCallback alibcFailureCallback) {
        this.f = alibcTaokeComponent;
        this.a = hashMap;
        this.b = str;
        this.c = alibcTaokeParams;
        this.d = str2;
        this.e = alibcFailureCallback;
    }

    public void run() {
        NetworkResponse synchTaokeTrace = this.f.synchTaokeTrace(this.a, this.b, this.c, this.d);
        if (synchTaokeTrace == null || !synchTaokeTrace.isSuccess) {
            String str = synchTaokeTrace == null ? "null taokeTrace response" : "code: " + synchTaokeTrace.errorCode + " msg: " + synchTaokeTrace.errorMsg;
            AlibcLogger.e("taoke", str != null ? str : null);
            if (this.e != null) {
                d.a(this.e, 0, "淘客打点失败，错误信息:" + str);
                AlibcLogger.d("taoke", "taoke异步打点失败");
                return;
            }
            return;
        }
        this.f.a(UserTrackerConstants.U_TAOKE_TRACE_ASYNC);
        AlibcLogger.d("taoke", "taoke异步打点成功");
    }
}
