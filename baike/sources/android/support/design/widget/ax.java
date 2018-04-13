package android.support.design.widget;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

final class ax {
    ValueAnimator a = null;
    private final ArrayList<a> b = new ArrayList();
    private a c = null;
    private final AnimatorListener d = new ay(this);

    static class a {
        final int[] a;
        final ValueAnimator b;

        a(int[] iArr, ValueAnimator valueAnimator) {
            this.a = iArr;
            this.b = valueAnimator;
        }
    }

    ax() {
    }

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        a aVar = new a(iArr, valueAnimator);
        valueAnimator.addListener(this.d);
        this.b.add(aVar);
    }

    void a(int[] iArr) {
        a aVar;
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            aVar = (a) this.b.get(i);
            if (StateSet.stateSetMatches(aVar.a, iArr)) {
                break;
            }
        }
        aVar = null;
        if (aVar != this.c) {
            if (this.c != null) {
                a();
            }
            this.c = aVar;
            if (aVar != null) {
                a(aVar);
            }
        }
    }

    private void a(a aVar) {
        this.a = aVar.b;
        this.a.start();
    }

    private void a() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    public void jumpToCurrentState() {
        if (this.a != null) {
            this.a.end();
            this.a = null;
        }
    }
}
