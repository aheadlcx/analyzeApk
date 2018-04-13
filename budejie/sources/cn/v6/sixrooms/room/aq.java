package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.IM.IMMessageLastManager;
import cn.v6.sixrooms.room.interfaces.OnRoomIMListener;
import cn.v6.sixrooms.socket.IM.IMListener;
import cn.v6.sixrooms.socket.IM.IMSocketUtil;
import org.json.JSONObject;

final class aq implements IMListener {
    final /* synthetic */ RoomActivity a;

    aq(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onContentReceive(int i, long j, String str, JSONObject jSONObject) {
        if (str.equals(IMSocketUtil.T_ADD_FRIEND)) {
            this.a.af.post(new ar(this, i, str, jSONObject));
        }
    }

    public final void onActionReceive(int i, long j, String str) {
        switch (i) {
            case 102:
            case 205:
            case 701:
                if (this.a.v instanceof OnRoomIMListener) {
                    this.a.v.onIMMsgNumChange(IMMessageLastManager.getInstance().getNewMsgCount());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
