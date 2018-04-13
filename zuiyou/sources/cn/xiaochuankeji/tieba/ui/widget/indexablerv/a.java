package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.c;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.d;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.e;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class a<T> {
    ArrayList<b<T>> a = new ArrayList();
    protected a<T> b;
    protected b<T> c;
    private final c d = new c();
    private final e e = new e();
    private String f;
    private String g;

    interface a<T> {
        void a(View view, int i, T t);
    }

    interface b<T> {
        boolean a(View view, int i, T t);
    }

    public abstract int a();

    public abstract ViewHolder a(ViewGroup viewGroup);

    public abstract void a(ViewHolder viewHolder, T t);

    public a(String str, String str2, List<T> list) {
        this.f = str;
        this.g = str2;
        if (str2 != null) {
            f().b(2147483646);
        }
        for (int i = 0; i < list.size(); i++) {
            f().a(list.get(i));
        }
    }

    private b<T> f() {
        b<T> bVar = new b();
        bVar.a(this.f);
        bVar.b(this.g);
        bVar.c(b());
        this.a.add(bVar);
        return bVar;
    }

    int b() {
        return 1;
    }

    a<T> c() {
        return this.b;
    }

    b d() {
        return this.c;
    }

    ArrayList<b<T>> e() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.g() == Integer.MAX_VALUE) {
                bVar.b(a());
            }
        }
        return this.a;
    }

    void a(d dVar) {
        this.d.registerObserver(dVar);
    }

    void a(f fVar) {
        this.e.registerObserver(fVar);
    }
}
