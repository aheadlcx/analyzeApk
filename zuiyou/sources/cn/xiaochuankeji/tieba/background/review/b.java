package cn.xiaochuankeji.tieba.background.review;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.review.CommentPublisher.PublishType;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    f a;
    private CommentPublisher b;

    public interface a {
        void a(boolean z, String str, Comment comment, int i);
    }

    public interface b {
        void a(boolean z, long j, String str);
    }

    public void a(long j, String str, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<Long> arrayList, ArrayList<Long> arrayList2, String str2, a aVar2) {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        this.b = new CommentPublisher(PublishType.Post, j);
        this.b.a(str, aVar, arrayList, str2, arrayList2, aVar2);
    }

    public void a(long j, long j2, String str, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<Long> arrayList, String str2, ArrayList<Long> arrayList2, a aVar2) {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        this.b = new CommentPublisher(PublishType.Reply, j, j2);
        this.b.a(str, aVar, arrayList, str2, arrayList2, aVar2);
    }

    public void a(long j, final long j2, final b bVar) {
        if (this.a == null) {
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
                jSONObject.put("rid", j2);
                jSONObject.put("pid", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.a = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/del_review"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ b c;

                public void onTaskFinish(d dVar) {
                    this.c.a = null;
                    if (dVar.c.a) {
                        if (bVar != null) {
                            bVar.a(true, j2, null);
                        }
                    } else if (bVar != null) {
                        bVar.a(false, j2, dVar.c.c());
                    }
                }
            });
            this.a.b();
        }
    }
}
