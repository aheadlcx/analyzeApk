package qsbk.app.utils;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

final class aw extends SimpleHttpTask {
    aw(String str, SimpleCallBack simpleCallBack) {
        super(str, simpleCallBack);
    }

    protected String a(Void... voidArr) {
        ReportArticle.a = ReportArticle.a + 1;
        ReportArticle.b = true;
        return super.a(voidArr);
    }
}
