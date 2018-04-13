package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.RedBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class RedInfoEngine {
    protected static final String TAG = "RedNumEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handerError(String str, String str2);

        void result(RedBean redBean);
    }

    public RedInfoEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getRedInfo(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new aq(this), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-getRedCount.php", arrayList);
    }
}
