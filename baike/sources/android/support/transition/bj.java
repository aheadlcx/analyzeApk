package android.support.transition;

import android.support.annotation.NonNull;

class bj extends TransitionListenerAdapter {
    final /* synthetic */ Transition a;
    final /* synthetic */ TransitionSet b;

    bj(TransitionSet transitionSet, Transition transition) {
        this.b = transitionSet;
        this.a = transition;
    }

    public void onTransitionEnd(@NonNull Transition transition) {
        this.a.a();
        transition.removeListener(this);
    }
}
