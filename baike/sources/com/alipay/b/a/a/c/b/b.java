package com.alipay.b.a.a.c.b;

import android.content.Context;
import com.alipay.b.a.a.c.a;
import com.alipay.b.a.a.c.a.c;
import com.alipay.b.a.a.c.a.d;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public final class b implements a {
    private static a a = null;
    private static a b = null;

    public static a a(Context context, String str) {
        a aVar = null;
        if (context == null) {
            return null;
        }
        if (a == null) {
            if (context != null) {
                aVar = com.alipay.b.a.a.c.b.a(context, str);
            }
            b = aVar;
            a = new b();
        }
        return a;
    }

    public final c a(d dVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        dataReportRequest.os = dVar.a();
        dataReportRequest.rpcVersion = Constants.VIA_SHARE_TYPE_PUBLISHVIDEO;
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", dVar.b());
        dataReportRequest.bizData.put("apdidToken", dVar.c());
        dataReportRequest.bizData.put("umidToken", dVar.d());
        dataReportRequest.bizData.put("dynamicKey", dVar.f());
        dataReportRequest.deviceData = dVar.e();
        return com.alipay.b.a.a.c.a.b.a(b.a(dataReportRequest));
    }

    public final boolean a(String str) {
        return b.a(str);
    }
}
