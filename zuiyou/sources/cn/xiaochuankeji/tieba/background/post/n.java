package cn.xiaochuankeji.tieba.background.post;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class n extends a {
    public n(long j, long j2, long j3, ArrayList<String> arrayList, String str, String str2, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/disgust"), a(j, j2, j3, arrayList, str, str2), null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, long j3, ArrayList<String> arrayList, String str, String str2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
            if (0 != j2) {
                b.put("tid", j2);
            }
            if (0 != j3) {
                b.put("pgcid", j3);
            }
            b.put("from", str2);
            b.put("is_new", 1);
            if (!(TextUtils.isEmpty(str) || arrayList == null)) {
                arrayList.add("5");
                b.put("msg", str);
            }
            if (arrayList != null && arrayList.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    jSONArray.put(Integer.valueOf(((String) it.next()).trim()));
                }
                b.put("reasons", jSONArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
