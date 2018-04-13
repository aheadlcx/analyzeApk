package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class OtherPlaceLoginEngine {
    protected static final String TAG = OtherPlaceLoginEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void loginsucceed(String str, String str2);
    }

    public OtherPlaceLoginEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void otherPlaceLogin(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("ticket", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ag(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "auth-loginCheck.php"), arrayList);
        LogUtils.i(TAG, "list=" + arrayList.toString());
    }
}
