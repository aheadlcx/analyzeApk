package cn.xiaochuankeji.tieba.background.review;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import com.iflytek.cloud.SpeechConstant;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends d<Comment> {
    private long a;
    private long b;
    private boolean c;
    private long d;
    private long e;
    private String f;
    private long g;
    private Post h;
    private int i;
    private String j;
    private int k;
    private int l;
    private String m;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public c(long j, long j2, boolean z, int i) {
        this.g = 0;
        this.j = "ct";
        this.k = 0;
        this.a = j;
        this.b = j2;
        this.c = z;
        this.l = i;
    }

    public c(long j, long j2, boolean z, long j3, long j4, String str) {
        this(j, j2, z, 0);
        this.d = j3;
        this.e = j4;
        this.f = str;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        if (this.f == null) {
            return cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/sub_reviews");
        }
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/query_reviews");
    }

    protected Comment a(JSONObject jSONObject) {
        return new Comment(jSONObject);
    }

    public void a(Comment comment) {
        if (this._items.size() >= 1) {
            this._items.add(1, comment);
            notifyListUpdate();
        }
    }

    public void a(long j) {
        Iterator it = this._items.iterator();
        while (it.hasNext()) {
            Comment comment = (Comment) it.next();
            if (comment._id == j) {
                this._items.remove(comment);
                notifyListUpdate();
                return;
            }
        }
    }

    public Post a() {
        return this.h;
    }

    public void a(String str) {
        this.j = str;
    }

    public String b() {
        if (this.f != null) {
            return null;
        }
        return this.j;
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("pid", this.a);
            jSONObject.put("prid", this.b);
            jSONObject.put("offset", this.g);
            if (this.c) {
                jSONObject.put("getpost", 1);
            }
            if (this.f == null) {
                jSONObject.put("sort", this.j);
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.l);
            } else {
                jSONObject.put(SpeechConstant.IST_SESSION_ID, this.d);
                jSONObject.put("rid", this.e);
                jSONObject.put("type", this.f);
            }
            if (!TextUtils.isEmpty(this.m)) {
                jSONObject.put("from", this.m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        this.g = 0;
        super.refresh();
        this._offset = 2;
    }

    public boolean hasMore() {
        return this.i == 1;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (!isQueryMore()) {
            this._items.clear();
        }
        if (0 == this.g) {
            optJSONObject = jSONObject.optJSONObject("preview");
            if (optJSONObject != null) {
                this._items.add(0, new Comment(optJSONObject));
            }
        }
        super.handleQuerySuccResult(jSONObject);
        if (0 == this.g) {
            optJSONObject = jSONObject.optJSONObject("post");
            if (optJSONObject != null) {
                this.h = new Post(optJSONObject);
            }
        }
        this.g = jSONObject.optLong("offset");
        this.i = jSONObject.optInt("more");
        this.k = jSONObject.optInt("disable_reply_off");
    }

    public boolean c() {
        return this.k != 1;
    }

    public void b(String str) {
        this.m = str;
    }
}
