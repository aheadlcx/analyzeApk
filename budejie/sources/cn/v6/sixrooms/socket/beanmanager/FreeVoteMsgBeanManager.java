package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.FreeVoteMsgBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FreeVoteMsgBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        FreeVoteMsgBean freeVoteMsgBean = (FreeVoteMsgBean) JsonParseUtils.json2Obj(jSONObject.toString(), FreeVoteMsgBean.class);
        freeVoteMsgBean.setTypeId(i);
        return freeVoteMsgBean;
    }
}
