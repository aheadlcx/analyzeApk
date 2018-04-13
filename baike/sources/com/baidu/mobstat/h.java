package com.baidu.mobstat;

import java.util.Comparator;

class h implements Comparator<i> {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public int a(i iVar, i iVar2) {
        int i = iVar2.b - iVar.b;
        return i == 0 ? (iVar.d && iVar2.d) ? 0 : iVar.d ? -1 : iVar2.d ? 1 : i : i;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((i) obj, (i) obj2);
    }
}
