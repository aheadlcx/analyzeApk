package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;

class aa extends EpicenterCallback {
    final /* synthetic */ Rect a;
    final /* synthetic */ z b;

    aa(z zVar, Rect rect) {
        this.b = zVar;
        this.a = rect;
    }

    public Rect onGetEpicenter(Transition transition) {
        return this.a;
    }
}
