package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.AnchorPrompt;
import cn.v6.sixrooms.bean.MessageBean;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class AnchorPromptManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean anchorPrompt = new AnchorPrompt();
        anchorPrompt.setTypeId(i);
        anchorPrompt.setTm(jSONObject.optLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        anchorPrompt.setToUid(jSONObject.optString("to"));
        anchorPrompt.setContent(jSONObject.optString("content"));
        anchorPrompt.setType(jSONObject.optString("type"));
        return anchorPrompt;
    }
}
