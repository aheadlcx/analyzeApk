package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.ActivityBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        ActivityBean activityBean = (ActivityBean) JsonParseUtils.json2Obj(jSONObject.toString(), ActivityBean.class);
        activityBean.setTypeId(i);
        return activityBean;
    }
}
