package cn.xiaochuankeji.tieba.background.splash;

import android.net.Uri;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.a.e;
import cn.xiaochuankeji.tieba.a.f;
import cn.xiaochuankeji.tieba.json.SplashConfigJson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import rx.b.g;
import rx.d;

public class a {
    public static int[] a = new int[]{1, 2, 3};
    public int b;
    public int c;
    public String d;
    public String e;
    public int f;
    public long g;
    public long h;
    public String i;
    private List<String> j;

    @WorkerThread
    public d<e> a() {
        return d.a(Boolean.valueOf(true)).d(new g<Boolean, e>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public e a(Boolean bool) {
                return f.a();
            }
        });
    }

    @WorkerThread
    public List<e> b() {
        List<e> b = f.b();
        this.j = new ArrayList();
        for (e eVar : b) {
            this.j.add(eVar.f);
        }
        return b;
    }

    public boolean c() {
        if (this.c >= 1) {
            return true;
        }
        return false;
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.g = eVar.b;
            this.d = eVar.f;
            this.i = eVar.h;
            this.b = eVar.j;
            this.c = eVar.c;
            this.e = eVar.e;
            this.f = (int) eVar.d;
            this.h = eVar.g;
        }
    }

    public void a(SplashConfigJson splashConfigJson) {
        f.c();
        if (this.j != null) {
            for (String str : this.j) {
                if (b.c(str)) {
                    b.a(str);
                }
            }
        }
        com.izuiyou.a.a.b.b("all splash config removed");
        if (splashConfigJson != null && splashConfigJson.list != null && splashConfigJson.list.size() != 0) {
            com.izuiyou.a.a.b.b("save list:" + splashConfigJson.list.toString());
            for (SplashInfo splashInfo : splashConfigJson.list) {
                if (!TextUtils.isEmpty(splashInfo.url)) {
                    com.izuiyou.a.a.b.b("save splash: url:" + splashInfo.url);
                    com.izuiyou.a.a.b.b("save splash config, Id:" + f.a(splashInfo, splashConfigJson.status, splashConfigJson.version) + ", url:" + splashInfo.url);
                    a(splashInfo);
                }
            }
        }
    }

    private void a(final SplashInfo splashInfo) {
        if (Uri.parse(splashInfo.url) != null) {
            final String str = BaseApplication.getAppContext().getFilesDir().getAbsolutePath() + File.separator + "splash_" + UUID.randomUUID().toString() + ".png";
            if (b.c(str)) {
                f.a(splashInfo.url, str);
                return;
            }
            cn.xiaochuankeji.tieba.background.a.f().a(new cn.htjyb.netlib.a(splashInfo.url, cn.xiaochuankeji.tieba.background.a.d(), str, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ a c;

                public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                    if (dVar.c.a) {
                        com.izuiyou.a.a.b.c("download image sucess:" + str);
                        f.a(splashInfo.url, str);
                    }
                }
            }), false);
        }
    }
}
