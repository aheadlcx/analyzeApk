package cn.xiaochuankeji.tieba.background.favorite;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends d<Favorite> {
    public static f a;
    private boolean b = false;
    private long c = 0;
    private a d;

    public interface a {
        void d();

        void k_();
    }

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public static f a() {
        if (a == null) {
            a = new f();
        }
        return a;
    }

    private f() {
    }

    public void refresh() {
        super.refresh();
    }

    protected b getHttpEngine() {
        return cn.xiaochuankeji.tieba.background.a.d();
    }

    public void a(Favorite favorite) {
        this._items.add(0, favorite);
        if (this.d != null) {
            this.d.k_();
        }
        notifyListUpdate();
    }

    public void a(long j) {
        for (int i = 0; i < itemCount(); i++) {
            Favorite favorite = (Favorite) itemAt(i);
            if (favorite.getId() == j) {
                this._items.remove(favorite);
                if (this.d != null) {
                    this.d.d();
                }
                notifyListUpdate();
                return;
            }
        }
    }

    public void a(long j, int i) {
        for (int i2 = 0; i2 < itemCount(); i2++) {
            Favorite favorite = (Favorite) itemAt(i2);
            if (favorite.getId() == j) {
                favorite.setPostCount(i);
                notifyListUpdate();
                return;
            }
        }
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/list");
    }

    protected Favorite a(JSONObject jSONObject) {
        return new Favorite(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        jSONObject.put("mid", cn.xiaochuankeji.tieba.background.a.g().c());
        if (isQueryMore()) {
            jSONObject.put("t", this.c);
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        this.c = jSONObject.optLong("t");
        if (jSONObject.optInt("more") != 1) {
            z = false;
        }
        this.b = z;
    }

    public boolean hasMore() {
        return this.b;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void b(a aVar) {
        this.d = aVar;
    }
}
