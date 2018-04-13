package android.support.transition;

import android.support.annotation.NonNull;
import android.view.View;

class w extends TransitionListenerAdapter {
    final /* synthetic */ View a;
    final /* synthetic */ Fade b;

    w(Fade fade, View view) {
        this.b = fade;
        this.a = view;
    }

    public void onTransitionEnd(@NonNull Transition transition) {
        bz.a(this.a, 1.0f);
        bz.e(this.a);
        transition.removeListener(this);
    }
}
