package cn.xiaochuankeji.tieba.ui.member.model;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.tale.a;
import cn.xiaochuankeji.tieba.json.tale.TaleListJson;
import cn.xiaochuankeji.tieba.ui.my.followpost.MyFollowPostAdapter;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import rx.j;

public class MemberFollowPostModel extends o {
    a a = new a();
    private MyFollowPostAdapter b;
    private b c;
    private String d;

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(MyFollowPostAdapter myFollowPostAdapter) {
        this.b = myFollowPostAdapter;
    }

    public void a(long j) {
        this.a.a(j, 20, "").a(rx.a.b.a.a()).b(new j<TaleListJson>(this) {
            final /* synthetic */ MemberFollowPostModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TaleListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", 0, true);
            }

            public void a(TaleListJson taleListJson) {
                this.a.d = taleListJson.cursor;
                if (this.a.c != null) {
                    this.a.c.a(true, "", taleListJson.list.size(), taleListJson.more);
                }
                this.a.b.b(taleListJson.list);
            }
        });
    }

    public void b(long j) {
        this.a.a(j, 20, this.d).a(rx.a.b.a.a()).b(new j<TaleListJson>(this) {
            final /* synthetic */ MemberFollowPostModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TaleListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", true);
            }

            public void a(TaleListJson taleListJson) {
                this.a.d = taleListJson.cursor;
                if (this.a.c != null) {
                    this.a.c.a(true, "", taleListJson.more);
                }
                this.a.b.a(taleListJson.list);
            }
        });
    }
}
