package cn.xiaochuankeji.tieba.ui.my.mypost;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.post.d;
import cn.xiaochuankeji.tieba.json.post.PostListJson;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.LinkedList;
import java.util.List;
import rx.b.b;

public class MyPostModel extends o {
    private List<c> a = new LinkedList();
    private d b = new d();
    private b c;
    private long d;

    interface a {
        void a();

        void a(boolean z);
    }

    void a(b bVar) {
        bVar.a(this.a);
        this.c = bVar;
    }

    void a(final a aVar) {
        this.b.a(0).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new b<PostListJson>(this) {
            final /* synthetic */ MyPostModel b;

            public /* synthetic */ void call(Object obj) {
                a((PostListJson) obj);
            }

            public void a(PostListJson postListJson) {
                boolean z = true;
                if (postListJson == null || postListJson.postDataBeanList.isEmpty()) {
                    aVar.a();
                    return;
                }
                this.b.a.clear();
                this.b.a.addAll(postListJson.postDataBeanList);
                this.b.c.notifyDataSetChanged();
                a aVar = aVar;
                if (postListJson.more != 1) {
                    z = false;
                }
                aVar.a(z);
                this.b.d = postListJson.time;
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ MyPostModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    void b(final a aVar) {
        this.b.a(this.d).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new b<PostListJson>(this) {
            final /* synthetic */ MyPostModel b;

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
            final /* synthetic */ MyPostModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }
}
