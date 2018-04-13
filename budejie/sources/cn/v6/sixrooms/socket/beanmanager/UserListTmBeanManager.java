package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.UserListTmBean;
import org.json.JSONException;
import org.json.JSONObject;

public class UserListTmBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean userListTmBean = new UserListTmBean();
        userListTmBean.setTypeId(i);
        userListTmBean.setTm(jSONObject.getLong("content"));
        return userListTmBean;
    }
}
