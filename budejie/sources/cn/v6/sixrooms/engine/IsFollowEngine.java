package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class IsFollowEngine {
    protected static final String TAG = "IsFollowEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(boolean z);
    }

    public IsFollowEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getIsFollowLive(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("rid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ad(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-isFollow.php"), arrayList);
    }

    public void getIsFollowLiveNew(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(RoomBaseFragment.RUID_KEY, str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ae(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "coop-mobile-isFollow.php"), arrayList);
    }
}
