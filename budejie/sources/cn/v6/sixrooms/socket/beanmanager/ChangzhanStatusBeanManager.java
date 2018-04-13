package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangzhanStatusBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ChangzhanStatusBean changzhanStatusBean = (ChangzhanStatusBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), ChangzhanStatusBean.class);
        changzhanStatusBean.setTypeId(i);
        return changzhanStatusBean;
    }
}
