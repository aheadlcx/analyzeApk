package com.alipay.b.a.a.c.a;

import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.Map;

public final class b {
    public static c a(DataReportResult dataReportResult) {
        c cVar = new c();
        if (dataReportResult == null) {
            return null;
        }
        cVar.a = dataReportResult.success;
        cVar.b = dataReportResult.resultCode;
        Map map = dataReportResult.resultData;
        if (map != null) {
            cVar.c = (String) map.get("apdid");
            cVar.d = (String) map.get("apdidToken");
            cVar.g = (String) map.get("dynamicKey");
            cVar.h = (String) map.get("timeInterval");
            cVar.i = (String) map.get("webrtcUrl");
            cVar.j = "";
            String str = (String) map.get("drmSwitch");
            if (a.b(str)) {
                if (str.length() > 0) {
                    cVar.e = str.charAt(0);
                }
                if (str.length() >= 3) {
                    cVar.f = str.charAt(2);
                }
            }
        }
        return cVar;
    }
}
