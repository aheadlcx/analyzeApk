package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;

class ad extends EpicenterCallback {
    final /* synthetic */ Rect a;
    final /* synthetic */ z b;

    ad(z zVar, Rect rect) {
        this.b = zVar;
        this.a = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        return this.a;
    }
}
