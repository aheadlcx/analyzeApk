package com.alibaba.baichuan.android.trade.utils;

import android.app.Activity;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcFailureCallback;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.utils.cache.MemoryCacheUtils;
import com.alibaba.baichuan.android.trade.utils.code.Base64Utils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AlibcTradeHelper {
    private static String a(Map map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        map.put(AlibcConstants.TTID, AlibcConfig.getInstance().getWebTTID());
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String str : map.keySet()) {
            String str2;
            String str3 = (String) map.get(str2);
            if (i != 0) {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(str2 + LoginConstants.EQUAL + URLEncoder.encode(new String(str3), "utf-8"));
                i++;
            } catch (Exception e) {
                AlibcLogger.e("AlibcTradeHelper", "构建Ybhpss参数错误：" + e.getMessage());
                return null;
            }
        }
        if (TextUtils.isEmpty(stringBuilder)) {
            return stringBuilder.toString();
        }
        str2 = Base64Utils.getBase64(new String(stringBuilder));
        MemoryCacheUtils.setGroupProperity(AlibcConstants.TRADE_GROUP, "ybhpss", str2);
        return str2;
    }

    public static boolean checkParams(AlibcBasePage alibcBasePage, Activity activity, a aVar, AlibcTradeCallback alibcTradeCallback) {
        if (alibcTradeCallback == null) {
            d.a((AlibcFailureCallback) alibcTradeCallback, com.alibaba.baichuan.android.trade.utils.a.a.a(14, "tradeProcessCallback"));
            return false;
        } else if (activity == null) {
            d.a((AlibcFailureCallback) alibcTradeCallback, com.alibaba.baichuan.android.trade.utils.a.a.a(14, "Activity"));
            return false;
        } else if (alibcBasePage != null && alibcBasePage.checkParams()) {
            return true;
        } else {
            d.a((AlibcFailureCallback) alibcTradeCallback, com.alibaba.baichuan.android.trade.utils.a.a.a(14, "AlibcPage"));
            return false;
        }
    }

    public static Map createUrlParams(Map map) {
        Map hashMap = new HashMap();
        hashMap.put(AlibcConstants.TTID, AlibcConfig.getInstance().getWebTTID());
        hashMap.put(AlibcConstants.UMP_CHANNEL, "1-" + AlibcContext.getAppKey());
        hashMap.put(AlibcConstants.U_CHANNEL, "1-" + AlibcContext.getAppKey());
        if (map == null || map.size() == 0) {
            return hashMap;
        }
        if (map.get(AlibcConstants.ISV_CODE) == null && AlibcConfig.getInstance().getIsvCode() != null) {
            hashMap.put(AlibcConstants.ISV_CODE, AlibcConfig.getInstance().getIsvCode());
        }
        Map hashMap2 = new HashMap();
        for (String str : map.keySet()) {
            String str2 = (String) map.get(str);
            if (!(str2 == null || TextUtils.isEmpty(str2))) {
                if (AlibcContext.firstLevelKeys.contains(str)) {
                    hashMap.put(str, str2);
                } else {
                    hashMap2.put(str, str2);
                }
            }
        }
        hashMap2.put(AlibcConstants.TTID, AlibcConfig.getInstance().getWebTTID());
        CharSequence a = a(hashMap2);
        if (!(a == null || TextUtils.isEmpty(a))) {
            hashMap.put("ybhpss", a);
        }
        return hashMap;
    }
}
