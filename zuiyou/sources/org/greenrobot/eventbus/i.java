package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class i {
    private static final List<i> d = new ArrayList();
    Object a;
    p b;
    i c;

    private i(Object obj, p pVar) {
        this.a = obj;
        this.b = pVar;
    }

    static i a(p pVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                i iVar = (i) d.remove(size - 1);
                iVar.a = obj;
                iVar.b = pVar;
                iVar.c = null;
                return iVar;
            }
            return new i(obj, pVar);
        }
    }

    static void a(i iVar) {
        iVar.a = null;
        iVar.b = null;
        iVar.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(iVar);
            }
        }
    }
}
