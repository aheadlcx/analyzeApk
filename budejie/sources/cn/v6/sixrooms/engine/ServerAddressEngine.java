package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class ServerAddressEngine {
    protected static final String TAG = "ServerAddressEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void retChatAddress(List<String> list, List<String> list2);

        void retIMAddress(List<String> list, List<String> list2);
    }

    public ServerAddressEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getChatServerAddress(String str) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(RoomBaseFragment.RUID_KEY, str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("type", StatisticCodeTable.CHAT);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        a(arrayList, StatisticCodeTable.CHAT);
    }

    public void getIMServerAddress(String str) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("type", IXAdRequestInfo.IMSI);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        a(arrayList, "IM");
    }

    private void a(List<NameValuePair> list, String str) {
        String str2;
        String str3 = UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-chatConf.php";
        if ("IM".equalsIgnoreCase(str)) {
            Object obj = "online";
            Properties properties = new Properties();
            try {
                properties.load(V6Coop.getInstance().getContext().getAssets().open("config.properties"));
                obj = properties.getProperty("type");
            } catch (IOException e) {
                LogUtils.e(TAG, e.getMessage());
            }
            if ("dev".equals(obj)) {
                str2 = "http://dev.v.6.cn/coop/mobile/chatConf.php";
                NetworkServiceSingleton.createInstance().sendAsyncRequest(new bd(this, str), str2, list);
            }
        }
        str2 = str3;
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bd(this, str), str2, list);
    }
}
