package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;

public class CoopEncyptEngine {
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void success(String str, String str2);
    }

    public CoopEncyptEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getEncyptKey(String str, String str2, String str3) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new m(this), UrlStrs.URL_COOP_ENCYPT + "?coop=" + str2 + "&flag=" + str3 + "&packname=" + str, "");
    }
}
