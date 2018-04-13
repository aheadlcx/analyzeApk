package cn.xiaochuankeji.tieba.ui.member.userpost;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.post.d;
import cn.xiaochuankeji.tieba.json.post.PostListJson;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.LinkedList;
import java.util.List;
import rx.b.b;

public class UserPostModel extends o {
    private List<c> a = new LinkedList();
    private d b = new d();
    private a c;
    private long d;

    interface a {
        void a();

        void a(boolean z);
    }

    void a(a aVar) {
        aVar.a(this.a);
        this.c = aVar;
    }

    void a(List<c> list, long j) {
        if (list != null) {
            this.a.clear();
            this.a.addAll(list);
            this.c.notifyDataSetChanged();
            this.d = j;
        }
    }

    void a(long j, final a aVar) {
        this.b.a(j, this.d).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new b<PostListJson>(this) {
            final /* synthetic */ UserPostModel b;

            public /* synthetic */ void call(Object obj) {
                a((PostListJson) obj);
            }

            public void a(PostListJson postListJson) {
                boolean z = true;
                if (postListJson != null) {
                    this.b.a.addAll(postListJson.postDataBeanList);
                    this.b.c.notifyDataSetChanged();
                    a aVar = aVar;
                    if (postListJson.more != 1) {
                        z = false;
                    }
                    aVar.a(z);
                    this.b.d = postListJson.time;
                    return;
                }
                aVar.a();
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ UserPostModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
            }
        });
    }
}
