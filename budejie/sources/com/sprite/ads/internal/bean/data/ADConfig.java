package com.sprite.ads.internal.bean.data;

import com.sprite.ads.internal.bean.JsonInterface;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class ADConfig implements JsonInterface {
    public ConfigParams params;
    public List<String> sequence;

    public class ConfigParams {
        public int first;
        public int interval;
        public int repeat;
        public int stepinc;
    }

    public ADConfig(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    public void jsonToObject(JSONObject jSONObject) {
        if (jSONObject.has("params")) {
            this.params = new ConfigParams();
            JSONObject jSONObject2 = jSONObject.getJSONObject("params");
            if (jSONObject2.has("stepinc")) {
                this.params.stepinc = jSONObject2.getInt("stepinc");
            }
            if (jSONObject2.has(x.ap)) {
                this.params.interval = jSONObject2.getInt(x.ap);
            }
            if (jSONObject2.has("repeat")) {
                this.params.repeat = jSONObject2.getInt("repeat");
            }
            if (jSONObject2.has("first")) {
                this.params.first = jSONObject2.getInt("first");
            }
        }
        if (jSONObject.has("sequence")) {
            this.sequence = parseSequence(jSONObject.getString("sequence"));
        }
    }

    public List<String> parseSequence(String str) {
        List<String> arrayList = new ArrayList();
        if (!(str == null || "".equals(str) || "[]".equals(str))) {
            arrayList.addAll(Arrays.asList(str.substring(1, str.length() - 1).replace("\"", "").split(",")));
        }
        return arrayList;
    }
}
