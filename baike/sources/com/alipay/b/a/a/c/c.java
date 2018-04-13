package com.alipay.b.a.a.c;

import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

final class c implements Runnable {
    final /* synthetic */ DataReportRequest a;
    final /* synthetic */ b b;

    c(b bVar, DataReportRequest dataReportRequest) {
        this.b = bVar;
        this.a = dataReportRequest;
    }

    public final void run() {
        try {
            b.e = this.b.c.reportData(this.a);
        } catch (Throwable th) {
            b.e = new DataReportResult();
            b.e.success = false;
            b.e.resultCode = "static data rpc upload error, " + a.a(th);
            a.a(th);
        }
    }
}
