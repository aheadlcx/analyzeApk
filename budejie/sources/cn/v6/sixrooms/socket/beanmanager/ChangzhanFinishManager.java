package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChangzhanFinishBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangzhanFinishManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ChangzhanFinishBean changzhanFinishBean = (ChangzhanFinishBean) JsonParseUtils.json2Obj(jSONObject.getString("content"), ChangzhanFinishBean.class);
        changzhanFinishBean.setTypeId(i);
        return changzhanFinishBean;
    }
}
