package com.alibaba.baichuan.android.trade.page;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;

public class AlibcMyOrdersPage extends AlibcBasePage {
    public static final String[] ORDER_TYPE = new String[]{"all", "waitPay", "waitSend", "waitConfirm", "waitRate"};
    public static final String TAB_CODE = "?tabCode=%s";
    int b;
    boolean c;

    public AlibcMyOrdersPage(int i, boolean z) {
        this.b = i;
        this.c = z;
    }

    public boolean checkParams() {
        return this.b >= 0 && this.b <= 4;
    }

    public String genOpenUrl() {
        if (this.a != null && !TextUtils.isEmpty(this.a)) {
            return this.a;
        }
        String str = AlibcContext.MY_ORDERS_URL;
        int indexOf = str.indexOf(63);
        if (indexOf > 0) {
            str = str.substring(0, indexOf);
        }
        str = str + TAB_CODE;
        if (this.b < 0 || this.b >= 5) {
            this.a = str.substring(0, str.indexOf("?"));
        } else if (this.c) {
            this.a = String.format(str, new Object[]{ORDER_TYPE[this.b]});
        } else {
            this.a = String.format(str + "&condition={\"extra\":{\"attributes\":{\"ttid\":\"%s\"}}}", new Object[]{ORDER_TYPE[this.b], AlibcContext.getAppKey()});
        }
        return this.a;
    }

    public String getApi() {
        return UserTrackerConstants.E_SHOWORDER;
    }

    public String getPerformancePageType() {
        return AlibcConstants.MY_ORDER;
    }

    public String getUsabilityPageType() {
        return UserTrackerConstants.U_ORDER_PAGE;
    }

    public boolean isBackWhenLoginFail() {
        return true;
    }

    public boolean needTaoke(AlibcTaokeParams alibcTaokeParams) {
        return false;
    }
}
