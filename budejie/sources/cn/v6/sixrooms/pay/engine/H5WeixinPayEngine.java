package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class H5WeixinPayEngine {
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleError(String str, String str2);

        void handleResutl(H5WeixinPay h5WeixinPay, String str);
    }

    public H5WeixinPayEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getWeixinH5PayUri(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("padapi", "user-wxpay-jspayh5.php");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("ovalue", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("payRMB", str2);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(SocialConstants.PARAM_TYPE_ID, "27");
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("pid", "8");
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair(a.k, "2.5");
        BasicNameValuePair basicNameValuePair7 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair8 = new BasicNameValuePair("logiuid", str4);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        arrayList.add(basicNameValuePair7);
        arrayList.add(basicNameValuePair8);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList), "");
    }
}
