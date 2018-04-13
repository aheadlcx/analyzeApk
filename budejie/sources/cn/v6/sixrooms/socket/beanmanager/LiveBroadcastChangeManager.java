package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.LiveBroadcastBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveBroadcastChangeManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        LiveBroadcastBean liveBroadcastBean = (LiveBroadcastBean) JsonParseUtils.json2Obj(jSONObject.getJSONObject("content").toString(), LiveBroadcastBean.class);
        liveBroadcastBean.setTypeId(i);
        liveBroadcastBean.setTm(jSONObject.optLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        return liveBroadcastBean;
    }
}
