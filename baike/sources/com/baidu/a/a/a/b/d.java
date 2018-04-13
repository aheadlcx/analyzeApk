package com.baidu.a.a.a.b;

import java.util.Comparator;

class d implements Comparator<a> {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public int a(a aVar, a aVar2) {
        int i = aVar2.b - aVar.b;
        return i == 0 ? (aVar.d && aVar2.d) ? 0 : aVar.d ? -1 : aVar2.d ? 1 : i : i;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((a) obj, (a) obj2);
    }
}
