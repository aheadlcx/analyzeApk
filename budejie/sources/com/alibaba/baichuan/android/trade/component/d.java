package com.alibaba.baichuan.android.trade.component;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.a.b;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcFailureCallback;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;

class d implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ AlibcTaokeTraceCallback c;
    final /* synthetic */ String d;
    final /* synthetic */ AlibcTaokeParams e;
    final /* synthetic */ String f;
    final /* synthetic */ AlibcFailureCallback g;
    final /* synthetic */ AlibcTaokeComponent h;

    d(AlibcTaokeComponent alibcTaokeComponent, a aVar, HashMap hashMap, AlibcTaokeTraceCallback alibcTaokeTraceCallback, String str, AlibcTaokeParams alibcTaokeParams, String str2, AlibcFailureCallback alibcFailureCallback) {
        this.h = alibcTaokeComponent;
        this.a = aVar;
        this.b = hashMap;
        this.c = alibcTaokeTraceCallback;
        this.d = str;
        this.e = alibcTaokeParams;
        this.f = str2;
        this.g = alibcFailureCallback;
    }

    public void run() {
        this.a.d(AlibcConstants.TK_SYNC);
        if (AlibcConfig.getInstance().getIsSyncForTaoke()) {
            AlibcLogger.i("taoke", "taoke同步打点");
            this.a.a(UserTrackerConstants.PM_TAOKE_TIME);
            NetworkResponse a = new b().a(new HashMap(this.b));
            Object a2 = b.a(a);
            this.a.b(UserTrackerConstants.PM_TAOKE_TIME);
            if (TextUtils.isEmpty(a2)) {
                if (a != null) {
                    this.h.a(UserTrackerConstants.U_TAOKE_TRACE_SYNC, a.errorMsg, UserTrackerConstants.ERRCODE_TAOKE_SYNC + a.errorCode);
                }
                AlibcLogger.e("taoke", "taoke同步打点失败");
            } else {
                AlibcLogger.i("taoke", "taoke同步打点成,sclickUrl：" + a.data);
                this.h.a(UserTrackerConstants.U_TAOKE_TRACE_SYNC);
                this.c.genTaokeUrl(a2);
                return;
            }
        }
        this.h.taokeTrace(this.b, this.d, this.e, this.f, this.a, this.g);
        this.c.genTaokeUrl(null);
    }
}
