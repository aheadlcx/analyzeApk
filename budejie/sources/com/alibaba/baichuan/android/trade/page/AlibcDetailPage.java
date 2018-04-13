package com.alibaba.baichuan.android.trade.page;

import android.app.Activity;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.security.AlibcSecurityGuard;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.component.AlibcTaokeComponent;
import com.alibaba.baichuan.android.trade.component.b;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;
import java.util.HashMap;
import java.util.Map;

public class AlibcDetailPage extends AlibcBasePage {
    private String b;

    public AlibcDetailPage(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.matches("^\\d+$")) {
                this.b = str;
            } else {
                this.b = String.valueOf(AlibcSecurityGuard.getInstance().analyzeItemId(str));
            }
        }
    }

    protected String a() {
        return AlibcContext.SHOUTAO_ITEM_DETAIL_URL;
    }

    public boolean checkParams() {
        return (this.b == null || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public String genOpenUrl() {
        if (this.a != null && !TextUtils.isEmpty(this.a)) {
            return this.a;
        }
        String a = a();
        int indexOf = a.indexOf(63);
        if (indexOf > 0) {
            a = a.substring(0, indexOf);
        }
        this.a = String.format(a + "?id=%s", new Object[]{this.b});
        return this.a;
    }

    public String getApi() {
        return UserTrackerConstants.E_SHOWITEMDETAIL;
    }

    public String getPerformancePageType() {
        return AlibcConstants.DETAIL;
    }

    public String getUsabilityPageType() {
        return UserTrackerConstants.U_DETAIL_PAGE;
    }

    public boolean needTaoke(AlibcTaokeParams alibcTaokeParams) {
        return alibcTaokeParams != null;
    }

    public void taokeTraceAndGenUrl(AlibcTaokeParams alibcTaokeParams, a aVar, AlibcTaokeTraceCallback alibcTaokeTraceCallback) {
        if (this.b != null && !TextUtils.isEmpty(this.b)) {
            HashMap hashMap = new HashMap();
            hashMap.put("itemId", this.b);
            if (aVar.e != null) {
                AlibcTaokeComponent.INSTANCE.genUrlAndTaokeTrace(hashMap, genOpenUrl(), true, alibcTaokeParams, getApi(), aVar, alibcTaokeTraceCallback, aVar.e);
            }
        }
    }

    public boolean tryNativeJump(AlibcTaokeParams alibcTaokeParams, AlibcShowParams alibcShowParams, Map map, Activity activity) {
        String str = alibcTaokeParams != null ? alibcTaokeParams.pid : null;
        Object obj = "";
        if (alibcShowParams != null) {
            obj = alibcShowParams.getClientType();
        }
        map.put(AppLinkConstants.APPTYPE, obj);
        String backUrl = (alibcShowParams == null || TextUtils.isEmpty(alibcShowParams.getBackUrl())) ? "alisdk://" : alibcShowParams.getBackUrl();
        return b.a(activity, ApplinkOpenType.SHOWITEM, null, this.b, AlibcConfig.getInstance().getIsvCode(), str, backUrl, map);
    }
}
