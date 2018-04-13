package android.support.v4.app;

import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.view.View;
import java.util.ArrayList;

class ab implements TransitionListener {
    final /* synthetic */ View a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ z c;

    ab(z zVar, View view, ArrayList arrayList) {
        this.c = zVar;
        this.a = view;
        this.b = arrayList;
    }

    public void onTransitionStart(Transition transition) {
    }

    public void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
        this.a.setVisibility(8);
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.b.get(i)).setVisibility(0);
        }
    }

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }
}
