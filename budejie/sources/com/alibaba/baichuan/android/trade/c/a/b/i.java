package com.alibaba.baichuan.android.trade.c.a.b;

import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.c.a.a.b.b;
import com.alibaba.baichuan.android.trade.c.a.a.b.c;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;

public class i implements b {
    public static String a() {
        String str = null;
        if (null == null) {
            str = AlibcContext.getAppKey() + ".nativeTaobao";
        }
        return "alisdk://" + str + "/handleraction";
    }

    public boolean a(c cVar) {
        String a = a();
        return com.alibaba.baichuan.android.trade.component.b.a(cVar.g, ApplinkOpenType.SHOWSHOP, null, cVar.a(AlibcConstants.URL_SHOP_ID), AlibcConfig.getInstance().getIsvCode(), cVar.a("pid"), a, null);
    }
}
