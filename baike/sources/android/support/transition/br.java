package android.support.transition;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class br {
    private static final bv a;

    static {
        if (VERSION.SDK_INT >= 18) {
            a = new bu();
        } else {
            a = new bs();
        }
    }

    static bq a(@NonNull ViewGroup viewGroup) {
        return a.getOverlay(viewGroup);
    }

    static void a(@NonNull ViewGroup viewGroup, boolean z) {
        a.suppressLayout(viewGroup, z);
    }
}
