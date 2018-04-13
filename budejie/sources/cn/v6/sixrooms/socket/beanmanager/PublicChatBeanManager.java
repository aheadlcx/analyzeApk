package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.PublicChatBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicChatBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        PublicChatBean publicChatBean = (PublicChatBean) JsonParseUtils.json2Obj(jSONObject.toString(), PublicChatBean.class);
        publicChatBean.setTypeId(i);
        return publicChatBean;
    }
}
