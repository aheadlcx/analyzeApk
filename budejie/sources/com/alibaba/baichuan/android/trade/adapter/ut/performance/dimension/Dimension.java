package com.alibaba.baichuan.android.trade.adapter.ut.performance.dimension;

import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.d;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import java.io.Serializable;

public class Dimension implements Serializable {
    private static final String e = Dimension.class.getSimpleName();
    protected String a = AlibcContext.getAppKey();
    protected String b = d.b(AlibcContext.context);
    protected String c = AlibcContext.sdkVersion;
    protected String d = AlibcConstants.PF_ANDROID;

    public static DimensionSet getDimensionSet() {
        return DimensionSet.create().addDimension("appkey").addDimension("app_version").addDimension("sdk_version").addDimension("platform");
    }

    public DimensionValueSet getDimensionValues() {
        if (this.a != null && this.b != null) {
            return DimensionValueSet.create().setValue("appkey", this.a).setValue("app_version", this.b).setValue("sdk_version", this.c).setValue("platform", this.d);
        }
        a.a(e, "getDimensionValues", "appkey/appVersion is null");
        return null;
    }

    public String toString() {
        return "Dimension{appkey='" + this.a + '\'' + ", appVersion='" + this.b + '\'' + ", sdkVersion='" + this.c + '\'' + ", platform='" + this.d + '\'' + '}';
    }
}
