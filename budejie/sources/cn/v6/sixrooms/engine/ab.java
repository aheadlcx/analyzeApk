package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONArray;
import org.json.JSONObject;

final class ab extends VLAsyncHandler<String> {
    final /* synthetic */ GetRoomMsgSysEngine a;

    ab(GetRoomMsgSysEngine getRoomMsgSysEngine) {
        this.a = getRoomMsgSysEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if (!"001".equals(string)) {
                    GetRoomMsgSysEngine.a(this.a).handleErrorInfo(string, string2);
                } else if (JsonParseUtils.isJsonArray(string2)) {
                    JSONArray jSONArray = new JSONArray(string2);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        GetRoomMsgSysEngine.a(this.a).onRoomMsgSysResult(jSONArray.getJSONObject(i));
                    }
                } else {
                    GetRoomMsgSysEngine.a(this.a).onRoomMsgSysResult(new JSONObject(string2));
                }
            } catch (Exception e) {
                GetRoomMsgSysEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && GetRoomMsgSysEngine.a(this.a) != null) {
            GetRoomMsgSysEngine.a(this.a).error(1006);
        }
    }
}
