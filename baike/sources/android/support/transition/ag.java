package android.support.transition;

import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.support.transition.GhostViewImpl.Creator;
import android.view.View;
import android.view.ViewGroup;

class ag {
    private static final Creator a;

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new a();
        }
    }

    static GhostViewImpl a(View view, ViewGroup viewGroup, Matrix matrix) {
        return a.addGhost(view, viewGroup, matrix);
    }

    static void a(View view) {
        a.removeGhost(view);
    }
}
