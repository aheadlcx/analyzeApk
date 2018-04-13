package com.liulishuo.filedownloader.b;

import android.util.SparseArray;
import com.liulishuo.filedownloader.d.c;
import com.liulishuo.filedownloader.g.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b implements a {
    final SparseArray<c> a = new SparseArray();
    final SparseArray<List<com.liulishuo.filedownloader.d.a>> b = new SparseArray();

    class a implements com.liulishuo.filedownloader.b.a.a {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        public Iterator<c> iterator() {
            return new b(this.a);
        }

        public void a() {
        }

        public void a(c cVar) {
        }

        public void b(c cVar) {
        }

        public void a(int i, c cVar) {
        }
    }

    class b implements Iterator<c> {
        final /* synthetic */ b a;

        public /* synthetic */ Object next() {
            return a();
        }

        b(b bVar) {
            this.a = bVar;
        }

        public boolean hasNext() {
            return false;
        }

        public c a() {
            return null;
        }

        public void remove() {
        }
    }

    public void a(int i) {
    }

    public c b(int i) {
        return (c) this.a.get(i);
    }

    public List<com.liulishuo.filedownloader.d.a> c(int i) {
        List<com.liulishuo.filedownloader.d.a> arrayList = new ArrayList();
        List list = (List) this.b.get(i);
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public void d(int i) {
        this.b.remove(i);
    }

    public void a(com.liulishuo.filedownloader.d.a aVar) {
        int a = aVar.a();
        List list = (List) this.b.get(a);
        if (list == null) {
            list = new ArrayList();
            this.b.put(a, list);
        }
        list.add(aVar);
    }

    public void a(int i, int i2, long j) {
        List<com.liulishuo.filedownloader.d.a> list = (List) this.b.get(i);
        if (list != null) {
            for (com.liulishuo.filedownloader.d.a aVar : list) {
                if (aVar.b() == i2) {
                    aVar.b(j);
                    return;
                }
            }
        }
    }

    public void a(int i, int i2) {
    }

    public void a(c cVar) {
        this.a.put(cVar.a(), cVar);
    }

    public void b(c cVar) {
        if (cVar == null) {
            d.d(this, "update but model == null!", new Object[0]);
        } else if (b(cVar.a()) != null) {
            this.a.remove(cVar.a());
            this.a.put(cVar.a(), cVar);
        } else {
            a(cVar);
        }
    }

    public boolean e(int i) {
        this.a.remove(i);
        return true;
    }

    public void a() {
        this.a.clear();
    }

    public void a(int i, String str, long j, long j2, int i2) {
    }

    public void a(int i, long j, String str, String str2) {
    }

    public void a(int i, long j) {
    }

    public void a(int i, Throwable th, long j) {
    }

    public void a(int i, Throwable th) {
    }

    public void b(int i, long j) {
        e(i);
    }

    public void c(int i, long j) {
    }

    public void f(int i) {
    }

    public com.liulishuo.filedownloader.b.a.a b() {
        return new a(this);
    }
}
