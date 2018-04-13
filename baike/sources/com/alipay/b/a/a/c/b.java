package com.alipay.b.a.a.c;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.DefaultRpcClient;
import com.alipay.android.phone.mrpc.core.RpcClient;
import com.alipay.android.phone.mrpc.core.RpcParams;
import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

public final class b implements a {
    private static b d = null;
    private static DataReportResult e;
    private RpcClient a = null;
    private BugTrackMessageService b = null;
    private DataReportService c = null;

    private b(Context context, String str) {
        RpcParams rpcParams = new RpcParams();
        rpcParams.setGwUrl(str);
        this.a = new DefaultRpcClient(context);
        this.b = (BugTrackMessageService) this.a.getRpcProxy(BugTrackMessageService.class, rpcParams);
        this.c = (DataReportService) this.a.getRpcProxy(DataReportService.class, rpcParams);
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

    public final DataReportResult a(DataReportRequest dataReportRequest) {
        if (this.c != null) {
            e = null;
            new Thread(new c(this, dataReportRequest)).start();
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
            } catch (Throwable th) {
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
