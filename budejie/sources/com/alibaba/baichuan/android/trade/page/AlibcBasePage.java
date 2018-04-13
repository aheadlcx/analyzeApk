package com.alibaba.baichuan.android.trade.page;

import android.app.Activity;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.c.a.a.e;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.component.b;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;
import java.util.Map;

public class AlibcBasePage {
    public static final int NOT_REQUIRE = 0;
    public static final int REQUIRE_H5 = 1;
    public static final int REQUIRE_NATIVE = 2;
    private static final String b = AlibcBasePage.class.getSimpleName();
    protected String a;

    public boolean checkParams() {
        return true;
    }

    public String genOpenUrl() {
        return !TextUtils.isEmpty(this.a) ? this.a : null;
    }

    public String getAddParamsUrl(String str, Map map, a aVar) {
        AlibcLogger.d(b, "首次进入加参，参数： urlParams = " + map + "  url=" + str);
        if (map == null) {
            return genOpenUrl();
        }
        aVar.a(UserTrackerConstants.PM_URL_HANDLE_TIME);
        com.alibaba.baichuan.android.trade.c.a.a.c.a aVar2 = new com.alibaba.baichuan.android.trade.c.a.a.c.a();
        aVar2.e = 1;
        if (str == null) {
            str = genOpenUrl();
        }
        aVar2.d = str;
        aVar2.i = new HashMap();
        aVar2.i.put("ui_contextParams", map);
        String a = e.a().a(aVar2);
        aVar.b(UserTrackerConstants.PM_URL_HANDLE_TIME);
        AlibcLogger.d(b, "首次加参后结果为 url=" + a);
        return a == null ? genOpenUrl() : a;
    }

    public String getAddParamsUrl(Map map, a aVar) {
        return getAddParamsUrl(null, map, aVar);
    }

    public Map getAdditionalHttpHeaders() {
        return null;
    }

    public String getApi() {
        return UserTrackerConstants.E_SHOW;
    }

    public String getPerformancePageType() {
        return "url";
    }

    public String getUsabilityPageType() {
        return UserTrackerConstants.U_OTHER_PAGE;
    }

    public boolean isBackWhenLoginFail() {
        return false;
    }

    public boolean isNativeOpen(AlibcShowParams alibcShowParams) {
        boolean z = true;
        if (!AlibcApplink.isApplinkSupported(alibcShowParams.getClientType())) {
            return false;
        }
        if (requireOpenType() != 0) {
            if (2 != requireOpenType()) {
                z = false;
            }
            return z;
        }
        int double11OpenType = AlibcConfig.getInstance().getDouble11OpenType();
        if (double11OpenType == 1) {
            return true;
        }
        if (double11OpenType == 2) {
            return false;
        }
        switch (AlibcBasePage$1.a[alibcShowParams.getOpenType().ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                String genOpenUrl = genOpenUrl();
                if (genOpenUrl == null) {
                    return false;
                }
                for (String matches : AlibcContext.nativeOpenPatterns) {
                    if (genOpenUrl.matches(matches) && !AlibcConfig.getInstance().isForceH5()) {
                        return true;
                    }
                }
                return false;
        }
    }

    public boolean needTaoke(AlibcTaokeParams alibcTaokeParams) {
        return alibcTaokeParams != null;
    }

    public int requireOpenType() {
        return 0;
    }

    public void taokeTraceAndGenUrl(AlibcTaokeParams alibcTaokeParams, a aVar, AlibcTaokeTraceCallback alibcTaokeTraceCallback) {
    }

    public boolean tryNativeJump(AlibcTaokeParams alibcTaokeParams, AlibcShowParams alibcShowParams, Map map, Activity activity) {
        String str = "alisdk://";
        Object obj = "";
        String str2 = alibcTaokeParams != null ? alibcTaokeParams.pid : null;
        if (alibcShowParams != null) {
            obj = alibcShowParams.getClientType();
        }
        if (!(alibcShowParams == null || TextUtils.isEmpty(alibcShowParams.getBackUrl()))) {
            str = alibcShowParams.getBackUrl();
        }
        map.put(AppLinkConstants.APPTYPE, obj);
        map.put("url", genOpenUrl());
        return b.a(activity, ApplinkOpenType.SHOWURL, genOpenUrl(), null, AlibcConfig.getInstance().getIsvCode(), str2, str, map);
    }
}
