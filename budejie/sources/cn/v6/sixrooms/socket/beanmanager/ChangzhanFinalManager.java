package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ChangzhanFinalUsersBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangzhanFinalManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ChangzhanFinalUsersBean changzhanFinalUsersBean = (ChangzhanFinalUsersBean) JsonParseUtils.json2Obj(jSONObject.toString(), ChangzhanFinalUsersBean.class);
        changzhanFinalUsersBean.setTypeId(i);
        return changzhanFinalUsersBean;
    }
}
