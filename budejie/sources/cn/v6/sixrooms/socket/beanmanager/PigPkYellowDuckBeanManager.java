package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.room.game.PigPkYellowDuckUser;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PigPkYellowDuckBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        Object obj;
        Object pigPkYellowDuckUser;
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        String string = jSONObject2.getString("state");
        String string2 = jSONObject2.getString("title");
        String string3 = jSONObject2.getString("etm");
        String string4 = jSONObject2.getString("type");
        jSONObject2 = jSONObject2.getJSONObject("prop");
        JSONObject jSONObject3 = jSONObject2.getJSONObject(GiftIdStrs.PIG_GIFT_ID);
        JSONObject jSONObject4 = jSONObject2.getJSONObject(GiftIdStrs.YELLOWDUCK_GIFT_ID);
        String string5 = jSONObject3.getString("captain");
        String string6 = jSONObject4.getString("captain");
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(string5);
        JsonElement parse2 = jsonParser.parse(string6);
        List arrayList = new ArrayList();
        if (parse.isJsonArray()) {
            PigPkYellowDuckUser pigPkYellowDuckUser2 = new PigPkYellowDuckUser(jSONObject3.getString("num"));
        } else {
            obj = (PigPkYellowDuckUser) JsonParseUtils.json2Obj(jSONObject3.toString(), PigPkYellowDuckUser.class);
        }
        if (parse2.isJsonArray()) {
            pigPkYellowDuckUser = new PigPkYellowDuckUser(jSONObject4.getString("num"));
        } else {
            pigPkYellowDuckUser = (PigPkYellowDuckUser) JsonParseUtils.json2Obj(jSONObject4.toString(), PigPkYellowDuckUser.class);
        }
        arrayList.add(pigPkYellowDuckUser);
        arrayList.add(obj);
        MessageBean pigPkYellowDuckBean = new PigPkYellowDuckBean();
        pigPkYellowDuckBean.setState(string);
        pigPkYellowDuckBean.setTitle(string2);
        pigPkYellowDuckBean.setEtm(string3);
        pigPkYellowDuckBean.setType(string4);
        pigPkYellowDuckBean.setProp(arrayList);
        return pigPkYellowDuckBean;
    }
}
