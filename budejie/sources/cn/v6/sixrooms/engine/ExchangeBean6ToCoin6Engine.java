package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class ExchangeBean6ToCoin6Engine {
    public static final String PADAPI = "user-exchange.php";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public ExchangeBean6ToCoin6Engine(CallBack callBack) {
        this.a = callBack;
    }

    public void sendRequest(String str, String str2, String str3, String str4) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, PADAPI);
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler pVar = new p(this);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("encpass", str));
        arrayList.add(new BasicNameValuePair("logiuid", str2));
        arrayList.add(new BasicNameValuePair(SocialConstants.PARAM_ACT, "p"));
        arrayList.add(new BasicNameValuePair("rtype", "json"));
        arrayList.add(new BasicNameValuePair("pwealth", str3));
        arrayList.add(new BasicNameValuePair("user_coin_v", str4));
        createInstance.sendAsyncRequest(pVar, padapiUrl, arrayList);
    }
}
