package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

public class SpeakManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean authKeyBean = new AuthKeyBean();
        authKeyBean.setTypeId(i);
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        authKeyBean.setAuthKey(jSONObject2.getString("authKey"));
        authKeyBean.setPriv(jSONObject2.getString(CommonStrs.ROOMINFOENGINE_PRIV));
        return authKeyBean;
    }
}
