package com.facebook.e;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class b {
    private final Map<String, e> a = new HashMap();
    private final Set<e> b = new CopyOnWriteArraySet();
    private final h c;
    private final CopyOnWriteArraySet<j> d = new CopyOnWriteArraySet();
    private boolean e = true;

    public b(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("springLooper is required");
        }
        this.c = hVar;
        this.c.a(this);
    }

    public boolean a() {
        return this.e;
    }

    public e b() {
        e eVar = new e(this);
        a(eVar);
        return eVar;
    }

    void a(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("spring is required");
        } else if (this.a.containsKey(eVar.a())) {
            throw new IllegalArgumentException("spring is already registered");
        } else {
            this.a.put(eVar.a(), eVar);
        }
    }

    void a(double d) {
        for (e eVar : this.b) {
            if (eVar.d()) {
                eVar.c(d / 1000.0d);
            } else {
                this.b.remove(eVar);
            }
        }
    }

    public void b(double d) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((j) it.next()).a(this);
        }
        a(d);
        if (this.b.isEmpty()) {
            this.e = true;
        }
        it = this.d.iterator();
        while (it.hasNext()) {
            ((j) it.next()).b(this);
        }
        if (this.e) {
            this.c.c();
        }
    }

    void a(String str) {
        e eVar = (e) this.a.get(str);
        if (eVar == null) {
            throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
        }
        this.b.add(eVar);
        if (a()) {
            this.e = false;
            this.c.b();
        }
    }
}
