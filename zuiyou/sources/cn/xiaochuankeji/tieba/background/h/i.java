package cn.xiaochuankeji.tieba.background.h;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.aiui.AIUIConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class i {
    private static i d;
    private String a = "block_upload_resume.dat";
    private String b = "block_upload_resume_new.dat";
    private ArrayList<a> c = new ArrayList();

    public static class a {
        public String a;
        public long b;
        public int c;
        public int d = -1;
    }

    public static i a() {
        if (d == null) {
            d = new i();
        }
        return d;
    }

    private i() {
        b();
    }

    private void b() {
        String d = d();
        e();
        JSONObject a = b.a(new File(d), AppController.kDataCacheCharset);
        JSONObject a2 = b.a(new File(d), AppController.kDataCacheCharset);
        if (a != null || a2 != null) {
            JSONArray optJSONArray;
            if (a2 != null) {
                optJSONArray = a2.optJSONArray("list");
            } else {
                optJSONArray = a.optJSONArray("list");
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                a aVar = new a();
                aVar.b = optJSONObject.optLong(RequestParameters.UPLOAD_ID);
                aVar.a = optJSONObject.optString(AIUIConstant.RES_TYPE_PATH);
                aVar.c = optJSONObject.optInt("bsize");
                aVar.d = optJSONObject.optInt("index");
                this.c.add(aVar);
            }
        }
    }

    public synchronized a a(String str) {
        a aVar;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            aVar = (a) it.next();
            if (aVar.a.equals(str)) {
                break;
            }
        }
        aVar = null;
        return aVar;
    }

    public synchronized void a(String str, int i) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (str.equals(aVar.a)) {
                aVar.d = i;
                c();
                break;
            }
        }
    }

    public synchronized void b(String str) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (str.equals(aVar.a)) {
                this.c.remove(aVar);
                c();
                break;
            }
        }
    }

    public synchronized void a(a aVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a aVar2 = (a) it.next();
            if (aVar.a.equals(aVar2.a)) {
                this.c.remove(aVar2);
                break;
            }
        }
        this.c.add(aVar);
        c();
    }

    private void c() {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AIUIConstant.RES_TYPE_PATH, aVar.a);
                jSONObject.put(RequestParameters.UPLOAD_ID, aVar.b);
                jSONObject.put("bsize", aVar.c);
                jSONObject.put("index", aVar.d);
                jSONArray.put(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("list", jSONArray);
            b.a(jSONObject2, new File(e()), AppController.kDataCacheCharsetUTF8.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String d() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + this.a;
    }

    private String e() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + this.b;
    }
}
