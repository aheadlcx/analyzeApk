package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.MessageBean;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatPermissionBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean chatPermissionBean = new ChatPermissionBean();
        chatPermissionBean.setTypeId(i);
        chatPermissionBean.setTm(jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        chatPermissionBean.setChatType(jSONObject.getJSONObject("content").getInt("value"));
        return chatPermissionBean;
    }
}
