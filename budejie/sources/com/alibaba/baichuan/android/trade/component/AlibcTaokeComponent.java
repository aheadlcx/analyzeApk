package com.alibaba.baichuan.android.trade.component;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;
import com.alibaba.baichuan.android.trade.adapter.ut.UserTrackerCompoment;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcFailureCallback;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;
import java.util.Map;

public class AlibcTaokeComponent {
    public static final AlibcTaokeComponent INSTANCE = new AlibcTaokeComponent();

    private AlibcTaokeComponent() {
    }

    private void a(String str) {
        UserTrackerCompoment.sendUseabilitySuccess(str);
    }

    private void a(String str, String str2, String str3) {
        UserTrackerCompoment.sendUseabilityFailureForOtherErrmsg(str, str2, str3);
    }

    private void a(HashMap hashMap, String str, AlibcTaokeParams alibcTaokeParams, String str2, a aVar, AlibcFailureCallback alibcFailureCallback) {
        AlibcLogger.d("taoke", "taoke异步打点开始，参数为：" + (hashMap != null ? hashMap.toString() : null) + "url=" + str + "rpcReferer=" + str2);
        AlibcContext.executorService.a(new e(this, hashMap, str, alibcTaokeParams, str2, alibcFailureCallback));
    }

    private void a(Map map, AlibcTaokeParams alibcTaokeParams, String str) {
        String str2 = null;
        AlibcLogger.d("taoke", "添加淘客参数");
        if (map != null) {
            Object obj;
            String str3 = "url";
            if (str == null) {
                obj = "";
            }
            map.put(str3, obj);
            map.put("appKey", AlibcContext.getAppKey());
            map.put("utdid", AlibcContext.getUtdid());
            if (TextUtils.isEmpty(alibcTaokeParams.subPid)) {
                map.put("subPid", null);
            } else {
                map.put("subPid", alibcTaokeParams.subPid);
            }
            if (TextUtils.isEmpty(alibcTaokeParams.unionId)) {
                map.put("unionId", null);
            } else {
                map.put("unionId", alibcTaokeParams.unionId);
            }
            map.put("pid", alibcTaokeParams.pid);
            str3 = "taoke";
            StringBuilder append = new StringBuilder().append("淘客参数:");
            if (map != null) {
                str2 = map.toString();
            }
            AlibcLogger.d(str3, append.append(str2).toString());
        }
    }

    public void asyncTaokeTrace(HashMap hashMap, AlibcTaokeParams alibcTaokeParams, AlibcFailureCallback alibcFailureCallback) {
        String str = null;
        if (hashMap != null && alibcTaokeParams != null) {
            a((Map) hashMap, alibcTaokeParams, null);
            String str2 = "taoke";
            StringBuilder append = new StringBuilder().append("taoke异步打点开始，参数为：");
            if (hashMap != null) {
                str = hashMap.toString();
            }
            AlibcLogger.d(str2, append.append(str).toString());
            AlibcContext.executorService.a(new c(this, hashMap, alibcTaokeParams, alibcFailureCallback));
        }
    }

    public void genUrlAndTaokeTrace(HashMap hashMap, String str, boolean z, AlibcTaokeParams alibcTaokeParams, String str2, a aVar, AlibcTaokeTraceCallback alibcTaokeTraceCallback, AlibcFailureCallback alibcFailureCallback) {
        if (!com.alibaba.baichuan.android.trade.utils.b.a.a(AlibcContext.context)) {
            AlibcLogger.e("taoke", "网络无连接，请连接网络后再试");
            alibcTaokeTraceCallback.genTaokeUrl(null);
        } else if (hashMap == null || alibcTaokeParams == null) {
            AlibcLogger.e("taoke", "淘客参数不存在");
        } else {
            a((Map) hashMap, alibcTaokeParams, str);
            aVar.d(AlibcConstants.TK_ASYNC);
            if (z) {
                AlibcContext.executorService.a(new d(this, aVar, hashMap, alibcTaokeTraceCallback, str, alibcTaokeParams, str2, alibcFailureCallback));
                return;
            }
            taokeTrace(hashMap, str, alibcTaokeParams, str2, aVar, alibcFailureCallback);
            alibcTaokeTraceCallback.genTaokeUrl(null);
        }
    }

    public NetworkResponse synchTaokeTrace(HashMap hashMap, String str, AlibcTaokeParams alibcTaokeParams, String str2) {
        if (alibcTaokeParams == null) {
            return null;
        }
        com.alibaba.baichuan.android.trade.a.a aVar = new com.alibaba.baichuan.android.trade.a.a();
        if (alibcTaokeParams.pid == null || !alibcTaokeParams.pid.startsWith(AlibcTaokeParams.PID_PREFIX)) {
            AlibcLogger.d("taoke", "淘客pid参数错误");
        }
        NetworkResponse a = aVar.a(new HashMap(hashMap));
        if (!(a == null || a.isSuccess())) {
            a(UserTrackerConstants.U_TAOKE_TRACE_ASYNC, a.errorMsg, UserTrackerConstants.ERRCODE_TAOKE_ASYNC + a.errorCode);
        }
        if (!AlibcContext.isDebuggable()) {
            return a;
        }
        AlibcLogger.d("taoke", "tktrace result json: " + a.data);
        return a;
    }

    public void taokeTrace(HashMap hashMap, String str, AlibcTaokeParams alibcTaokeParams, String str2, a aVar, AlibcFailureCallback alibcFailureCallback) {
        a(hashMap, str, alibcTaokeParams, str2, aVar, alibcFailureCallback);
    }
}
