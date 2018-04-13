package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FlyTextBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        return (FlyTextBean) JsonParseUtils.json2Obj(jSONObject.toString(), FlyTextBean.class);
    }
}
