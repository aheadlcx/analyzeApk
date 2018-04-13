package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class HeadlineBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        long optLong = jSONObject.optLong(IXAdRequestInfo.MAX_TITLE_LENGTH);
        OnHeadlineBeans onHeadlineBeans = (OnHeadlineBeans) JsonParseUtils.json2Obj(jSONObject.getJSONObject("content").toString(), OnHeadlineBeans.class);
        onHeadlineBeans.setTypeId(135);
        onHeadlineBeans.setTm(optLong);
        return onHeadlineBeans;
    }
}
