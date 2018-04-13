package cn.xiaochuan.jsbridge.data;

import com.a.a.a.a;
import com.a.a.a.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    public static final String a = "checkJsApi";
    public static a b = new a() {
        public void a(String str, e eVar) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                JSONObject jSONObject = new JSONObject();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    int i2;
                    String string = jSONArray.getString(i);
                    if (f.c.contains(string)) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    jSONObject.put(string, i2);
                }
                eVar.a(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
                eVar.a("{\"ret\":-1}");
            }
        }
    };
    private static List<String> c = new ArrayList();

    static {
        c.add(a);
        c.add(JSAssess.a);
        c.add(a.a);
        c.add(JSCreateUgcVideo.a);
        c.add(b.a);
        c.add(c.a);
        c.add(d.a);
        c.add(JSMarket.a);
        c.add(e.a);
        c.add(JSMenuConfig.a);
        c.add(JSOpen.a);
        c.add(JSOpenUgcVideo.a);
        c.add(JSPost.a);
        c.add(JSProfile.a);
        c.add(JSReview.a);
        c.add("share");
        c.add(JSToast.a);
        c.add(JSTopic.a);
        c.add(JSUploadFile.a);
    }
}
