package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.GiftBean;
import cn.v6.sixrooms.bean.SystemGift;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GirlSystemGiftManager extends MessageBeanManager {
    public Object progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        if (jSONObject.has("content")) {
            String string = jSONObject.getString("content");
            SystemGift systemGift = JsonParseUtils.isJson(string) ? (SystemGift) JsonParseUtils.json2Obj(string, SystemGift.class) : null;
            if (systemGift != null) {
                GiftBean giftBean = new GiftBean();
                giftBean.setSystem(true);
                giftBean.setTid(systemGift.getUid());
                giftBean.setTrid(systemGift.getRid());
                giftBean.setNum(systemGift.getNum());
                giftBean.setTo(systemGift.getTalias());
                giftBean.setItem(systemGift.getItem());
                giftBean.setFrom("系统");
                giftBean.setTypeId(409);
                return giftBean;
            }
        }
        return null;
    }
}
