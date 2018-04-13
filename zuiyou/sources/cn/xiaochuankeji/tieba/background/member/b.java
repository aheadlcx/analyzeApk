package cn.xiaochuankeji.tieba.background.member;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public Member a;
    public int b;
    public int c;
    private int d = 0;

    public interface a {
        void a(boolean z, JSONArray jSONArray, ArrayList<Post> arrayList, int i, boolean z2, long j, int i2, String str);
    }

    public b(long j) {
        this.a = new Member(j);
    }

    public void a(final a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            jSONObject.put("mid", this.a.getId());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(1);
            jSONArray.put(2);
            jSONObject.put("c_types", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/profile"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ b b;

            public void onTaskFinish(d dVar) {
                cn.htjyb.netlib.b.a aVar = dVar.c;
                if (aVar.a) {
                    this.b.a = new Member(aVar.c.optJSONObject("member_info"));
                    this.b.b = aVar.c.optInt("gotlikes");
                    this.b.d = aVar.c.optInt("block");
                    int i = 0;
                    boolean z = false;
                    long j = 0;
                    ArrayList arrayList = new ArrayList();
                    JSONObject optJSONObject = aVar.c.optJSONObject("postlist");
                    if (optJSONObject != null) {
                        i = optJSONObject.optInt("total");
                        this.b.c = i;
                        boolean z2 = optJSONObject.optInt("more") == 1;
                        j = optJSONObject.optLong("t");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("list");
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            arrayList.add(new Post(optJSONArray.optJSONObject(i2)));
                        }
                        z = z2;
                    }
                    if (aVar != null) {
                        aVar.a(true, optJSONObject == null ? null : optJSONObject.optJSONArray("list"), arrayList, i, z, j, this.b.d, null);
                    }
                } else if (aVar != null) {
                    aVar.a(false, null, null, 0, false, 0, this.b.d, dVar.c.c());
                }
            }
        }).b();
    }
}
