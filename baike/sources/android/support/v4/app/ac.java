package android.support.v4.app;

import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import java.util.ArrayList;

class ac implements TransitionListener {
    final /* synthetic */ Object a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ Object c;
    final /* synthetic */ ArrayList d;
    final /* synthetic */ Object e;
    final /* synthetic */ ArrayList f;
    final /* synthetic */ z g;

    ac(z zVar, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.g = zVar;
        this.a = obj;
        this.b = arrayList;
        this.c = obj2;
        this.d = arrayList2;
        this.e = obj3;
        this.f = arrayList3;
    }

    public void onTransitionStart(Transition transition) {
        if (this.a != null) {
            this.g.replaceTargets(this.a, this.b, null);
        }
        if (this.c != null) {
            this.g.replaceTargets(this.c, this.d, null);
        }
        if (this.e != null) {
            this.g.replaceTargets(this.e, this.f, null);
        }
    }

    public void onTransitionEnd(Transition transition) {
    }

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }
}
