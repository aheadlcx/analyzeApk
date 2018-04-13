package android.support.transition;

import android.support.annotation.NonNull;
import android.support.transition.Transition.TransitionListener;
import java.util.ArrayList;

class aa implements TransitionListener {
    final /* synthetic */ Object a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ Object c;
    final /* synthetic */ ArrayList d;
    final /* synthetic */ Object e;
    final /* synthetic */ ArrayList f;
    final /* synthetic */ FragmentTransitionSupport g;

    aa(FragmentTransitionSupport fragmentTransitionSupport, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.g = fragmentTransitionSupport;
        this.a = obj;
        this.b = arrayList;
        this.c = obj2;
        this.d = arrayList2;
        this.e = obj3;
        this.f = arrayList3;
    }

    public void onTransitionStart(@NonNull Transition transition) {
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

    public void onTransitionEnd(@NonNull Transition transition) {
    }

    public void onTransitionCancel(@NonNull Transition transition) {
    }

    public void onTransitionPause(@NonNull Transition transition) {
    }

    public void onTransitionResume(@NonNull Transition transition) {
    }
}
