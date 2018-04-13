package com.alibaba.baichuan.android.trade.adapter.ut;

import com.alibaba.baichuan.android.trade.adapter.ut.performance.PerformancePoint;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

public class UserTrackerCompoment {
    private static final String a = UserTrackerCompoment.class.getSimpleName();
    private static long b = 0;

    private static String a(String str) {
        return str.contains(UserTrackerConstants.U_PAGE_NATIVE) ? "Page_Native" : str.contains(UserTrackerConstants.U_PAGE_H5) ? "Page_H5" : str.contains("Taoke_Trace_") ? "Taoke_Trace" : str;
    }

    public static void endInitTimeRecord() {
        b = System.currentTimeMillis() - b;
    }

    public static long getUTInitTime() {
        return b;
    }

    public static void sendPerfomancePoint(PerformancePoint performancePoint) {
        if (performancePoint == null) {
            a.a(a, "sendPerfomancePoint", "pagePerformancePoint is null");
            return;
        }
        AlibcLogger.d(a, "sendPerfomancePoint:" + performancePoint.toString());
        if (!performancePoint.checkData()) {
            AlibcLogger.e(a, "sendPerfomancePoint: time is too large");
        }
        AlibcUserTracker.getInstance().sendPerfomancePoint("BCTradeSDK", performancePoint.getMonitorPoint(), performancePoint.getDimensionValues(), performancePoint.getMeasureValues());
    }

    public static void sendUseabilityFailure(String str, String str2) {
        sendUseabilityFailure(str, str2, null);
    }

    public static void sendUseabilityFailure(String str, String str2, String str3) {
        if (str == null || str2 == null) {
            a.a(a, "sendUseabilityFailure", "label/errMsg is null!");
            return;
        }
        UsabilityErrorMsg usabilityMsg = ErrorMsgBeanGererator.getUsabilityMsg(str, str2, str3);
        if (usabilityMsg == null) {
            a.a(a, "sendUseabilityFailure", "usabilityErrorMsg is null !");
            return;
        }
        AlibcUserTracker.getInstance().sendUseabilityFailure("BCTradeSDK", a(str), usabilityMsg.getErrorCodeString(), usabilityMsg.getErrMsg());
    }

    public static void sendUseabilityFailureForOtherErrmsg(String str, String str2, String str3) {
        if (str == null || str3 == null) {
            a.a(a, "sendUseabilityFailure", "monitorPoint/errCode is null!");
            return;
        }
        String a = a(str);
        AlibcUserTracker instance = AlibcUserTracker.getInstance();
        String str4 = "BCTradeSDK";
        if (str2 == null) {
            str2 = "";
        }
        instance.sendUseabilityFailure(str4, a, str3, str2);
    }

    public static void sendUseabilitySuccess(String str) {
        if (str == null) {
            a.a(a, "sendUseabilityFailure", "label/errMsg is null!");
            return;
        }
        AlibcUserTracker.getInstance().sendUseabilitySuccess("BCTradeSDK", a(str));
    }

    public static void startInitTimeRecord() {
        b = System.currentTimeMillis();
    }
}
