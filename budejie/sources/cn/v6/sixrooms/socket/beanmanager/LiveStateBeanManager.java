package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveStateBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        LogUtils.d("105", "SocketUtil.TYPEID_105---contentJson---" + jSONObject.toString());
        return (MessageBean) JsonParseUtils.json2Obj(jSONObject.toString(), LiveStateBean.class);
    }
}
