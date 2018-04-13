package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class GetRoomMsgSysEngine {
    protected static final String TAG = "GetRoomMsgSysEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void onRoomMsgSysResult(JSONObject jSONObject);
    }

    public GetRoomMsgSysEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getRoomMsgSys(String str) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ab(this), UrlStrs.URL_INDEX_INFO + "?padapi=room-getRoomMsgSys.php&t=" + str, "");
    }
}
