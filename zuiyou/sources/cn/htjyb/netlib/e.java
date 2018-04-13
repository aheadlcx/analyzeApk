package cn.htjyb.netlib;

import java.util.HashSet;
import java.util.LinkedList;

public class e {
    private final int a;
    private final LinkedList<d> b = new LinkedList();
    private final LinkedList<d> c = new LinkedList();
    private final HashSet<d> d;

    public e(int i) {
        this.a = i;
        this.d = new HashSet(this.a);
    }

    public void a(d dVar, boolean z) {
        dVar.a(this);
        if (this.d.size() < this.a) {
            this.d.add(dVar);
            dVar.b();
        } else if (z) {
            this.c.add(dVar);
        } else {
            this.b.add(dVar);
        }
    }

    public boolean a(d dVar) {
        return this.b.remove(dVar) || this.c.remove(dVar);
    }

    public void b(d dVar) {
        if (this.d.remove(dVar)) {
            d dVar2 = null;
            if (!this.c.isEmpty()) {
                dVar2 = (d) this.c.removeFirst();
            } else if (!this.b.isEmpty()) {
                dVar2 = (d) this.b.removeFirst();
            }
            if (dVar2 != null) {
                this.d.add(dVar2);
                dVar2.b();
            }
        }
    }
}
