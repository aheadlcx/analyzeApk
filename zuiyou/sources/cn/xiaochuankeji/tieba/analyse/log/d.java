package cn.xiaochuankeji.tieba.analyse.log;

import android.content.Context;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.api.log.b;
import com.izuiyou.a.a.a.a;
import rx.b.g;
import rx.e;

public class d implements a {
    private static final String a = d.class.getSimpleName();
    private LogCommandModel b;

    public d(LogCommandModel logCommandModel) {
        this.b = logCommandModel;
    }

    public void a() {
        rx.d.a(BaseApplication.getAppContext()).d(new g<Context, String>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Context) obj);
            }

            public String a(Context context) {
                return a.a(context).toJSONString();
            }
        }).c(new g<String, rx.d<String>>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((String) obj);
            }

            public rx.d<String> a(String str) {
                return b.a(str, this.a.b.opid);
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).a(new e<String>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.a.d(d.a, th);
            }

            public void a(String str) {
                if (TextUtils.isEmpty(str)) {
                    com.izuiyou.a.a.a.c(d.a, "upload success ");
                } else {
                    com.izuiyou.a.a.a.c(d.a, "upload success " + str);
                }
            }
        });
    }
}
