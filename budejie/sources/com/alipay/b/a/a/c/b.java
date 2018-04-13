package com.alipay.b.a.a.c;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.vkeydfp.DeviceDataReportService;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;
import org.json.JSONObject;

public final class b implements a {
    private static b d = null;
    private static DeviceDataReportResult e;
    private w a = null;
    private BugTrackMessageService b = null;
    private DeviceDataReportService c = null;

    private b(Context context, String str) {
        aa aaVar = new aa();
        aaVar.a(str);
        this.a = new h(context);
        this.b = (BugTrackMessageService) this.a.a(BugTrackMessageService.class, aaVar);
        this.c = (DeviceDataReportService) this.a.a(DeviceDataReportService.class, aaVar);
    }

    public static synchronized b a(Context context, String str) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context, str);
            }
            bVar = d;
        }
        return bVar;
    }

    public final DeviceDataReportResult a(DeviceDataReportRequest deviceDataReportRequest) {
        if (this.c != null) {
            e = null;
            new Thread(new c(this, deviceDataReportRequest)).start();
            int i = 300000;
            while (e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return e;
    }

    public final boolean a(String str) {
        if (a.a(str)) {
            return false;
        }
        boolean booleanValue;
        if (this.b != null) {
            String str2 = null;
            try {
                str2 = this.b.logCollect(a.e(str));
            } catch (Exception e) {
            }
            if (!a.a(str2)) {
                booleanValue = ((Boolean) new JSONObject(str2).get("success")).booleanValue();
                return booleanValue;
            }
        }
        booleanValue = false;
        return booleanValue;
    }
}
