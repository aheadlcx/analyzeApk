package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class UnbundlePhoneEngine {
    protected static final String TAG = UnbundlePhoneEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void unbundleSucceed(String str);
    }

    public UnbundlePhoneEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void unbundlePhone(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(new BasicNameValuePair("logiuid", str3));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bf(this), UrlStrs.URL_INDEX_INFO + "?padapi=auth-unBundleMobile.php", arrayList);
    }
}
