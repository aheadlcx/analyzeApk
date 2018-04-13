package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class GetUserVisibleEngine {
    public static final String USER_TYPE_INVISIBLE = "1";
    public static final String USER_TYPE_NEED_SVIP = "2";
    public static final String USER_TYPE_VISIBLE = "0";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public GetUserVisibleEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getUserVisible(String str, String str2) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-listPropState.php");
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler acVar = new ac(this);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("logiuid", str));
        arrayList.add(new BasicNameValuePair("encpass", str2));
        createInstance.sendAsyncRequest(acVar, padapiUrl, arrayList);
    }
}
