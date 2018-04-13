package com.tencent.bugly.crashreport.biz;

import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.x;
import java.util.List;

final class d implements t {
    private /* synthetic */ List a;
    private /* synthetic */ a b;

    d(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public final void a(boolean z) {
        if (z) {
            x.c("[UserInfo] Successfully uploaded user info.", new Object[0]);
            long currentTimeMillis = System.currentTimeMillis();
            for (UserInfoBean userInfoBean : this.a) {
                userInfoBean.f = currentTimeMillis;
                a.a(this.b, userInfoBean, true);
            }
        }
    }
}
