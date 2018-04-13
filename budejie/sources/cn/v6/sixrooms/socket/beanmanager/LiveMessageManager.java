package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.LiveMessage;
import cn.v6.sixrooms.bean.MessageBean;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveMessageManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean liveMessage = new LiveMessage();
        liveMessage.setTypeId(i);
        liveMessage.setTm(jSONObject.optLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        liveMessage.setToUid(jSONObject.optString("to"));
        liveMessage.setContent(jSONObject.optString("content"));
        return liveMessage;
    }
}
