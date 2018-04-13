package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class InitHeadLineEngine {
    protected static final String TAG = InitHeadLineEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(InitHeadLineBean initHeadLineBean);
    }

    public InitHeadLineEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getInitHeadLine(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("padapi", "event-getInitHeadLine.php");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("type", str);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        String url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList);
        List arrayList2 = new ArrayList();
        basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str3);
        arrayList2.add(basicNameValuePair2);
        arrayList2.add(basicNameValuePair3);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new f(this), url, arrayList2);
    }
}
