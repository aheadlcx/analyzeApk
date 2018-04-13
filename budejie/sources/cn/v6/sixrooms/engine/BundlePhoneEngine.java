package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class BundlePhoneEngine {
    protected static final String TAG = BundlePhoneEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void bundleSucceed(String str, String str2);

        void error(int i);

        void handleErrorInfo(String str, String str2);
    }

    public BundlePhoneEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void bundlePhone(String str, String str2, String str3, String str4) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "auth-bundleMobile.php");
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str4);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("mobile", str);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new h(this), padapiUrl, arrayList);
        LogUtils.i(TAG, "list=" + arrayList.toString());
    }
}
