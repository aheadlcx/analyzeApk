package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;

public class GtAuthEngine {
    private CallBack a;

    public interface CallBack {
        void phoneError(int i);

        void serverError(String str, String str2);

        void success(int i, String str, String str2);
    }

    public GtAuthEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getGtChanllenge() {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), UrlStrs.URL_INDEX_INFO + "?padapi=getGeeGt.php&prod=10007", "");
    }
}
