package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.CustomExceptionBean;
import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.BoxingBean;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthKeyBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        System.out.print("TYPEID_408---contentJson---" + jSONObject.toString());
        AuthKeyBean authKeyBean = new AuthKeyBean();
        authKeyBean.setTypeId(i);
        JSONObject jSONObject2 = jSONObject.getJSONObject("content").getJSONObject("404").getJSONObject("content");
        authKeyBean.setPriv(jSONObject2.getString(CommonStrs.ROOMINFOENGINE_PRIV));
        authKeyBean.setAuthKey(jSONObject2.getString("authKey"));
        authKeyBean = (AuthKeyBean) a(authKeyBean, jSONObject2);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("userInfo");
        if (jSONObject3.has("eventDefend")) {
            authKeyBean.setEventDefend(jSONObject3.getInt("eventDefend"));
        }
        String str2 = "initTopGift";
        JSONObject jSONObject4 = jSONObject2.getJSONObject("userInfo");
        if (jSONObject4.has(str2)) {
            authKeyBean.setInitTopGift((InitTopGiftBean) JsonParseUtils.json2Obj(jSONObject4.getString(str2), InitTopGiftBean.class));
        }
        str2 = "mg";
        jSONObject2 = jSONObject2.getJSONObject("userInfo");
        if (jSONObject2.has(str2)) {
            str2 = jSONObject2.getString(str2);
            if (!JsonParseUtils.isJsonArray(str2)) {
                authKeyBean.setMiniGameBean((MiniGameBean) JsonParseUtils.json2Obj(str2, MiniGameBean.class));
            }
        }
        return authKeyBean;
    }

    private static MessageBean a(AuthKeyBean authKeyBean, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("userInfo");
            if (!jSONObject2.has("initBoxing")) {
                return authKeyBean;
            }
            String string = jSONObject2.getString("initBoxing");
            if (new JsonParser().parse(string).isJsonArray()) {
                return authKeyBean;
            }
            authKeyBean.setBoxingBean((BoxingBean) new Gson().fromJson(string, BoxingBean.class));
            return authKeyBean;
        } catch (Exception e) {
            authKeyBean = new CustomExceptionBean();
            authKeyBean.setData(jSONObject.toString());
            authKeyBean.setTag(CustomExceptionBean.TAG_DEBUG);
            authKeyBean.setE(e);
            return authKeyBean;
        }
    }
}
