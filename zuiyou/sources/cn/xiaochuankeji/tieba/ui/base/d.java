package cn.xiaochuankeji.tieba.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.homepage.ugc.UgcCrumbManger;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu;
import cn.xiaochuankeji.tieba.ui.widget.SDTopSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.android.a.a.c;
import com.c.a.e;
import com.c.a.g;
import com.umeng.analytics.MobclickAgent;
import org.aspectj.a.b.b;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public abstract class d extends AppCompatActivity {
    private static boolean a = true;
    private static long b;
    private static long c;
    private static long d;
    private static long e;
    private static Thread f;
    private static Object g = new Object();
    private static final org.aspectj.lang.a.a i = null;
    private String h;

    private static class a extends Thread {
        private a() {
        }

        public void run() {
            cn.xiaochuankeji.tieba.network.custom.a.a.a().a(false, true);
            synchronized (d.g) {
                d.f = null;
            }
        }
    }

    private static void b() {
        b bVar = new b("BaseEventActivity.java", d.class);
        i = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.base.BaseEventActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 62);
    }

    static {
        b();
    }

    static final void a(d dVar, Bundle bundle, org.aspectj.lang.a aVar) {
        boolean z = true;
        if (dVar.i()) {
            c.a(dVar.getWindow(), c.a.c.e().d());
        }
        if (VERSION.SDK_INT < 16) {
            dVar.getWindow().clearFlags(16777216);
        }
        super.onCreate(bundle);
        if (dVar.g()) {
            boolean a = g.a(dVar);
            com.c.a.c.c(dVar);
            e a2 = com.c.a.c.b(dVar).a(0.95f).b(1.0f).a(true);
            if (a) {
                z = false;
            }
            a2.c(z);
        }
        dVar.h = c.a.c.e().h();
        org.greenrobot.eventbus.c.a().a(dVar);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(i, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new e(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    public final boolean g() {
        return h() && VERSION.SDK_INT >= 21;
    }

    protected boolean h() {
        return true;
    }

    protected boolean i() {
        return true;
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (g()) {
            com.c.a.c.d(this);
        }
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (a) {
            a = false;
            a();
        }
        String h = c.a.c.e().h();
        if (!h.equalsIgnoreCase(this.h)) {
            this.h = h;
            a(c.a.c.e().c());
        }
    }

    protected void a(boolean z) {
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        cn.xiaochuankeji.tieba.background.j.b.a().i();
        cn.xiaochuankeji.tieba.background.j.a.a().b();
    }

    protected void onStop() {
        super.onStop();
        if (!a) {
            boolean isApplicationInBackground = BaseApplication.isApplicationInBackground();
            boolean i = cn.htjyb.c.a.i(this);
            if (isApplicationInBackground || i) {
                a = true;
                b(isApplicationInBackground);
            }
        }
    }

    protected void onDestroy() {
        if (g()) {
            com.c.a.c.e(this);
        }
        super.onDestroy();
        if (org.greenrobot.eventbus.c.a().b(this)) {
            org.greenrobot.eventbus.c.a().c(this);
        }
    }

    protected boolean l() {
        Activity parent = getParent();
        if (parent instanceof MainActivity) {
            Activity activity = parent;
        }
        if (f.a(activity)) {
            return true;
        }
        if (SDEditSheet.a(activity)) {
            return true;
        }
        if (SDPopupMenu.a(activity)) {
            return true;
        }
        if (SDTopSheet.a(activity)) {
            return true;
        }
        if (SDBottomSheet.a(activity)) {
            return true;
        }
        if (SDGuideDialog.a(activity)) {
            return true;
        }
        if (SDCheckSheet.a(activity)) {
            return true;
        }
        return cn.xiaochuankeji.tieba.ui.widget.b.a(activity);
    }

    public void onBackPressed() {
        if (!l()) {
            Activity parent = getParent();
            if (parent instanceof MainActivity) {
                parent.onBackPressed();
            } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                if (!isFinishing()) {
                    try {
                        getSupportFragmentManager().popBackStack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    super.onBackPressed();
                } catch (Exception e2) {
                }
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (cn.xiaochuankeji.tieba.background.a.a != null) {
            cn.xiaochuankeji.tieba.background.a.a.a(i, i2, intent);
        }
        if (cn.xiaochuankeji.tieba.background.a.b != null) {
            cn.xiaochuankeji.tieba.background.a.b.a(i, i2, intent);
        }
    }

    public void a(f fVar) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, fVar);
        beginTransaction.setTransition(0);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    private void a() {
        long j = 0;
        cn.xiaochuankeji.tieba.background.utils.d.a().b();
        synchronized (g) {
            if (f == null) {
                f = new a();
                f.start();
            }
        }
        b = System.currentTimeMillis();
        d = System.nanoTime() / 1000000;
        if ((c > 0 && e > 0) || cn.xiaochuankeji.tieba.background.h.d.a().j() != null) {
            long j2 = c / 1000;
            long j3 = b / 1000;
            long j4 = (d / 1000) - (e / 1000);
            if (e == 0) {
                j3 = System.currentTimeMillis() / 1000;
                j2 = j3;
            } else {
                j = j4;
            }
            cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
                final /* synthetic */ d d;

                public void run() {
                    cn.xiaochuankeji.tieba.background.utils.monitor.a.a.a().a("background", j2, j3, j);
                    d.c = 0;
                    d.e = 0;
                    cn.xiaochuankeji.tieba.common.a.a.a(BaseApplication.getAppContext());
                }
            });
        }
        UgcCrumbManger.a().b();
    }

    private void b(boolean z) {
        if (z) {
            cn.xiaochuankeji.tieba.background.h.a.b();
        }
        cn.xiaochuankeji.tieba.background.utils.a.g.a().d();
        c = System.currentTimeMillis();
        e = System.nanoTime() / 1000000;
        if (b > 0 && d > 0) {
            final long j = (e / 1000) - (d / 1000);
            cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
                final /* synthetic */ d b;

                public void run() {
                    cn.xiaochuankeji.tieba.background.utils.monitor.a.a.a().a("foreground", d.b / 1000, d.c / 1000, j);
                    d.b = 0;
                    d.d = 0;
                }
            });
        }
    }

    @l(a = ThreadMode.POSTING)
    public void emptyEvent(k kVar) {
    }
}
