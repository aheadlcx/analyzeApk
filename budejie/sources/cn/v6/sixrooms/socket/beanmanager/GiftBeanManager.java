package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.GiftBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alipay.sdk.cons.b;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.google.analytics.tracking.android.HitTypes;
import org.json.JSONException;
import org.json.JSONObject;

public class GiftBeanManager extends MessageBeanManager {
    public Object progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        GiftBean giftBean = new GiftBean();
        giftBean.setTypeId(i);
        giftBean.setTm(jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        giftBean.setFid(jSONObject.getInt("fid"));
        giftBean.setFrid(jSONObject.getInt("frid"));
        giftBean.setFrom(jSONObject.getString(UserTrackerConstants.FROM));
        giftBean.setTo(jSONObject.getString("to"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        int i2 = jSONObject2.getInt(HitTypes.ITEM);
        giftBean.setItem(i2);
        giftBean.setNum(jSONObject2.getInt("num"));
        if (i2 == Integer.parseInt(GiftIdStrs.SUPER_FIREWORKS)) {
            giftBean.setTid(0);
            giftBean.setTrid(0);
        } else {
            giftBean.setTid(jSONObject.getInt(b.c));
            giftBean.setTrid(jSONObject.getInt("trid"));
        }
        giftBean.setMsg(jSONObject2.getString("msg"));
        return giftBean;
    }
}
