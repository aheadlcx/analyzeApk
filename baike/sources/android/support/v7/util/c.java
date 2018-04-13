package android.support.v7.util;

import java.util.Comparator;

final class c implements Comparator<c> {
    c() {
    }

    public int compare(c cVar, c cVar2) {
        int i = cVar.a - cVar2.a;
        return i == 0 ? cVar.b - cVar2.b : i;
    }
}
