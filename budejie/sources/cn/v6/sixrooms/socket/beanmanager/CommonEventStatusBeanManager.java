package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonEventStatusBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        CommonEventStatusBean commonEventStatusBean = (CommonEventStatusBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), CommonEventStatusBean.class);
        commonEventStatusBean.setTypeId(i);
        return commonEventStatusBean;
    }
}
