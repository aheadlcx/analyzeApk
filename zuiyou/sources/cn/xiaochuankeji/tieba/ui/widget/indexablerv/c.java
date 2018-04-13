package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class c<T extends f> {
    private final cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.a a = new cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.a();
    private List<T> b;
    private a<T> c;
    private d d;
    private b e;
    private e f;
    private c g;

    public interface b<T> {
        void a(View view, int i, int i2, T t);
    }

    public interface a<T> {
        void a(List<b<T>> list);
    }

    public interface c<T> {
        boolean a(View view, int i, int i2, T t);
    }

    public interface d {
        void a(View view, int i, String str);
    }

    public interface e {
        boolean a(View view, int i, String str);
    }

    public abstract ViewHolder a(ViewGroup viewGroup);

    public abstract void a(ViewHolder viewHolder, T t);

    public abstract void a(ViewHolder viewHolder, String str);

    public abstract ViewHolder b(ViewGroup viewGroup);

    public void a(List<T> list, a<T> aVar) {
        this.c = aVar;
        this.b = list;
        g();
    }

    public void a(b<T> bVar) {
        this.e = bVar;
        a(2);
    }

    private void g() {
        this.a.a();
    }

    private void a(int i) {
        this.a.a(i);
    }

    public List<T> a() {
        return this.b;
    }

    a<T> b() {
        return this.c;
    }

    d c() {
        return this.d;
    }

    e d() {
        return this.f;
    }

    b e() {
        return this.e;
    }

    c f() {
        return this.g;
    }

    void a(cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.b bVar) {
        this.a.registerObserver(bVar);
    }

    void b(cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.b bVar) {
        this.a.unregisterObserver(bVar);
    }
}
