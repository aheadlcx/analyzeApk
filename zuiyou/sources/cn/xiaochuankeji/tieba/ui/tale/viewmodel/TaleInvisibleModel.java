package cn.xiaochuankeji.tieba.ui.tale.viewmodel;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.json.tale.TaleListJson;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.tale.TaleListAdapter;
import com.alibaba.fastjson.JSONArray;
import rx.j;

public class TaleInvisibleModel extends o {
    cn.xiaochuankeji.tieba.api.tale.a a = new cn.xiaochuankeji.tieba.api.tale.a();
    private TaleListAdapter b;
    private b c;
    private a d;
    private String e;

    public interface a {
        void a(FollowPostThemeJson followPostThemeJson);
    }

    public void a(b bVar, a aVar) {
        this.c = bVar;
        this.d = aVar;
    }

    public void a(TaleListAdapter taleListAdapter) {
        this.b = taleListAdapter;
    }

    public void a(String str, long j, JSONArray jSONArray, int i) {
        this.a.a(str, j, 20, "", jSONArray, i).a(rx.a.b.a.a()).b(new j<TaleListJson>(this) {
            final /* synthetic */ TaleInvisibleModel a;

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
                this.a.e = taleListJson.cursor;
                if (this.a.c != null) {
                    this.a.c.a(true, "", 0, taleListJson.more);
                }
                if (this.a.d != null) {
                    this.a.d.a(taleListJson.theme);
                }
                this.a.b.b(taleListJson.list);
            }
        });
    }

    public void a(String str, long j, int i) {
        this.a.a("index", j, 20, this.e, null, i).a(rx.a.b.a.a()).b(new j<TaleListJson>(this) {
            final /* synthetic */ TaleInvisibleModel a;

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
                if (this.a.c != null) {
                    this.a.c.a(true, "", taleListJson.more);
                }
                this.a.e = taleListJson.cursor;
                this.a.b.a(taleListJson.list);
            }
        });
    }
}
