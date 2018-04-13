package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.PrivateChatBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class PrivateChatBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        PrivateChatBean privateChatBean = (PrivateChatBean) JsonParseUtils.json2Obj(jSONObject.toString(), PrivateChatBean.class);
        privateChatBean.setTypeId(i);
        return privateChatBean;
    }
}
