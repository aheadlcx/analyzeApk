package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.SocketRoomMessageSofaBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SofaBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        LogUtils.d("SofaBeanManager", "socket_sofa: " + jSONObject);
        SocketRoomMessageSofaBean socketRoomMessageSofaBean = (SocketRoomMessageSofaBean) JsonParseUtils.json2Obj(jSONObject.toString(), SocketRoomMessageSofaBean.class);
        socketRoomMessageSofaBean.setTypeId(i);
        return socketRoomMessageSofaBean;
    }
}
