package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        if (jSONObject2 == null) {
            return null;
        }
        WelcomeBean welcomeBean = (WelcomeBean) JsonParseUtils.json2Obj(jSONObject2.toString(), WelcomeBean.class);
        welcomeBean.setTypeId(i);
        welcomeBean.setTm(jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        String[] split = welcomeBean.getRich().split("-");
        welcomeBean.setRich(split[0]);
        welcomeBean.setUid(split[1]);
        return welcomeBean;
    }
}
