package android.support.transition;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.transition.Transition.EpicenterCallback;

class y extends EpicenterCallback {
    final /* synthetic */ Rect a;
    final /* synthetic */ FragmentTransitionSupport b;

    y(FragmentTransitionSupport fragmentTransitionSupport, Rect rect) {
        this.b = fragmentTransitionSupport;
        this.a = rect;
    }

    public Rect onGetEpicenter(@NonNull Transition transition) {
        return this.a;
    }
}