package com.alibaba.baichuan.android.auth;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkClient.NetworkRequestListener;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import java.util.Map;

class AlibcAuth$b implements NetworkRequestListener {
    private e a;
    private String b;
    private boolean c;

    public AlibcAuth$b(e eVar, String str, boolean z) {
        this.a = eVar;
        this.b = str;
        this.c = z;
    }

    public void onError(int i, NetworkResponse networkResponse) {
        if (this.a != null) {
            this.a.a(networkResponse.errorCode, networkResponse.errorMsg);
        }
        AlibcAuth.b(networkResponse.errorCode);
    }

    public void onSuccess(int i, NetworkResponse networkResponse) {
        Map map = networkResponse.data;
        if (map == null || !networkResponse.isSuccess) {
            if (this.a != null) {
                this.a.a(networkResponse.errorCode, networkResponse.errorMsg);
            }
            AlibcAuth.b(networkResponse.errorCode);
            return;
        }
        AlibcUserTracker.getInstance().sendUseabilitySuccess(UserTrackerConstants.P_BCPCSDK, "Mtop_Auth");
        if (!this.c || TextUtils.equals(this.b, d.a().c())) {
            d.a().a(map.get(INoCaptchaComponent.token).toString(), StringUtils.obj2Long(map.get("expires")).longValue());
            if (this.a != null) {
                this.a.a();
            }
        } else if (this.a != null) {
            this.a.a();
        }
    }
}
