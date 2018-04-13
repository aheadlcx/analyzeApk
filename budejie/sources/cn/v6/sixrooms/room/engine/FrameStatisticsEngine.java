package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.alipay.sdk.sys.a;

public class FrameStatisticsEngine {
    protected static final String TAG = FrameStatisticsEngine.class.getSimpleName();
    private static String a = a.i;
    private CallBack b;

    public interface CallBack {
        void error(int i);

        void success();
    }

    public FrameStatisticsEngine(CallBack callBack) {
        this.b = callBack;
    }

    public void sendFrameStatistics(String str, String str2, String str3, String str4, String str5) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new d(this), UrlStrs.FRAME_STATISTICS_URL + "?p=" + a + "&d=" + str + "&t=" + str2 + "&f=" + str3 + "&w=" + str4 + "&u=" + str5, "");
    }
}
