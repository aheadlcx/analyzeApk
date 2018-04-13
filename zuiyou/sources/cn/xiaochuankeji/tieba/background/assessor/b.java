package cn.xiaochuankeji.tieba.background.assessor;

import android.content.SharedPreferences.Editor;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;

public class b {
    private static b a;

    public interface a {
        void a(boolean z, boolean z2, String str);
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private b() {
    }

    public void a(final a aVar) {
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/assessor/check_permission"), cn.xiaochuankeji.tieba.background.a.d(), cn.xiaochuankeji.tieba.background.utils.d.a.b(), new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ b b;

                public void onTaskFinish(d dVar) {
                    boolean z = false;
                    if (dVar.c.a) {
                        int optInt = dVar.c.c.optInt("open");
                        this.b.a(optInt);
                        if (aVar != null) {
                            a aVar = aVar;
                            if (1 == optInt) {
                                z = true;
                            }
                            aVar.a(true, z, null);
                            return;
                        }
                        return;
                    }
                    this.b.a(0);
                    if (aVar != null) {
                        aVar.a(false, false, dVar.c.c());
                    }
                }
            }).b();
        } else if (aVar != null) {
            aVar.a(false, false, null);
        }
    }

    private void a(int i) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.c().edit();
        edit.putInt("key_assessor_open_auth", i);
        edit.apply();
    }

    public boolean b() {
        if (1 == cn.xiaochuankeji.tieba.background.a.c().getInt("key_assessor_open_auth", 0)) {
            return true;
        }
        return false;
    }
}
