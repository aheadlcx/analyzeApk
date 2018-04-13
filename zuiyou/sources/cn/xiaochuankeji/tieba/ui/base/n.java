package cn.xiaochuankeji.tieba.ui.base;

import android.os.Bundle;
import android.widget.FrameLayout;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public abstract class n extends h {
    private static final a g = null;
    protected QueryListView d;
    protected FrameLayout e;
    protected NavigationBar f;

    static {
        x();
    }

    private static void x() {
        b bVar = new b("QueryListViewContainerActivity.java", n.class);
        g = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.base.QueryListViewContainerActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 17);
    }

    protected abstract void j();

    protected abstract QueryListView k();

    protected abstract String v();

    static final void a(n nVar, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        nVar.j();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(g, this, this, bundle);
        c.c().a(new o(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_query_listview_container;
    }

    protected void c() {
        this.e = (FrameLayout) findViewById(R.id.frameContainer);
        this.f = (NavigationBar) findViewById(R.id.navBar);
        this.d = k();
        this.e.addView(this.d);
    }

    protected void i_() {
        this.f.setTitle(v());
        w();
    }

    protected void w() {
    }
}
