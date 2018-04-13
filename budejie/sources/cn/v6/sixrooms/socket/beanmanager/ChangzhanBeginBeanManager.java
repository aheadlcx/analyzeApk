package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChangzhanBeginBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangzhanBeginBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ChangzhanBeginBean changzhanBeginBean = (ChangzhanBeginBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), ChangzhanBeginBean.class);
        changzhanBeginBean.setTypeId(i);
        return changzhanBeginBean;
    }
}
