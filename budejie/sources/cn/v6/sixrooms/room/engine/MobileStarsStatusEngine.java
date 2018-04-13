package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class MobileStarsStatusEngine {
    protected static final String TAG = MobileStarsStatusEngine.class.getSimpleName();
    private StatusCallBack a;

    public interface StatusCallBack {
        void error(int i);

        void result(boolean z, String str);
    }

    public MobileStarsStatusEngine(StatusCallBack statusCallBack) {
        this.a = statusCallBack;
    }

    public void getMobileGiftPrivilege(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("logiuid", str));
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("encpass", str2));
        }
        if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4))) {
            arrayList.add(new BasicNameValuePair(x.af, str3));
            arrayList.add(new BasicNameValuePair(x.ae, str4));
        }
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new g(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-excGif.php"), arrayList);
    }
}
