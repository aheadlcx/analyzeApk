package master.flame.danmaku.danmaku.model.android;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.a;
import master.flame.danmaku.danmaku.model.l.b;
import master.flame.danmaku.danmaku.model.l.f;

public class e implements l {
    public Collection<d> a;
    private e b;
    private d c;
    private d d;
    private d e;
    private d f;
    private volatile AtomicInteger g;
    private int h;
    private a i;
    private boolean j;
    private Object k;

    public e() {
        this(0, false);
    }

    public e(int i) {
        this(i, false);
    }

    public e(int i, boolean z) {
        this.g = new AtomicInteger(0);
        this.h = 0;
        this.k = new Object();
        Object obj = null;
        if (i == 0) {
            obj = new l.d(z);
        } else if (i == 1) {
            obj = new master.flame.danmaku.danmaku.model.l.e(z);
        } else if (i == 2) {
            obj = new f(z);
        }
        if (i == 4) {
            this.a = new LinkedList();
        } else {
            this.j = z;
            obj.a(z);
            this.a = new TreeSet(obj);
            this.i = obj;
        }
        this.h = i;
        this.g.set(0);
    }

    public e(Collection<d> collection) {
        this.g = new AtomicInteger(0);
        this.h = 0;
        this.k = new Object();
        a((Collection) collection);
    }

    public e(boolean z) {
        this(0, z);
    }

    public void a(Collection<d> collection) {
        if (!this.j || this.h == 4) {
            this.a = collection;
        } else {
            this.a.clear();
            this.a.addAll(collection);
            collection = this.a;
        }
        if (collection instanceof List) {
            this.h = 4;
        }
        this.g.set(collection == null ? 0 : collection.size());
    }

    public boolean a(d dVar) {
        if (this.a != null) {
            try {
                if (this.a.add(dVar)) {
                    this.g.incrementAndGet();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean b(d dVar) {
        if (dVar == null) {
            return false;
        }
        if (dVar.g()) {
            dVar.a(false);
        }
        if (!this.a.remove(dVar)) {
            return false;
        }
        this.g.decrementAndGet();
        return true;
    }

    private Collection<d> c(long j, long j2) {
        if (this.h == 4 || this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            this.b = new e(this.j);
            this.b.k = this.k;
        }
        if (this.f == null) {
            this.f = a("start");
        }
        if (this.e == null) {
            this.e = a("end");
        }
        this.f.d(j);
        this.e.d(j2);
        return ((SortedSet) this.a).subSet(this.f, this.e);
    }

    public l a(long j, long j2) {
        Collection c = c(j, j2);
        if (c == null || c.isEmpty()) {
            return null;
        }
        return new e(new LinkedList(c));
    }

    public l b(long j, long j2) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            if (this.h == 4) {
                this.b = new e(4);
                this.b.k = this.k;
                synchronized (this.k) {
                    this.b.a(this.a);
                }
            } else {
                this.b = new e(this.j);
                this.b.k = this.k;
            }
        }
        if (this.h == 4) {
            return this.b;
        }
        if (this.c == null) {
            this.c = a("start");
        }
        if (this.d == null) {
            this.d = a("end");
        }
        if (this.b != null && j - this.c.s() >= 0 && j2 <= this.d.s()) {
            return this.b;
        }
        this.c.d(j);
        this.d.d(j2);
        synchronized (this.k) {
            this.b.a(((SortedSet) this.a).subSet(this.c, this.d));
        }
        return this.b;
    }

    private d a(String str) {
        return new master.flame.danmaku.danmaku.model.e(str);
    }

    public int a() {
        return this.g.get();
    }

    public void b() {
        if (this.a != null) {
            this.a.clear();
            this.g.set(0);
        }
        if (this.b != null) {
            this.b = null;
            this.c = a("start");
            this.d = a("end");
        }
    }

    public d c() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.h == 4) {
            return (d) ((LinkedList) this.a).peek();
        }
        return (d) ((SortedSet) this.a).first();
    }

    public d d() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.h == 4) {
            return (d) ((LinkedList) this.a).peekLast();
        }
        return (d) ((SortedSet) this.a).last();
    }

    public boolean c(d dVar) {
        return this.a != null && this.a.contains(dVar);
    }

    public boolean e() {
        return this.a == null || this.a.isEmpty();
    }

    public void a(b<? super d, ?> bVar) {
        synchronized (this.k) {
            b((b) bVar);
        }
    }

    public void b(b<? super d, ?> bVar) {
        bVar.c();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar != null) {
                int a = bVar.a(dVar);
                if (a == 1) {
                    break;
                } else if (a == 2) {
                    it.remove();
                    this.g.decrementAndGet();
                } else if (a == 3) {
                    it.remove();
                    this.g.decrementAndGet();
                    break;
                }
            }
        }
        bVar.d();
    }
}
