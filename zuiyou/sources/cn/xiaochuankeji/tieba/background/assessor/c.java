package cn.xiaochuankeji.tieba.background.assessor;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.net.XCError;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static c a;
    private ArrayList<Post> b = new ArrayList();
    private a c;

    public interface a {
        void a(boolean z, String str);
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private c() {
        e();
    }

    private void e() {
        String f = f();
        String g = g();
        JSONObject a = b.a(new File(f), AppController.kDataCacheCharset);
        JSONObject a2 = b.a(new File(g), AppController.kDataCacheCharsetUTF8.name());
        if (a != null || a2 != null) {
            JSONArray optJSONArray;
            if (a2 != null) {
                optJSONArray = a2.optJSONArray("list");
            } else {
                optJSONArray = a.optJSONArray("list");
            }
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.b.add(new Post(optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    private String f() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "post_assess_list.dat";
    }

    private String g() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "post_assess_list_new.dat";
    }

    public void a(Post post) {
        if (this.b.remove(post)) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < this.b.size()) {
                try {
                    jSONArray.put(((Post) this.b.get(i)).serializeTo());
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            jSONObject.put("list", jSONArray);
            b.a(jSONObject, new File(g()), AppController.kDataCacheCharsetUTF8.name());
        }
    }

    public boolean b() {
        if (this.b.size() > 0) {
            return true;
        }
        return false;
    }

    public Post c() {
        if (this.b.size() > 0) {
            return (Post) this.b.get(0);
        }
        return null;
    }

    public void d() {
        this.b.clear();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("assessed_post_count", 0);
            jSONObject.put("last_save_count_time", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b.a(jSONObject, new File(g()), AppController.kDataCacheCharsetUTF8.name());
    }

    public void a(final a aVar) {
        if (this.b.size() == 0 && this.c == null) {
            this.c = new a(new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ c b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    int i = 0;
                    this.b.c = null;
                    if (jSONObject != null) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("list");
                        if (optJSONArray.length() == 0) {
                            aVar.a(false, "今天已审200条,明天再审喽!");
                            return;
                        }
                        while (i < optJSONArray.length()) {
                            this.b.b.add(new Post(optJSONArray.optJSONObject(i)));
                            i++;
                        }
                        aVar.a(true, null);
                    }
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ c b;

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.b.c = null;
                    aVar.a(false, xCError.getMessage());
                }
            });
            this.c.execute();
        }
    }
}
