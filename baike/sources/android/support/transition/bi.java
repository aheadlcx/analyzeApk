package android.support.transition;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

class bi extends TransitionListenerAdapter {
    final /* synthetic */ ArrayMap a;
    final /* synthetic */ a b;

    bi(a aVar, ArrayMap arrayMap) {
        this.b = aVar;
        this.a = arrayMap;
    }

    public void onTransitionEnd(@NonNull Transition transition) {
        ((ArrayList) this.a.get(this.b.b)).remove(transition);
    }
}
