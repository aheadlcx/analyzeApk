package org.ahocorasick.interval;

import java.util.Comparator;

public class e implements Comparator<c> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((c) obj, (c) obj2);
    }

    public int a(c cVar, c cVar2) {
        int c = cVar2.c() - cVar.c();
        if (c == 0) {
            return cVar.a() - cVar2.a();
        }
        return c;
    }
}
