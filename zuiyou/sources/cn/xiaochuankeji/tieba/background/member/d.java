package cn.xiaochuankeji.tieba.background.member;

import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.m;
import java.util.ArrayList;
import org.json.JSONObject;
import rx.b.g;
import rx.e;

public class d extends m {
    private static d a;
    private b b;
    private a c;

    public interface a {
        void a(long j);
    }

    public interface b {
        void b();

        void h_();
    }

    private d() {
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public void a(final Post post) {
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                cn.xiaochuankeji.tieba.a.d.a(post);
            }
        });
    }

    public void b() {
        rx.d.a(Boolean.valueOf(true)).d(new g<Boolean, Long>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public Long a(Boolean bool) {
                return Long.valueOf(cn.xiaochuankeji.tieba.a.d.a());
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Long>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Long) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Long l) {
                if (this.a.c != null) {
                    this.a.c.a(l.longValue());
                }
            }
        });
    }

    public void c() {
        cn.xiaochuankeji.tieba.a.d.a(new rx.b.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void call() {
                this.a._items.clear();
                if (this.a.b != null) {
                    this.a.b.b();
                }
            }
        });
    }

    public void d() {
        rx.d.a(Boolean.valueOf(true)).d(new g<Boolean, ArrayList<Post>>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public ArrayList<Post> a(Boolean bool) {
                return cn.xiaochuankeji.tieba.a.d.b();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<ArrayList<Post>>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ArrayList) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(ArrayList<Post> arrayList) {
                this.a._items.clear();
                this.a._items.addAll(arrayList);
                if (this.a.b != null) {
                    this.a.b.h_();
                }
            }
        });
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
    }

    protected String getQueryUrl() {
        return null;
    }

    public boolean hasMore() {
        return false;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void e() {
        this.b = null;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void f() {
        this.c = null;
    }
}
