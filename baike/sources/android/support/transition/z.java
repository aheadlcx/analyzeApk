package android.support.transition;

import android.support.annotation.NonNull;
import android.support.transition.Transition.TransitionListener;
import android.view.View;
import java.util.ArrayList;

class z implements TransitionListener {
    final /* synthetic */ View a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ FragmentTransitionSupport c;

    z(FragmentTransitionSupport fragmentTransitionSupport, View view, ArrayList arrayList) {
        this.c = fragmentTransitionSupport;
        this.a = view;
        this.b = arrayList;
    }

    public void onTransitionStart(@NonNull Transition transition) {
    }

    public void onTransitionEnd(@NonNull Transition transition) {
        transition.removeListener(this);
        this.a.setVisibility(8);
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.b.get(i)).setVisibility(0);
        }
    }

    public void onTransitionCancel(@NonNull Transition transition) {
    }

    public void onTransitionPause(@NonNull Transition transition) {
    }

    public void onTransitionResume(@NonNull Transition transition) {
    }
}
