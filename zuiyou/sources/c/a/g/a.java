package c.a.g;

import java.util.ArrayList;

public class a {
    private final ArrayList<b> a = new ArrayList();

    public synchronized void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException();
        } else if (!this.a.contains(bVar)) {
            this.a.add(bVar);
        }
    }

    public synchronized void b(b bVar) {
        this.a.remove(bVar);
    }

    public void m() {
        a(null);
    }

    public void a(Object obj) {
        synchronized (this) {
            b[] bVarArr = (b[]) this.a.toArray(new b[this.a.size()]);
        }
        for (int length = bVarArr.length - 1; length >= 0; length--) {
            bVarArr[length].a(this, obj);
        }
    }
}
