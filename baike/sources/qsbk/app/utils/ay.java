package qsbk.app.utils;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

final class ay extends SimpleHttpTask {
    ay(String str, SimpleCallBack simpleCallBack) {
        super(str, simpleCallBack);
    }

    protected String a(Void... voidArr) {
        ReportCommon.a = ReportCommon.a + 1;
        ReportCommon.b = true;
        return super.a(voidArr);
    }
}
