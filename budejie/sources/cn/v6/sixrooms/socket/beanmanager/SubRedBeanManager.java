package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.SubRedBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SubRedBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        SubRedBean subRedBean = (SubRedBean) JsonParseUtils.json2Obj(jSONObject.toString(), SubRedBean.class);
        subRedBean.setTypeId(i);
        return subRedBean;
    }
}
