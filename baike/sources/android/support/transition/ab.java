package android.support.transition;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.transition.Transition.EpicenterCallback;

class ab extends EpicenterCallback {
    final /* synthetic */ Rect a;
    final /* synthetic */ FragmentTransitionSupport b;

    ab(FragmentTransitionSupport fragmentTransitionSupport, Rect rect) {
        this.b = fragmentTransitionSupport;
        this.a = rect;
    }

    public Rect onGetEpicenter(@NonNull Transition transition) {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        return this.a;
    }
}
