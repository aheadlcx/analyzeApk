package org.greenrobot.eventbus;

final class p {
    final Object a;
    final n b;
    volatile boolean c = true;

    p(Object obj, n nVar) {
        this.a = obj;
        this.b = nVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        if (this.a == pVar.a && this.b.equals(pVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.f.hashCode();
    }
}
