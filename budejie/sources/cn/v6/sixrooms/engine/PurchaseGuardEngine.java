package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class PurchaseGuardEngine {
    protected static final String TAG = "PurchaseGuardEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void success();
    }

    public PurchaseGuardEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void buyProp(String str, String str2, String str3, String str4, int i, String str5) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("rid", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("id", str4);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair(IXAdRequestInfo.MAX_TITLE_LENGTH, String.valueOf(i));
        if (!TextUtils.isEmpty(str5)) {
            arrayList.add(new BasicNameValuePair("trid", str5));
        }
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair(a.k, "1.5");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ap(this), UrlStrs.URL_INDEX_INFO + "?padapi=user-prop-buyRoomProp.php", arrayList);
    }
}
