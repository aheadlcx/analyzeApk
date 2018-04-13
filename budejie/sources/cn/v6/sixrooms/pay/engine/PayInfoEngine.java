package cn.v6.sixrooms.pay.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class PayInfoEngine {
    protected static final String TAG = "PayInfoEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleResult(WrapPaySelect wrapPaySelect, String str);
    }

    public PayInfoEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getPayInfo(String str, String str2) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-payInfo.php");
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler fVar = new f(this);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("encpass", str));
        arrayList.add(new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2));
        createInstance.sendAsyncRequest(fVar, padapiUrl, arrayList);
    }
}
