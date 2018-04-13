package com.budejie.www.activity.video.barrage.a;

import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class b {
    public final Exception a = new Exception("not suuport this filter tag");
    e<?>[] b = new e[0];
    e<?>[] c = new e[0];
    private final Map<String, e<?>> d = Collections.synchronizedSortedMap(new TreeMap());
    private final Map<String, e<?>> e = Collections.synchronizedSortedMap(new TreeMap());

    public interface e<T> {
        void a();

        void a(T t);

        boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext);
    }

    public static abstract class a<T> implements e<T> {
        public void a() {
        }
    }

    public static class b extends a<Void> {
        protected final com.budejie.www.activity.video.barrage.danmaku.model.k a = new com.budejie.www.activity.video.barrage.danmaku.model.android.c(4);
        protected final LinkedHashMap<String, com.budejie.www.activity.video.barrage.danmaku.model.c> b = new LinkedHashMap();
        private final com.budejie.www.activity.video.barrage.danmaku.model.k c = new com.budejie.www.activity.video.barrage.danmaku.model.android.c(4);

        private final void a(com.budejie.www.activity.video.barrage.danmaku.model.k kVar, long j) {
            com.budejie.www.activity.video.barrage.danmaku.model.j e = kVar.e();
            long currentTimeMillis = System.currentTimeMillis();
            while (e.b()) {
                try {
                    if (e.a().e()) {
                        e.c();
                        if (System.currentTimeMillis() - currentTimeMillis > j) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e2) {
                    return;
                }
            }
        }

        private void a(LinkedHashMap<String, com.budejie.www.activity.video.barrage.danmaku.model.c> linkedHashMap, int i) {
            Iterator it = linkedHashMap.entrySet().iterator();
            long currentTimeMillis = System.currentTimeMillis();
            while (it.hasNext()) {
                try {
                    if (((com.budejie.www.activity.video.barrage.danmaku.model.c) ((Entry) it.next()).getValue()).e()) {
                        it.remove();
                        if (System.currentTimeMillis() - currentTimeMillis > ((long) i)) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
        }

        public synchronized boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z) {
            boolean z2 = true;
            synchronized (this) {
                a(this.a, 2);
                a(this.c, 2);
                a(this.b, 3);
                if (!this.a.c(cVar) || cVar.f()) {
                    if (this.c.c(cVar)) {
                        z2 = false;
                    } else if (this.b.containsKey(cVar.c)) {
                        this.b.put(String.valueOf(cVar.c), cVar);
                        this.a.b(cVar);
                        this.a.a(cVar);
                    } else {
                        this.b.put(String.valueOf(cVar.c), cVar);
                        this.c.a(cVar);
                        z2 = false;
                    }
                }
            }
            return z2;
        }

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(cVar, i, i2, eVar, z);
            if (a) {
                cVar.z |= 128;
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

        private synchronized boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z) {
            boolean z2 = false;
            synchronized (this) {
                if (eVar != null) {
                    if (cVar.f()) {
                        if (System.currentTimeMillis() - eVar.a >= this.a) {
                            z2 = true;
                        }
                    }
                }
            }
            return z2;
        }

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(cVar, i, i2, eVar, z);
            if (a) {
                cVar.z |= 4;
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

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = this.a.booleanValue() && cVar.w;
            if (z2) {
                cVar.z |= 64;
            }
            return z2;
        }

        public void a(Boolean bool) {
            this.a = bool;
        }
    }

    public static class f extends a<Map<Integer, Integer>> {
        private Map<Integer, Integer> a;

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a == null) {
                return false;
            }
            Integer num = (Integer) this.a.get(Integer.valueOf(cVar.n()));
            boolean z2 = num != null && i >= num.intValue();
            if (!z2) {
                return z2;
            }
            cVar.z |= 256;
            return z2;
        }

        public void a(Map<Integer, Integer> map) {
            this.a = map;
        }
    }

    public static class g extends a<Map<Integer, Boolean>> {
        private Map<Integer, Boolean> a;

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a == null) {
                return false;
            }
            Boolean bool = (Boolean) this.a.get(Integer.valueOf(cVar.n()));
            boolean z2 = bool != null && bool.booleanValue() && z;
            if (!z2) {
                return z2;
            }
            cVar.z |= 512;
            return z2;
        }

        public void a(Map<Integer, Boolean> map) {
            this.a = map;
        }
    }

    public static class h extends a<Integer> {
        protected int a = -1;
        protected com.budejie.www.activity.video.barrage.danmaku.model.c b = null;

        private boolean b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a <= 0 || cVar.n() != 1) {
                return false;
            }
            if (i2 < this.a || cVar.d() || (this.b != null && cVar.b - this.b.b > danmakuContext.t.d / 20)) {
                this.b = cVar;
                return false;
            } else if (i > this.a && !cVar.e()) {
                return true;
            } else {
                this.b = cVar;
                return false;
            }
        }

        public synchronized boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean b;
            b = b(cVar, i, i2, eVar, z, danmakuContext);
            if (b) {
                cVar.z |= 2;
            }
            return b;
        }

        public void a(Integer num) {
            b();
            if (num != null && num.intValue() != this.a) {
                this.a = num.intValue();
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

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = (cVar == null || this.a.contains(Integer.valueOf(cVar.e))) ? false : true;
            if (z2) {
                cVar.z |= 8;
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

        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = cVar != null && this.a.contains(Integer.valueOf(cVar.n()));
            if (z2) {
                cVar.z |= 1;
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
        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = cVar != null && this.a.contains(cVar.v);
            if (z2) {
                cVar.z |= 32;
            }
            return z2;
        }
    }

    public static class m extends k<Integer> {
        public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = cVar != null && this.a.contains(Integer.valueOf(cVar.u));
            if (z2) {
                cVar.z |= 16;
            }
            return z2;
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar2 : this.b) {
            if (eVar2 != null) {
                boolean a = eVar2.a(cVar, i, i2, eVar, z, danmakuContext);
                cVar.A = danmakuContext.r.c;
                if (a) {
                    return;
                }
            }
        }
    }

    public boolean b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, com.budejie.www.activity.video.barrage.danmaku.model.e eVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar2 : this.c) {
            if (eVar2 != null) {
                boolean a = eVar2.a(cVar, i, i2, eVar, z, danmakuContext);
                cVar.A = danmakuContext.r.c;
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
