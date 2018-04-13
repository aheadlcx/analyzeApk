package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class CommonEventStatusEngine {
    protected static final String TAG = CommonEventStatusEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(CommonEventStatusBean commonEventStatusBean);
    }

    public CommonEventStatusEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getCommonEventStatus(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("padapi", "event-match-getUserScheduleStatus.php");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("rid", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("eid", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        String url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList);
        List arrayList2 = new ArrayList();
        basicNameValuePair2 = new BasicNameValuePair("logiuid", str3);
        basicNameValuePair3 = new BasicNameValuePair("encpass", str4);
        arrayList2.add(basicNameValuePair2);
        arrayList2.add(basicNameValuePair3);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), url, arrayList2);
    }
}
