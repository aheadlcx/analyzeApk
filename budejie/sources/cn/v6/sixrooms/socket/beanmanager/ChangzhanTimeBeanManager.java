package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChangzhanTimeBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangzhanTimeBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ChangzhanTimeBean changzhanTimeBean = (ChangzhanTimeBean) JsonParseUtils.json2Obj(jSONObject.toString(), ChangzhanTimeBean.class);
        changzhanTimeBean.setTypeId(i);
        return changzhanTimeBean;
    }
}
