package cn.xiaochuankeji.tieba.background.splash;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SplashInfo {
    @JSONField(name = "data")
    public Data data;
    @JSONField(name = "dur")
    public int dur;
    @JSONField(name = "et")
    public long et;
    @JSONField(name = "st")
    public long st;
    @JSONField(name = "type")
    public int type;
    @JSONField(name = "url")
    public String url;

    public static class Data {
        @JSONField(name = "tid")
        public int tid;
        @JSONField(name = "url")
        public String url;
    }

    public static SplashInfo fromJson(JSONObject jSONObject) {
        SplashInfo splashInfo = new SplashInfo();
        splashInfo.type = jSONObject.optInt("type");
        splashInfo.url = jSONObject.optString("url");
        splashInfo.dur = jSONObject.optInt("dur");
        splashInfo.et = (long) jSONObject.optInt("end_t");
        splashInfo.data = new Data();
        splashInfo.data.tid = jSONObject.optJSONObject("data").optInt("tid");
        splashInfo.data.url = jSONObject.optJSONObject("data").optString("url");
        return splashInfo;
    }

    public static List<SplashInfo> fromJsonArray(JSONArray jSONArray) {
        List<SplashInfo> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(fromJson(jSONArray.getJSONObject(i)));
            } catch (Exception e) {
            }
        }
        return arrayList;
    }
}
