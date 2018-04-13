package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class BundleInfoEngine {
    protected static final String TAG = BundleInfoEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void bundleInfo(String str, String str2, String str3);

        void error(int i);

        void handleErrorInfo(String str, String str2);
    }

    public BundleInfoEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getBundleInfo(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new g(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-userPhone.php"), arrayList);
        LogUtils.i(TAG, "list=" + arrayList.toString());
    }
}
