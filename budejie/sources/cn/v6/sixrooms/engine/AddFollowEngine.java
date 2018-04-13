package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class AddFollowEngine {
    protected static final String TAG = "AddFollowEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(boolean z);
    }

    public AddFollowEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void addFollow(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("tuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("from_module", StatisticValue.getInstance().getFromAttentionPageModule());
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("watchid", StatisticValue.getInstance().getWatchid());
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-follow_add.php"), arrayList);
    }
}
