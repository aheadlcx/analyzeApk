package master.flame.danmaku.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;

public class b {
    public final Exception a = new Exception("not suuport this filter tag");
    e<?>[] b = new e[0];
    e<?>[] c = new e[0];
    private final Map<String, e<?>> d = Collections.synchronizedSortedMap(new TreeMap());
    private final Map<String, e<?>> e = Collections.synchronizedSortedMap(new TreeMap());

    public interface e<T> {
        void a();

        void a(T t);

        boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext);
    }

    public static abstract class a<T> implements e<T> {
        public void a() {
        }
    }

    public static class b extends a<Void> {
        protected final master.flame.danmaku.danmaku.model.l a = new master.flame.danmaku.danmaku.model.android.e(4);
        protected final LinkedHashMap<String, master.flame.danmaku.danmaku.model.d> b = new LinkedHashMap();
        private final master.flame.danmaku.danmaku.model.l c = new master.flame.danmaku.danmaku.model.android.e(4);

        private final void a(master.flame.danmaku.danmaku.model.l lVar, final long j) {
            lVar.a(new master.flame.danmaku.danmaku.model.l.c<master.flame.danmaku.danmaku.model.d>(this) {
                long a = master.flame.danmaku.danmaku.c.b.a();
                final /* synthetic */ b c;

                public int a(master.flame.danmaku.danmaku.model.d dVar) {
                    try {
                        if (master.flame.danmaku.danmaku.c.b.a() - this.a <= j && dVar.f()) {
                            return 2;
                        }
                        return 1;
                    } catch (Exception e) {
                        return 1;
                    }
                }
            });
        }

        private void a(LinkedHashMap<String, master.flame.danmaku.danmaku.model.d> linkedHashMap, int i) {
            Iterator it = linkedHashMap.entrySet().iterator();
            long a = master.flame.danmaku.danmaku.c.b.a();
            while (it.hasNext()) {
                try {
                    if (((master.flame.danmaku.danmaku.model.d) ((Entry) it.next()).getValue()).f()) {
                        it.remove();
                        if (master.flame.danmaku.danmaku.c.b.a() - a > ((long) i)) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
        }

        public synchronized boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z) {
            boolean z2 = true;
            synchronized (this) {
                a(this.a, 2);
                a(this.c, 2);
                a(this.b, 3);
                if (!this.a.c(dVar) || dVar.g()) {
                    if (this.c.c(dVar)) {
                        z2 = false;
                    } else if (this.b.containsKey(dVar.c)) {
                        this.b.put(String.valueOf(dVar.c), dVar);
                        this.a.b(dVar);
                        this.a.a(dVar);
                    } else {
                        this.b.put(String.valueOf(dVar.c), dVar);
                        this.c.a(dVar);
                        z2 = false;
                    }
                }
            }
            return z2;
        }

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(dVar, i, i2, fVar, z);
            if (a) {
                dVar.F |= 128;
            }
            return a;
        }

        public void a(Void voidR) {
        }

        public synchronized void b() {
            this.c.b();
            this.a.b();
            this.b.clear();
        }

        public void a() {
            b();
        }
    }

    public static class c extends a<Object> {
        long a = 20;

        private synchronized boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z) {
            boolean z2 = false;
            synchronized (this) {
                if (fVar != null) {
                    if (dVar.g()) {
                        if (master.flame.danmaku.danmaku.c.b.a() - fVar.a >= this.a) {
                            z2 = true;
                        }
                    }
                }
            }
            return z2;
        }

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(dVar, i, i2, fVar, z);
            if (a) {
                dVar.F |= 4;
            }
            return a;
        }

        public void a(Object obj) {
            b();
        }

        public synchronized void b() {
        }

        public void a() {
            b();
        }
    }

    public static class d extends a<Boolean> {
        private Boolean a = Boolean.valueOf(false);

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = this.a.booleanValue() && dVar.C;
            if (z2) {
                dVar.F |= 64;
            }
            return z2;
        }

        public void a(Boolean bool) {
            this.a = bool;
        }
    }

    public static class f extends a<Map<Integer, Integer>> {
        private Map<Integer, Integer> a;

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a == null) {
                return false;
            }
            Integer num = (Integer) this.a.get(Integer.valueOf(dVar.o()));
            boolean z2 = num != null && i >= num.intValue();
            if (!z2) {
                return z2;
            }
            dVar.F |= 256;
            return z2;
        }

        public void a(Map<Integer, Integer> map) {
            this.a = map;
        }
    }

    public static class g extends a<Map<Integer, Boolean>> {
        private Map<Integer, Boolean> a;

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a == null) {
                return false;
            }
            Boolean bool = (Boolean) this.a.get(Integer.valueOf(dVar.o()));
            boolean z2 = bool != null && bool.booleanValue() && z;
            if (!z2) {
                return z2;
            }
            dVar.F |= 512;
            return z2;
        }

        public void a(Map<Integer, Boolean> map) {
            this.a = map;
        }
    }

    public static class h extends a<Integer> {
        protected int a = -1;
        protected master.flame.danmaku.danmaku.model.d b = null;
        private float c = 1.0f;

        private boolean b(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a <= 0 || dVar.o() != 1) {
                return false;
            }
            if (this.b == null || this.b.f()) {
                this.b = dVar;
                return false;
            }
            long s = dVar.s() - this.b.s();
            master.flame.danmaku.danmaku.model.g gVar = danmakuContext.v.e;
            if ((s >= 0 && gVar != null && ((float) s) < ((float) gVar.a) * this.c) || i > this.a) {
                return true;
            }
            this.b = dVar;
            return false;
        }

        public synchronized boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean b;
            b = b(dVar, i, i2, fVar, z, danmakuContext);
            if (b) {
                dVar.F |= 2;
            }
            return b;
        }

        public void a(Integer num) {
            b();
            if (num != null && num.intValue() != this.a) {
                this.a = num.intValue() + (num.intValue() / 5);
                this.c = 1.0f / ((float) this.a);
            }
        }

        public synchronized void b() {
            this.b = null;
        }

        public void a() {
            b();
        }
    }

    public static class i extends a<List<Integer>> {
        public List<Integer> a = new ArrayList();

        private void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = (dVar == null || this.a.contains(Integer.valueOf(dVar.g))) ? false : true;
            if (z2) {
                dVar.F |= 8;
            }
            return z2;
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    public static class j extends a<List<Integer>> {
        final List<Integer> a = Collections.synchronizedList(new ArrayList());

        public void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = dVar != null && this.a.contains(Integer.valueOf(dVar.o()));
            if (z2) {
                dVar.F |= 1;
            }
            return z2;
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    public static abstract class k<T> extends a<List<T>> {
        public List<T> a = new ArrayList();

        private void b(T t) {
            if (!this.a.contains(t)) {
                this.a.add(t);
            }
        }

        public void a(List<T> list) {
            b();
            if (list != null) {
                for (T b : list) {
                    b(b);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    public static class l extends k<String> {
        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = dVar != null && this.a.contains(dVar.B);
            if (z2) {
                dVar.F |= 32;
            }
            return z2;
        }
    }

    public static class m extends k<Integer> {
        public boolean a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = dVar != null && this.a.contains(Integer.valueOf(dVar.A));
            if (z2) {
                dVar.F |= 16;
            }
            return z2;
        }
    }

    public void a(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar : this.b) {
            if (eVar != null) {
                boolean a = eVar.a(dVar, i, i2, fVar, z, danmakuContext);
                dVar.G = danmakuContext.t.c;
                if (a) {
                    return;
                }
            }
        }
    }

    public boolean b(master.flame.danmaku.danmaku.model.d dVar, int i, int i2, master.flame.danmaku.danmaku.model.f fVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar : this.c) {
            if (eVar != null) {
                boolean a = eVar.a(dVar, i, i2, fVar, z, danmakuContext);
                dVar.G = danmakuContext.t.c;
                if (a) {
                    return true;
                }
            }
        }
        return false;
    }

    public e<?> a(String str, boolean z) {
        e<?> eVar = z ? (e) this.d.get(str) : (e) this.e.get(str);
        if (eVar == null) {
            return b(str, z);
        }
        return eVar;
    }

    public e<?> a(String str) {
        return b(str, true);
    }

    public e<?> b(String str, boolean z) {
        if (str == null) {
            b();
            return null;
        }
        e<?> jVar;
        e<?> eVar = (e) this.d.get(str);
        if (eVar == null) {
            if ("1010_Filter".equals(str)) {
                jVar = new j();
            } else if ("1011_Filter".equals(str)) {
                r2 = new h();
            } else if ("1012_Filter".equals(str)) {
                r2 = new c();
            } else if ("1013_Filter".equals(str)) {
                r2 = new i();
            } else if ("1014_Filter".equals(str)) {
                r2 = new m();
            } else if ("1015_Filter".equals(str)) {
                r2 = new l();
            } else if ("1016_Filter".equals(str)) {
                r2 = new d();
            } else if ("1017_Filter".equals(str)) {
                r2 = new b();
            } else if ("1018_Filter".equals(str)) {
                r2 = new f();
            } else if ("1019_Filter".equals(str)) {
                r2 = new g();
            }
            if (jVar != null) {
                b();
                return null;
            }
            jVar.a(null);
            if (z) {
                this.e.put(str, jVar);
                this.c = (e[]) this.e.values().toArray(this.c);
            } else {
                this.d.put(str, jVar);
                this.b = (e[]) this.d.values().toArray(this.b);
            }
            return jVar;
        }
        jVar = eVar;
        if (jVar != null) {
            jVar.a(null);
            if (z) {
                this.e.put(str, jVar);
                this.c = (e[]) this.e.values().toArray(this.c);
            } else {
                this.d.put(str, jVar);
                this.b = (e[]) this.d.values().toArray(this.b);
            }
            return jVar;
        }
        b();
        return null;
    }

    public void b(String str) {
        c(str, true);
    }

    public void c(String str, boolean z) {
        e eVar = z ? (e) this.d.remove(str) : (e) this.e.remove(str);
        if (eVar != null) {
            eVar.a();
            if (z) {
                this.b = (e[]) this.d.values().toArray(this.b);
            } else {
                this.c = (e[]) this.e.values().toArray(this.c);
            }
        }
    }

    public void a() {
        int i = 0;
        for (e eVar : this.b) {
            if (eVar != null) {
                eVar.a();
            }
        }
        e[] eVarArr = this.c;
        int length = eVarArr.length;
        while (i < length) {
            e eVar2 = eVarArr[i];
            if (eVar2 != null) {
                eVar2.a();
            }
            i++;
        }
    }

    private void b() {
        try {
            throw this.a;
        } catch (Exception e) {
        }
    }
}
