package com.alibaba.baichuan.android.trade.c.a.b;

import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.c.a.a.b.b;
import com.alibaba.baichuan.android.trade.c.a.a.b.c;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;

public class h implements b {
    public static String a() {
        return "alisdk://" + (AlibcContext.getAppKey() + ".nativeTaobao") + "/handleraction";
    }

    public boolean a(c cVar) {
        String a = a();
        return com.alibaba.baichuan.android.trade.component.b.a(cVar.g, ApplinkOpenType.SHOWITEM, null, cVar.a("id"), AlibcConfig.getInstance().getIsvCode(), cVar.a("pid"), a, null);
    }
}
