package c.a.a;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import c.a.g.b;
import c.a.i.b.e;
import c.a.i.x;
import cn.xiaochuankeji.aop.permission.c;
import org.aspectj.lang.a.a;

public class d extends TabActivity implements b {
    private static final a b = null;
    private c a;

    static {
        a();
    }

    private static void a() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("SkinCompatTabActivity.java", d.class);
        b = bVar.a("method-execution", bVar.a("4", "onCreate", "skin.support.app.SkinCompatTabActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 25);
    }

    static final void a(d dVar, Bundle bundle, org.aspectj.lang.a aVar) {
        LayoutInflaterCompat.setFactory(dVar.getLayoutInflater(), dVar.d());
        super.onCreate(bundle);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(b, this, this, bundle);
        c.c().a(new e(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    @NonNull
    public c d() {
        if (this.a == null) {
            this.a = c.a((Context) this);
        }
        return this.a;
    }

    protected void onResume() {
        super.onResume();
        c.a.c.e().a((b) this);
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a.c.e().b(this);
    }

    protected boolean e() {
        return true;
    }

    protected void f() {
        if (e() && VERSION.SDK_INT >= 21) {
            int d = x.d(this);
            int b = x.b(this);
            if (e.b(d) != 0) {
                getWindow().setStatusBarColor(c.a.d.a.a.a().a(d));
            } else if (e.b(b) != 0) {
                getWindow().setStatusBarColor(c.a.d.a.a.a().a(b));
            }
        }
    }

    protected void g() {
        int e = x.e(this);
        if (e.b(e) != 0) {
            Drawable b = c.a.d.a.a.a().b(e);
            if (b != null) {
                getWindow().setBackgroundDrawable(b);
            }
        }
    }

    public void a(c.a.g.a aVar, Object obj) {
        f();
        g();
        d().a();
    }
}
