package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class GainMobileStarsEngine {
    protected static final String TAG = "GainMobileStarsEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str, String str2);

        void start();
    }

    public GainMobileStarsEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void sendGetMobileStarsRequest(String str, String str2, String str3) {
        this.a.start();
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(SocialConstants.PARAM_ACT, str3);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(a.k, "2.5");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new t(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-excGif.php"), arrayList);
    }
}
