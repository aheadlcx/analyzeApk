package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.room.game.GamePlaneEndBean;
import com.google.gson.Gson;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GamePlaneEndBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        Map map = null;
        JSONObject jSONObject2 = jSONObject.getJSONObject("content").getJSONObject("content");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("game");
        if (jSONObject2.has("users")) {
            map = (Map) new Gson().fromJson(jSONObject2.getJSONObject("users").toString(), new a(this).getType());
        }
        return new GamePlaneEndBean(jSONObject3.getString("gid"), jSONObject3.getString("outer"), map);
    }
}
