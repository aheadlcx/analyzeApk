package cn.xiaochuankeji.tieba.ui.base;

import android.os.Bundle;
import cn.xiaochuankeji.aop.permission.c;
import org.aspectj.a.b.b;

public abstract class a extends d {
    private static final org.aspectj.lang.a.a a = null;

    static {
        e();
    }

    private static void e() {
        b bVar = new b("BaseActivity.java", a.class);
        a = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.base.BaseActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 15);
    }

    protected abstract int a();

    protected abstract void i_();

    static final void a(a aVar, Bundle bundle, org.aspectj.lang.a aVar2) {
        super.onCreate(bundle);
        if (aVar.a() != 0) {
            aVar.setContentView(aVar.a());
        }
        if (aVar.a(bundle)) {
            aVar.f();
            aVar.c();
            aVar.i_();
            aVar.j_();
            return;
        }
        aVar.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(a, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void f() {
    }

    protected boolean a(Bundle bundle) {
        return true;
    }

    protected void c() {
    }

    protected void j_() {
    }

    protected void onResume() {
        super.onResume();
    }

    protected void a(boolean z) {
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
