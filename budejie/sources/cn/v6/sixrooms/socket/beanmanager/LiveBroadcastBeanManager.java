package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.LiveBroadcastBean;
import cn.v6.sixrooms.bean.LiveUserInfo;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveBroadcastBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean liveBroadcastBean = new LiveBroadcastBean();
        liveBroadcastBean.setTypeId(i);
        liveBroadcastBean.setTm(jSONObject.optLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        liveBroadcastBean.setCurrent((LiveUserInfo) JsonParseUtils.json2Obj(jSONObject.getJSONObject("content").toString(), LiveUserInfo.class));
        return liveBroadcastBean;
    }
}
