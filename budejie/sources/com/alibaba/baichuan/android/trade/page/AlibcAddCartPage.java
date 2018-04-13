package com.alibaba.baichuan.android.trade.page;

import android.net.Uri;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.security.AlibcSecurityGuard;
import com.alibaba.baichuan.android.trade.b.a;
import com.alibaba.baichuan.android.trade.callback.AlibcTaokeTraceCallback;
import com.alibaba.baichuan.android.trade.component.AlibcTaokeComponent;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import java.util.HashMap;

public class AlibcAddCartPage extends AlibcBasePage {
    private String b;

    public AlibcAddCartPage(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.matches("^\\d+$")) {
                this.b = str;
            } else {
                this.b = String.valueOf(AlibcSecurityGuard.getInstance().analyzeItemId(str));
            }
        }
    }

    public boolean checkParams() {
        return (this.b == null || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public String genOpenUrl() {
        if (this.a != null && !TextUtils.isEmpty(this.a)) {
            return this.a;
        }
        String str = AlibcContext.TB_ITEM_DETAIL_URL;
        int indexOf = str.indexOf(63);
        if (indexOf > 0) {
            str = str.substring(0, indexOf);
        }
        this.a = Uri.parse(String.format(str + "?id=%s", new Object[]{String.valueOf(this.b)})).buildUpon().appendQueryParameter("action_tae", "cart").appendQueryParameter("from_tae", Constants.SERVICE_SCOPE_FLAG_VALUE).fragment("sku").build().toString();
        return this.a;
    }

    public String getApi() {
        return UserTrackerConstants.E_ADDITEM2CART;
    }

    public String getPerformancePageType() {
        return AlibcConstants.ADD_CART;
    }

    public String getUsabilityPageType() {
        return UserTrackerConstants.U_CART_PAGE;
    }

    public boolean needTaoke(AlibcTaokeParams alibcTaokeParams) {
        return alibcTaokeParams != null;
    }

    public int requireOpenType() {
        return 1;
    }

    public void taokeTraceAndGenUrl(AlibcTaokeParams alibcTaokeParams, a aVar, AlibcTaokeTraceCallback alibcTaokeTraceCallback) {
        if (this.b != null && !TextUtils.isEmpty(this.b)) {
            HashMap hashMap = new HashMap();
            hashMap.put("itemId", this.b);
            if (aVar.e != null) {
                AlibcTaokeComponent.INSTANCE.genUrlAndTaokeTrace(hashMap, genOpenUrl(), false, alibcTaokeParams, getApi(), aVar, alibcTaokeTraceCallback, aVar.e);
            }
        }
    }
}
