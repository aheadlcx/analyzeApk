package cn.tatagou.sdk.c.a;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class d {
    protected List<b> a = new ArrayList();
    private String b = "";
    private String c = "";

    public d(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public void PutTopic(String str) {
        this.b = str;
    }

    public void PutSource(String str) {
        this.c = str;
    }

    public void PutLog(b bVar) {
        this.a.add(bVar);
    }

    public String LogGroupToJsonString() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("__source__", this.c);
        jSONObject.put("__topic__", this.b);
        JSONArray jSONArray = new JSONArray();
        for (b GetContent : this.a) {
            jSONArray.add(new JSONObject(GetContent.GetContent()));
        }
        jSONObject.put("__logs__", jSONArray);
        return jSONObject.toJSONString();
    }
}
