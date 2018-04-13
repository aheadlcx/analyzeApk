package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class f extends a {
    public f(JSONObject jSONObject, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/ugcvideo/rec4zy/digust"), jSONObject, null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, String[] strArr) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        if (0 != j) {
            try {
                b.put("id", j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < strArr.length) {
            try {
                jSONArray.put(Integer.valueOf(strArr[i]));
                i++;
            } catch (Exception e2) {
            }
        }
        b.put("reasons", jSONArray);
        return b;
    }

    public static LinkedHashMap<String, String> a() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("1", "内容质量差");
        linkedHashMap.put("2", "不喜欢该类内容");
        return linkedHashMap;
    }
}
