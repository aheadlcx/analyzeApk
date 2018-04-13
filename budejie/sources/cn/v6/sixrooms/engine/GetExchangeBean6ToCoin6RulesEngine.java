package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.ExchangeRulesBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class GetExchangeBean6ToCoin6RulesEngine {
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void success(ExchangeRulesBean exchangeRulesBean);
    }

    public GetExchangeBean6ToCoin6RulesEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void sendRequest(String str, String str2, String str3, String str4) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-getAngelPayList.php");
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler yVar = new y(this);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("encpass", str));
        arrayList.add(new BasicNameValuePair("logiuid", str2));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("rid", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("type", str4));
        }
        arrayList.add(new BasicNameValuePair(a.k, "1.3"));
        createInstance.sendAsyncRequest(yVar, padapiUrl, arrayList);
    }
}
