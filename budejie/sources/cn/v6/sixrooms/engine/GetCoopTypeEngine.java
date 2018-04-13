package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;

public class GetCoopTypeEngine {
    protected static final String TAG = "GetCoopTypeEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void success(String str, String str2);
    }

    public GetCoopTypeEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.a = callBack;
    }

    public void getCoopType(String str) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new x(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-getUnicomItem.php") + "&coop=" + str, "");
    }
}
