package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.room.game.GamePlaneStartBean;
import org.json.JSONException;
import org.json.JSONObject;

public class GamePlaneStartBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        return new GamePlaneStartBean(jSONObject2.getString("rid"), jSONObject2.getString("gameid"), jSONObject2.getString("flag"));
    }
}
