package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.request.b.b;
import java.util.Comparator;

class ac$b implements Comparator<b> {
    private final c a;

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((b) obj, (b) obj2);
    }

    ac$b(c cVar) {
        this.a = cVar;
    }

    public int a(b bVar, b bVar2) {
        boolean a = ac.a(bVar, this.a);
        boolean a2 = ac.a(bVar2, this.a);
        if (a && a2) {
            return bVar.b() - bVar2.b();
        }
        if (a) {
            return -1;
        }
        if (a2) {
            return 1;
        }
        return bVar2.b() - bVar.b();
    }
}
