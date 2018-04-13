package com.alibaba.baichuan.android.trade.page;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.component.AlibcTaokeComponent;
import com.alibaba.baichuan.android.trade.component.b;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlibcPage extends AlibcBasePage {
    public Map additionalHttpHeaders;

    public AlibcPage(String str) {
        this.a = str;
    }

    private boolean a() {
        if (this.a == null) {
            return false;
        }
        for (String matches : AlibcContext.detailPatterns) {
            if (this.a.trim().matches(matches)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkParams() {
        return (getClass().getSuperclass() != null && getClass().getSuperclass().getName().equals(AlibcPage.class.getName())) || this.a != null;
    }

    public String genOpenUrl() {
        return URLUtil.isNetworkUrl(this.a) ? this.a.trim() : null;
    }

    public Map getAdditionalHttpHeaders() {
        return this.additionalHttpHeaders;
    }

    public String getApi() {
        return UserTrackerConstants.E_SHOWPAGE;
    }

    public boolean needTaoke(AlibcTaokeParams alibcTaokeParams) {
        return a() && alibcTaokeParams != null;
    }

    public void taokeTraceAndGenUrl(AlibcTaokeParams alibcTaokeParams, a aVar, AlibcTaokeTraceCallback alibcTaokeTraceCallback) {
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.a)) {
            boolean z = true;
            for (String matches : AlibcContext.detailPatterns) {
                if (this.a.matches(matches)) {
                    z = true;
                }
            }
            if (true == z) {
                Matcher matcher = Pattern.compile("(\\?|&)id=([^&?]*)").matcher(this.a);
                Object obj = null;
                if (matcher.find()) {
                    String group = matcher.group();
                    obj = group.substring(group.indexOf(61) + 1);
                }
                HashMap hashMap = new HashMap();
                hashMap.put("itemId", obj);
                if (aVar.e != null) {
                    AlibcTaokeComponent.INSTANCE.genUrlAndTaokeTrace(hashMap, genOpenUrl(), true, alibcTaokeParams, getApi(), aVar, alibcTaokeTraceCallback, aVar.e);
                }
            }
        }
    }

    public boolean tryNativeJump(AlibcTaokeParams alibcTaokeParams, AlibcShowParams alibcShowParams, Map map, Activity activity) {
        Object obj;
        if (a()) {
            String substring;
            Matcher matcher = Pattern.compile("(\\?|&)id=([^&?]*)").matcher(this.a);
            if (matcher.find()) {
                String group = matcher.group();
                substring = group.substring(group.indexOf(61) + 1);
            } else {
                substring = null;
            }
            String str = alibcTaokeParams != null ? alibcTaokeParams.pid : null;
            String backUrl = (alibcShowParams == null || TextUtils.isEmpty(alibcShowParams.getBackUrl())) ? "alisdk://" : alibcShowParams.getBackUrl();
            obj = "";
            if (alibcShowParams != null) {
                obj = alibcShowParams.getClientType();
            }
            map.put(AppLinkConstants.APPTYPE, obj);
            return b.a(activity, ApplinkOpenType.SHOWITEM, null, substring, AlibcConfig.getInstance().getIsvCode(), str, backUrl, map);
        }
        obj = "";
        if (alibcShowParams != null) {
            obj = alibcShowParams.getClientType();
        }
        map.put(AppLinkConstants.APPTYPE, obj);
        return super.tryNativeJump(alibcTaokeParams, alibcShowParams, map, activity);
    }
}
