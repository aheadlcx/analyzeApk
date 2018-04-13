package android.support.v4.text.util;

import java.util.Comparator;

final class a implements Comparator<a> {
    a() {
    }

    public final int compare(a aVar, a aVar2) {
        if (aVar.c < aVar2.c) {
            return -1;
        }
        if (aVar.c > aVar2.c) {
            return 1;
        }
        if (aVar.d < aVar2.d) {
            return 1;
        }
        if (aVar.d <= aVar2.d) {
            return 0;
        }
        return -1;
    }
}
