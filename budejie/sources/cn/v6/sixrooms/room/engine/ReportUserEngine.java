package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class ReportUserEngine {
    protected static final String TAG = ChangzhanStatusBean.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public ReportUserEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void reportUser(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("padapi", "coop-mobile-reportUser.php"));
        String url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList);
        List arrayList2 = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str3);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str4);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("touid", str);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("type", str2);
        arrayList2.add(basicNameValuePair);
        arrayList2.add(basicNameValuePair2);
        arrayList2.add(basicNameValuePair3);
        arrayList2.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new i(this), url, arrayList2);
    }
}
