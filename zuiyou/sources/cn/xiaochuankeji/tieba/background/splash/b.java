package cn.xiaochuankeji.tieba.background.splash;

import android.content.Context;
import cn.xiaochuankeji.tieba.a.e;
import cn.xiaochuankeji.tieba.api.config.a;
import cn.xiaochuankeji.tieba.json.SplashConfigJson;
import rx.b.g;
import rx.d;
import rx.j;

public class b {
    private final a a = new a();
    private boolean b;
    private boolean c;
    private int d = 0;

    public void a(final Context context) {
        com.izuiyou.a.a.b.c("check new splash");
        if (!this.b && !this.c) {
            final a aVar = new a();
            this.a.a().b(rx.f.a.c()).a(rx.f.a.c()).c(new g<e, d<SplashConfigJson>>(this) {
                final /* synthetic */ b c;

                public /* synthetic */ Object call(Object obj) {
                    return a((e) obj);
                }

                public d<SplashConfigJson> a(e eVar) {
                    if (eVar != null) {
                        this.c.d = eVar.j;
                    }
                    com.izuiyou.a.a.b.e("loadLatest splash config finished, version:" + this.c.d);
                    return aVar.a(this.c.b(context), this.c.d);
                }
            }).a(rx.f.a.c()).b(new j<SplashConfigJson>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((SplashConfigJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                }

                public void a(SplashConfigJson splashConfigJson) {
                    if (splashConfigJson.version > this.a.d) {
                        com.izuiyou.a.a.b.c("save splash");
                        this.a.a.a(splashConfigJson);
                    }
                    this.a.b = false;
                    this.a.c = true;
                }
            });
        }
    }

    private String b(Context context) {
        float f = cn.htjyb.c.a.f(context);
        if (f <= 1.5f) {
            return "small";
        }
        if (f <= 2.0f) {
            return "middle";
        }
        return "big";
    }
}
