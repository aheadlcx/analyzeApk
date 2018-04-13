package android.support.v7.widget;

import java.util.Comparator;

final class ap implements Comparator<b> {
    ap() {
    }

    public int compare(b bVar, b bVar2) {
        int i = -1;
        if ((bVar.view == null ? 1 : 0) != (bVar2.view == null ? 1 : 0)) {
            return bVar.view == null ? 1 : -1;
        } else {
            if (bVar.immediate != bVar2.immediate) {
                if (!bVar.immediate) {
                    i = 1;
                }
                return i;
            }
            int i2 = bVar2.viewVelocity - bVar.viewVelocity;
            if (i2 != 0) {
                return i2;
            }
            i2 = bVar.distanceToItem - bVar2.distanceToItem;
            if (i2 == 0) {
                return 0;
            }
            return i2;
        }
    }
}
