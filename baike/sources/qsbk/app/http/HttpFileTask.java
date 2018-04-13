package qsbk.app.http;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

public class HttpFileTask extends HttpTask {
    public HttpFileTask(String str, String str2, HttpCallBack httpCallBack) {
        super(str, str2, httpCallBack);
    }

    public HttpFileTask(String str, HttpCallBack httpCallBack) {
        super(str, httpCallBack);
    }

    protected String a(Void... voidArr) {
        String str = null;
        try {
            str = HttpClient.getIntentce().submitWithFile(this.b, this.e);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
        return str;
    }
}
