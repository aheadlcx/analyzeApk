package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomUpgradeMsgManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        MessageBean roomUpgradeMsg = new RoomUpgradeMsg();
        roomUpgradeMsg.setName(jSONObject2.optString("alias"));
        roomUpgradeMsg.setRank(jSONObject2.optInt("rank"));
        roomUpgradeMsg.setMsgid(jSONObject2.optString("msgid"));
        roomUpgradeMsg.setRid(jSONObject2.optString("rid"));
        roomUpgradeMsg.setRid(jSONObject2.optString("urid"));
        roomUpgradeMsg.setType(jSONObject2.optString("type"));
        return roomUpgradeMsg;
    }
}
