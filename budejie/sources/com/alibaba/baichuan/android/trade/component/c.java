package com.alibaba.baichuan.android.trade.component;

import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;
import com.alibaba.baichuan.android.trade.callback.AlibcFailureCallback;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;

class c implements Runnable {
    final /* synthetic */ HashMap a;
    final /* synthetic */ AlibcTaokeParams b;
    final /* synthetic */ AlibcFailureCallback c;
    final /* synthetic */ AlibcTaokeComponent d;

    c(AlibcTaokeComponent alibcTaokeComponent, HashMap hashMap, AlibcTaokeParams alibcTaokeParams, AlibcFailureCallback alibcFailureCallback) {
        this.d = alibcTaokeComponent;
        this.a = hashMap;
        this.b = alibcTaokeParams;
        this.c = alibcFailureCallback;
    }

    public void run() {
        String str = null;
        NetworkResponse synchTaokeTrace = this.d.synchTaokeTrace(this.a, null, this.b, null);
        if (synchTaokeTrace == null || !synchTaokeTrace.isSuccess) {
            String str2 = synchTaokeTrace == null ? "null taokeTrace response" : "code: " + synchTaokeTrace.errorCode + " msg: " + synchTaokeTrace.errorMsg;
            String str3 = "taoke";
            if (str2 != null) {
                str = str2;
            }
            AlibcLogger.e(str3, str);
            if (this.c != null) {
                this.c.onFailure(0, "淘客打点失败，错误信息:" + str2);
                AlibcLogger.d("taoke", "taoke异步打点失败");
                return;
            }
            return;
        }
        this.d.a(UserTrackerConstants.U_TAOKE_TRACE_ASYNC);
        AlibcLogger.d("taoke", "taoke异步打点成功");
    }
}
