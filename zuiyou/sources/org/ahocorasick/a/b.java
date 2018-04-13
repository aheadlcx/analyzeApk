package org.ahocorasick.a;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class b {
    private final int a;
    private final b b;
    private Map<Character, b> c;
    private b d;
    private Set<String> e;

    public b() {
        this(0);
    }

    public b(int i) {
        b bVar = null;
        this.c = new HashMap();
        this.d = null;
        this.e = null;
        this.a = i;
        if (i == 0) {
            bVar = this;
        }
        this.b = bVar;
    }

    private b a(Character ch, boolean z) {
        b bVar = (b) this.c.get(ch);
        if (z || bVar != null || this.b == null) {
            return bVar;
        }
        return this.b;
    }

    public b a(Character ch) {
        return a(ch, false);
    }

    public Collection<String> a() {
        return this.e == null ? Collections.emptyList() : this.e;
    }

    public b b() {
        return this.d;
    }
}
