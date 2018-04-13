package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class PropActionEngine {
    protected static final String TAG = "PropActionEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(boolean z);

        void start(boolean z);
    }

    public PropActionEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void actionProp(String str, String str2, String str3, String str4, String str5) {
        this.a.start("1".equals(str4));
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(CommonStrs.ROOMINFOENGINE_PRIV, Constants.VIA_REPORT_TYPE_QQFAVORITES);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(SocialConstants.PARAM_ACT, str3);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("status", str4);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("encpass", str2);
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("propid", str5);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new am(this), UrlStrs.URL_INDEX_INFO + "?padapi=user-prop-listPriv.php", arrayList);
    }
}
