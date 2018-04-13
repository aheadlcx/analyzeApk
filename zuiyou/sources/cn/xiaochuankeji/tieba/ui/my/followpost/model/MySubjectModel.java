package cn.xiaochuankeji.tieba.ui.my.followpost.model;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.tale.a;
import cn.xiaochuankeji.tieba.json.tale.ThemeListJson;
import cn.xiaochuankeji.tieba.ui.my.followpost.c;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import rx.j;

public class MySubjectModel extends o {
    a a = new a();
    private c b;
    private b c;
    private String d;

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void b() {
        this.a.a(20, "").a(rx.a.b.a.a()).b(new j<ThemeListJson>(this) {
            final /* synthetic */ MySubjectModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ThemeListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", 0, true);
            }

            public void a(ThemeListJson themeListJson) {
                this.a.d = themeListJson.cursor;
                if (this.a.c != null) {
                    this.a.c.a(true, "", themeListJson.themes.size(), themeListJson.more);
                }
                this.a.b.a(themeListJson.themes);
            }
        });
    }

    public void c() {
        this.a.a(20, this.d).a(rx.a.b.a.a()).b(new j<ThemeListJson>(this) {
            final /* synthetic */ MySubjectModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ThemeListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", true);
            }

            public void a(ThemeListJson themeListJson) {
                this.a.d = themeListJson.cursor;
                if (this.a.c != null) {
                    this.a.c.a(true, "", themeListJson.more);
                }
                this.a.b.b(themeListJson.themes);
            }
        });
    }
}
