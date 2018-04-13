package cn.xiaochuankeji.tieba.background.review;

import cn.htjyb.b.a.c;
import cn.htjyb.netlib.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.utils.h;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends cn.htjyb.b.a.d<Comment> {
    private long a;
    private c b;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public d(long j) {
        this.a = j;
    }

    public void a() {
        clear();
        Object eVar = new e(this.a, true);
        this.b = eVar;
        if (itemCount() > 0) {
            eVar.a(((Comment) itemAt(itemCount() - 1))._createTime);
        }
    }

    public void a(ArrayList<Comment> arrayList, long j, String str, boolean z, long j2) {
        clear();
        this.b = new f(this.a, j, str, z, j2);
        a((ArrayList) arrayList);
    }

    private void a(ArrayList<Comment> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Comment comment = (Comment) it.next();
            if (comment != null) {
                this._items.add(comment);
            }
        }
        notifyListUpdate();
        notifyQueryFinish(true, null);
    }

    protected b getHttpEngine() {
        return a.d();
    }

    public void a(Comment comment) {
        this._items.remove(comment);
    }

    protected String getQueryUrl() {
        if (this.b == null) {
            return cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/new_reviews");
        }
        return this.b.a();
    }

    protected Comment a(JSONObject jSONObject) {
        return new Comment(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        h.a(BaseApplication.getAppContext(), "zy_event_postdetail_page", "最新评论请求次数");
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        if (this.b != null) {
            this.b.a(jSONObject);
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        if (this.b != null) {
            this.b.b(jSONObject);
        }
    }

    public boolean hasMore() {
        if (this.b == null) {
            return false;
        }
        return this.b.b();
    }

    public void refresh() {
        clear();
        super.refresh();
    }

    public void clear() {
        super.clear();
        if (this.b != null) {
            this.b.c();
        }
    }
}
