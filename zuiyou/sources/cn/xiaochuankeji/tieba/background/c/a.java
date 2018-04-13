package cn.xiaochuankeji.tieba.background.c;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.Member;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends cn.htjyb.b.a.a<Member> {
    private final String a = "data_member_history_record";
    private final String b = "data_member_history_record_new";
    private final int c = 10;
    private ArrayList<Member> d = new ArrayList();

    public /* synthetic */ Object itemAt(int i) {
        return a(i);
    }

    public a() {
        c();
    }

    private void c() {
        JSONArray optJSONArray;
        JSONObject a = b.a(d(), AppController.kDataCacheCharset);
        JSONObject a2 = b.a(e(), AppController.kDataCacheCharsetUTF8.name());
        if (a2 != null) {
            optJSONArray = a2.optJSONArray("list");
        } else if (a != null) {
            optJSONArray = a.optJSONArray("list");
        } else {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            this.d.add(new Member(optJSONArray.optJSONObject(i)));
        }
    }

    public void a() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Member) it.next()).serializeTo());
            }
            jSONObject.put("list", jSONArray);
            b.a(jSONObject, e(), AppController.kDataCacheCharsetUTF8.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private File d() {
        return new File(cn.xiaochuankeji.tieba.background.a.e().r() + "data_member_history_record");
    }

    private File e() {
        return new File(cn.xiaochuankeji.tieba.background.a.e().r() + "data_member_history_record_new");
    }

    public int itemCount() {
        return this.d.size();
    }

    public Member a(int i) {
        return (Member) this.d.get(i);
    }

    public void a(Member member) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            Member member2 = (Member) it.next();
            if (member2.getId() == member.getId()) {
                this.d.remove(member2);
                break;
            }
        }
        this.d.add(0, member);
        if (itemCount() >= 10) {
            for (int i = 0; i < (itemCount() - 10) + 1; i++) {
                this.d.remove(itemCount() - 1);
            }
        }
        notifyListUpdate();
        a();
    }

    public void b() {
        this.d.clear();
        notifyListUpdate();
        a();
    }
}
