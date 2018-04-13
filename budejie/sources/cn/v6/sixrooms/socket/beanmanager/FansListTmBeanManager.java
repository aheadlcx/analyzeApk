package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.FansListTmBean;
import cn.v6.sixrooms.bean.MessageBean;
import org.json.JSONException;
import org.json.JSONObject;

public class FansListTmBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean fansListTmBean = new FansListTmBean();
        fansListTmBean.setTypeId(i);
        fansListTmBean.setTm(jSONObject.getLong("content"));
        return fansListTmBean;
    }
}
