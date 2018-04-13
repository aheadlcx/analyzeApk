package cn.v6.sixrooms.pay.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class YeepayCardStatusEngine {
    protected static final String TAG = "YeepayCardStatus";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleResult(OrderStatusBean orderStatusBean);
    }

    public YeepayCardStatusEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void payCardStatus(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str3);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("orderid", str);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new g(this), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-payCardstatus.php", arrayList);
    }
}
