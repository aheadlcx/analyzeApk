package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.ConfigUpdateBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class ConfigUpdateEngine {
    protected static final String TAG = ConfigUpdateEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(ConfigUpdateBean configUpdateBean);
    }

    public ConfigUpdateEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getConfigUpdate(String str, String str2, String str3, String str4, String str5, String str6) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str6);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(a.k, "2.5");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair("padapi", "coop-mobile-giftupdate.php"));
        arrayList2.add(new BasicNameValuePair("logiuid", str5));
        arrayList2.add(new BasicNameValuePair("ver", str));
        arrayList2.add(new BasicNameValuePair("pver", str2));
        arrayList2.add(new BasicNameValuePair("fver", str3));
        arrayList2.add(new BasicNameValuePair("nver", str4));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }
}
