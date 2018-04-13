package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateGiftNumBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        String jSONObject2 = jSONObject.toString();
        ArrayList arrayList = new ArrayList();
        if (!(jSONObject2.contains("[") && jSONObject2.contains("]"))) {
            jSONObject2 = jSONObject.getJSONObject("content").toString();
            for (String split : jSONObject2.substring(jSONObject2.indexOf("{") + 1, jSONObject2.indexOf(h.d)).split(",")) {
                String[] split2 = split.split(":");
                arrayList.add(new RepertoryBean(split2[0].substring(split2[0].indexOf("\"") + 1, split2[0].lastIndexOf("\"")), split2[1].replace("\"", "")));
            }
        }
        return new UpdateGiftNumBean(arrayList);
    }
}
