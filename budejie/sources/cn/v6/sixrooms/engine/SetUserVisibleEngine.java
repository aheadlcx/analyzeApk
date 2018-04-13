package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class SetUserVisibleEngine {
    public static final String USER_TYPE_INVISIBLE = "1";
    public static final String USER_TYPE_VISIBLE = "0";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public SetUserVisibleEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void setUserVisible(String str, String str2, String str3) {
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler beVar = new be(this);
        String str4 = UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-listPriv.php";
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("status", str));
        arrayList.add(new BasicNameValuePair(CommonStrs.ROOMINFOENGINE_PRIV, AlibcJsResult.CLOSED));
        arrayList.add(new BasicNameValuePair("logiuid", str2));
        arrayList.add(new BasicNameValuePair("encpass", str3));
        createInstance.sendAsyncRequest(beVar, str4, arrayList);
    }
}
