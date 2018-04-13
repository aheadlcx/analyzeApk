package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageBeanManager {
    public Object progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        return new MessageBean();
    }
}
