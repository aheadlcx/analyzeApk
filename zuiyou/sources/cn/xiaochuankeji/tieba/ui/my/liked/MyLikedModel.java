package cn.xiaochuankeji.tieba.ui.my.liked;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.post.d;
import cn.xiaochuankeji.tieba.json.post.LikedListJson;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.LinkedList;
import java.util.List;
import rx.b.b;

public class MyLikedModel extends o {
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
        this.b.b(0).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new b<LikedListJson>(this) {
            final /* synthetic */ MyLikedModel b;

            public /* synthetic */ void call(Object obj) {
                a((LikedListJson) obj);
            }

            public void a(LikedListJson likedListJson) {
                boolean z = true;
                if (likedListJson == null || likedListJson.postDataBeanList.isEmpty()) {
                    aVar.a();
                    return;
                }
                this.b.a.clear();
                this.b.a.addAll(likedListJson.postDataBeanList);
                this.b.c.notifyDataSetChanged();
                a aVar = aVar;
                if (likedListJson.more != 1) {
                    z = false;
                }
                aVar.a(z);
                this.b.d = likedListJson.offset;
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ MyLikedModel b;

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
        this.b.b(this.d).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new b<LikedListJson>(this) {
            final /* synthetic */ MyLikedModel b;

            public /* synthetic */ void call(Object obj) {
                a((LikedListJson) obj);
            }

            public void a(LikedListJson likedListJson) {
                boolean z = true;
                if (likedListJson != null) {
                    this.b.a.addAll(likedListJson.postDataBeanList);
                    this.b.c.notifyDataSetChanged();
                    a aVar = aVar;
                    if (likedListJson.more != 1) {
                        z = false;
                    }
                    aVar.a(z);
                    this.b.d = likedListJson.offset;
                    return;
                }
                aVar.a();
            }
        }, new b<Throwable>(this) {
            final /* synthetic */ MyLikedModel b;

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
