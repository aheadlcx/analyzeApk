package cn.v6.sixrooms.room;

import cn.v6.sixrooms.engine.GetRoomMsgSysEngine.CallBack;
import org.json.JSONObject;

final class k implements CallBack {
    final /* synthetic */ BaseRoomActivity a;

    k(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public final void error(int i) {
    }

    public final void handleErrorInfo(String str, String str2) {
    }

    public final void onRoomMsgSysResult(JSONObject jSONObject) {
        if (this.a.chatMsgSocket != null) {
            this.a.chatMsgSocket.onReceiveSuccessOnMain(jSONObject, "");
        }
    }
}
