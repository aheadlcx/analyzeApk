package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeTmBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean noticeTmBean = new NoticeTmBean();
        noticeTmBean.setTypeId(i);
        noticeTmBean.setTm(jSONObject.getLong("content"));
        return noticeTmBean;
    }
}
