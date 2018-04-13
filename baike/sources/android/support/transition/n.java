package android.support.transition;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

class n extends TransitionListenerAdapter {
    boolean a = false;
    final /* synthetic */ ViewGroup b;
    final /* synthetic */ ChangeBounds c;

    n(ChangeBounds changeBounds, ViewGroup viewGroup) {
        this.c = changeBounds;
        this.b = viewGroup;
    }

    public void onTransitionCancel(@NonNull Transition transition) {
        br.a(this.b, false);
        this.a = true;
    }

    public void onTransitionEnd(@NonNull Transition transition) {
        if (!this.a) {
            br.a(this.b, false);
        }
        transition.removeListener(this);
    }

    public void onTransitionPause(@NonNull Transition transition) {
        br.a(this.b, false);
    }

    public void onTransitionResume(@NonNull Transition transition) {
        br.a(this.b, true);
    }
}
