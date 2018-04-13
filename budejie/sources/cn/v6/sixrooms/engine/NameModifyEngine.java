package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class NameModifyEngine {
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str, String str2);
    }

    public NameModifyEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void modifyNewName(String str, String str2, String str3) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-aliasname.php");
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler afVar = new af(this, str);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("encpass", str3));
        arrayList.add(new BasicNameValuePair("logiuid", str2));
        arrayList.add(new BasicNameValuePair("alias", str));
        arrayList.add(new BasicNameValuePair(a.k, "1.3"));
        createInstance.sendAsyncRequest(afVar, padapiUrl, arrayList);
    }
}
