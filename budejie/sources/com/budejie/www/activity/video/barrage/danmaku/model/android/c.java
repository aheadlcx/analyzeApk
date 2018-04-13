package com.budejie.www.activity.video.barrage.danmaku.model.android;

import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class c implements k {
    public Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> a;
    private c b;
    private com.budejie.www.activity.video.barrage.danmaku.model.c c;
    private com.budejie.www.activity.video.barrage.danmaku.model.c d;
    private com.budejie.www.activity.video.barrage.danmaku.model.c e;
    private com.budejie.www.activity.video.barrage.danmaku.model.c f;
    private b g;
    private int h;
    private int i;
    private a j;
    private boolean k;

    private class a implements Comparator<com.budejie.www.activity.video.barrage.danmaku.model.c> {
        protected boolean a;
        final /* synthetic */ c b;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.budejie.www.activity.video.barrage.danmaku.model.c) obj, (com.budejie.www.activity.video.barrage.danmaku.model.c) obj2);
        }

        public a(c cVar, boolean z) {
            this.b = cVar;
            a(z);
        }

        public void a(boolean z) {
            this.a = z;
        }

        public int a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2) {
            if (this.a && com.budejie.www.activity.video.barrage.danmaku.c.b.a(cVar, cVar2)) {
                return 0;
            }
            return com.budejie.www.activity.video.barrage.danmaku.c.b.b(cVar, cVar2);
        }
    }

    private class b implements j {
        final /* synthetic */ c a;
        private Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> b;
        private Iterator<com.budejie.www.activity.video.barrage.danmaku.model.c> c;
        private boolean d;

        public b(c cVar, Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> collection) {
            this.a = cVar;
            a(collection);
        }

        public synchronized void d() {
            if (this.d || this.c == null) {
                if (this.b == null || this.a.h <= 0) {
                    this.c = null;
                } else {
                    this.c = this.b.iterator();
                }
            }
        }

        public synchronized void a(Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> collection) {
            if (this.b != collection) {
                this.d = false;
                this.c = null;
            }
            this.b = collection;
        }

        public synchronized com.budejie.www.activity.video.barrage.danmaku.model.c a() {
            this.d = true;
            return this.c != null ? (com.budejie.www.activity.video.barrage.danmaku.model.c) this.c.next() : null;
        }

        public synchronized boolean b() {
            boolean z;
            z = this.c != null && this.c.hasNext();
            return z;
        }

        public synchronized void c() {
            this.d = true;
            if (this.c != null) {
                this.c.remove();
            }
        }
    }

    private class c extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.budejie.www.activity.video.barrage.danmaku.model.c) obj, (com.budejie.www.activity.video.barrage.danmaku.model.c) obj2);
        }

        public c(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2) {
            return super.a(cVar, cVar2);
        }
    }

    private class d extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.budejie.www.activity.video.barrage.danmaku.model.c) obj, (com.budejie.www.activity.video.barrage.danmaku.model.c) obj2);
        }

        public d(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2) {
            if (this.a && com.budejie.www.activity.video.barrage.danmaku.c.b.a(cVar, cVar2)) {
                return 0;
            }
            return Float.compare(cVar.k(), cVar2.k());
        }
    }

    private class e extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.budejie.www.activity.video.barrage.danmaku.model.c) obj, (com.budejie.www.activity.video.barrage.danmaku.model.c) obj2);
        }

        public e(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2) {
            if (this.a && com.budejie.www.activity.video.barrage.danmaku.c.b.a(cVar, cVar2)) {
                return 0;
            }
            return Float.compare(cVar2.k(), cVar.k());
        }
    }

    public c() {
        this(0, false);
    }

    public c(int i) {
        this(i, false);
    }

    public c(int i, boolean z) {
        this.h = 0;
        this.i = 0;
        Object obj = null;
        if (i == 0) {
            obj = new c(this, z);
        } else if (i == 1) {
            obj = new d(this, z);
        } else if (i == 2) {
            obj = new e(this, z);
        }
        if (i == 4) {
            this.a = new ArrayList();
        } else {
            this.k = z;
            obj.a(z);
            this.a = new TreeSet(obj);
            this.j = obj;
        }
        this.i = i;
        this.h = 0;
        this.g = new b(this, this.a);
    }

    public c(Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> collection) {
        this.h = 0;
        this.i = 0;
        a((Collection) collection);
    }

    public c(boolean z) {
        this(0, z);
    }

    public void a(Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> collection) {
        if (!this.k || this.i == 4) {
            this.a = collection;
        } else {
            this.a.clear();
            this.a.addAll(collection);
            collection = this.a;
        }
        if (collection instanceof List) {
            this.i = 4;
        }
        this.h = collection == null ? 0 : collection.size();
        if (this.g == null) {
            this.g = new b(this, collection);
        } else {
            this.g.a(collection);
        }
    }

    public j e() {
        this.g.d();
        return this.g;
    }

    public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.a != null) {
            try {
                if (this.a.add(cVar)) {
                    this.h++;
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (cVar == null) {
            return false;
        }
        if (cVar.f()) {
            cVar.a(false);
        }
        if (!this.a.remove(cVar)) {
            return false;
        }
        this.h--;
        return true;
    }

    private Collection<com.budejie.www.activity.video.barrage.danmaku.model.c> c(long j, long j2) {
        if (this.i == 4 || this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            this.b = new c(this.k);
        }
        if (this.f == null) {
            this.f = a("start");
        }
        if (this.e == null) {
            this.e = a("end");
        }
        this.f.b = j;
        this.e.b = j2;
        return ((SortedSet) this.a).subSet(this.f, this.e);
    }

    public k a(long j, long j2) {
        Collection c = c(j, j2);
        if (c == null || c.isEmpty()) {
            return null;
        }
        return new c(new ArrayList(c));
    }

    public k b(long j, long j2) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            if (this.i == 4) {
                this.b = new c(4);
                this.b.a(this.a);
            } else {
                this.b = new c(this.k);
            }
        }
        if (this.i == 4) {
            return this.b;
        }
        if (this.c == null) {
            this.c = a("start");
        }
        if (this.d == null) {
            this.d = a("end");
        }
        if (this.b != null && j - this.c.b >= 0 && j2 <= this.d.b) {
            return this.b;
        }
        this.c.b = j;
        this.d.b = j2;
        this.b.a(((SortedSet) this.a).subSet(this.c, this.d));
        return this.b;
    }

    private com.budejie.www.activity.video.barrage.danmaku.model.c a(String str) {
        return new com.budejie.www.activity.video.barrage.danmaku.model.d(str);
    }

    public int a() {
        return this.h;
    }

    public void b() {
        if (this.a != null) {
            this.a.clear();
            this.h = 0;
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c c() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.i == 4) {
            return (com.budejie.www.activity.video.barrage.danmaku.model.c) ((ArrayList) this.a).get(0);
        }
        return (com.budejie.www.activity.video.barrage.danmaku.model.c) ((SortedSet) this.a).first();
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c d() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.i == 4) {
            return (com.budejie.www.activity.video.barrage.danmaku.model.c) ((ArrayList) this.a).get(this.a.size() - 1);
        }
        return (com.budejie.www.activity.video.barrage.danmaku.model.c) ((SortedSet) this.a).last();
    }

    public boolean c(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        return this.a != null && this.a.contains(cVar);
    }

    public boolean f() {
        return this.a == null || this.a.isEmpty();
    }
}
