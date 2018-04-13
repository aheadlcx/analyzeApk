package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.bean.ConfigureInfoBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class GetInfoEngine {
    protected static final String TAG = "GetInfoEngine";
    private static boolean a;
    private CallBack b;

    public interface CallBack {
        void error(int i);

        void result(ConfigureInfoBean configureInfoBean);
    }

    private GetInfoEngine() {
    }

    public static GetInfoEngine getInstance() {
        return GetInfoEngine$a.a();
    }

    public void addCallback(CallBack callBack) {
        this.b = callBack;
    }

    public void removeCallback() {
        this.b = null;
    }

    public void getInfo() {
        if (!a) {
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair(a.k, "1.0"));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new z(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-getinfo.php"), arrayList);
        }
    }
}
