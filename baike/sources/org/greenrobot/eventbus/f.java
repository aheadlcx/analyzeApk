package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class f {
    private static final List<f> d = new ArrayList();
    Object a;
    i b;
    f c;

    private f(Object obj, i iVar) {
        this.a = obj;
        this.b = iVar;
    }

    static f a(i iVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                f fVar = (f) d.remove(size - 1);
                fVar.a = obj;
                fVar.b = iVar;
                fVar.c = null;
                return fVar;
            }
            return new f(obj, iVar);
        }
    }

    static void a(f fVar) {
        fVar.a = null;
        fVar.b = null;
        fVar.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(fVar);
            }
        }
    }
}
