package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateCoinWealthBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        return (MessageBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), UpdateCoinWealthBean.class);
    }
}
