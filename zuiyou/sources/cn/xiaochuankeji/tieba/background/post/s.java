package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import com.iflytek.cloud.SpeechConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class s extends a {
    public s(JSONObject jSONObject, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/subject/disgust"), jSONObject, null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, ArrayList<String> arrayList) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        if (0 != j) {
            try {
                b.put(SpeechConstant.IST_SESSION_ID, j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (0 != j2) {
            b.put("mid", j2);
        }
        if (arrayList != null && arrayList.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(Integer.valueOf(((String) it.next()).trim()));
            }
            b.put("reasons", jSONArray);
        }
        return b;
    }

    public static LinkedHashMap<String, String> a() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("1", "内容质量差");
        linkedHashMap.put("2", "不喜欢该类内容");
        return linkedHashMap;
    }
}
