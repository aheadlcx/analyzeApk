package cn.xiaochuankeji.tieba.background.review;

import cn.htjyb.netlib.d;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.review.b.a;
import cn.xiaochuankeji.tieba.background.upload.g.c;
import cn.xiaochuankeji.tieba.background.utils.f;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentPublisher {
    private String a;
    private long b;
    private long c;
    private a d;
    private PublishType e;
    private d f;
    private final ArrayList<c> g;
    private ArrayList<Long> h;
    private ArrayList<Long> i;
    private cn.xiaochuankeji.tieba.background.post.a j;
    private f k;
    private String l;

    public enum PublishType {
        Post,
        Reply
    }

    public CommentPublisher(PublishType publishType, long j) {
        this(publishType, j, 0);
    }

    public CommentPublisher(PublishType publishType, long j, long j2) {
        this.g = new ArrayList();
        this.e = publishType;
        this.b = j;
        this.c = j2;
    }

    public void a(String str, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<Long> arrayList, String str2, ArrayList<Long> arrayList2, a aVar2) {
        if (aVar2 == null) {
            b.e("参数错误");
            return;
        }
        this.a = str;
        if (arrayList != null) {
            this.h = (ArrayList) arrayList.clone();
        }
        if (arrayList2 != null) {
            this.i = (ArrayList) arrayList2.clone();
        }
        this.j = aVar;
        this.d = aVar2;
        this.l = str2;
        a();
        b();
    }

    public void a() {
        if (this.k != null) {
            this.k.b();
            this.k = null;
        }
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
    }

    private void b() {
        if (this.j == null) {
            c();
            return;
        }
        this.k = new f(this.j.a, this.j.b, new f.a(this) {
            final /* synthetic */ CommentPublisher a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str, String str2) {
                if (z) {
                    this.a.j.c = str;
                    this.a.c();
                } else {
                    this.a.d.a(false, str2, null, 0);
                }
                this.a.k = null;
            }
        });
        this.k.a();
    }

    private void c() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            JSONArray jSONArray;
            Iterator it;
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            jSONObject.put("localid", this.l);
            jSONObject.put("pid", this.b);
            if (this.e == PublishType.Reply) {
                jSONObject.put("rid", this.c);
            }
            if (this.a != null) {
                jSONObject.put("review", this.a);
            }
            if (this.j != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("uri", this.j.c);
                jSONObject2.put("dur", this.j.d);
                jSONObject.put("audio", jSONObject2);
            }
            if (this.h != null && this.h.size() > 0) {
                jSONArray = new JSONArray();
                it = this.h.iterator();
                while (it.hasNext()) {
                    jSONArray.put((Long) it.next());
                }
                jSONObject.put("imgs", jSONArray);
            }
            if (this.i != null && this.i.size() > 0) {
                jSONArray = new JSONArray();
                it = this.i.iterator();
                while (it.hasNext()) {
                    jSONArray.put((Long) it.next());
                }
                jSONObject.put("videos", jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = null;
        if (this.e == PublishType.Post) {
            str = cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/post_review");
        } else if (this.e == PublishType.Reply) {
            b.e("调用 回复 评论");
            str = cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/reply_review");
        }
        this.f = new cn.htjyb.netlib.f(str, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new d.a(this) {
            final /* synthetic */ CommentPublisher a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                this.a.f = null;
                if (dVar.c.a) {
                    JSONObject optJSONObject = dVar.c.c.optJSONObject("review");
                    int optInt = dVar.c.c.optInt("reviews");
                    this.a.d.a(true, null, new Comment(optJSONObject), optInt);
                    return;
                }
                b.e("评论发布失败!!");
                this.a.d.a(false, dVar.c.c(), null, 0);
            }
        }).b();
    }
}
