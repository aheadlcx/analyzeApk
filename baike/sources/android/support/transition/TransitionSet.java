package android.support.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.transition.Transition.EpicenterCallback;
import android.support.transition.Transition.TransitionListener;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet extends Transition {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private ArrayList<Transition> g = new ArrayList();
    private boolean h = true;
    private int i;
    private boolean j = false;

    static class a extends TransitionListenerAdapter {
        TransitionSet a;

        a(TransitionSet transitionSet) {
            this.a = transitionSet;
        }

        public void onTransitionStart(@NonNull Transition transition) {
            if (!this.a.j) {
                this.a.b();
                this.a.j = true;
            }
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            TransitionSet.b(this.a);
            if (this.a.i == 0) {
                this.a.j = false;
                this.a.c();
            }
            transition.removeListener(this);
        }
    }

    static /* synthetic */ int b(TransitionSet transitionSet) {
        int i = transitionSet.i - 1;
        transitionSet.i = i;
        return i;
    }

    /* synthetic */ Transition c(ViewGroup viewGroup) {
        return d(viewGroup);
    }

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, be.i);
        setOrdering(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }

    @NonNull
    public TransitionSet setOrdering(int i) {
        switch (i) {
            case 0:
                this.h = true;
                break;
            case 1:
                this.h = false;
                break;
            default:
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    public int getOrdering() {
        return this.h ? 0 : 1;
    }

    @NonNull
    public TransitionSet addTransition(@NonNull Transition transition) {
        this.g.add(transition);
        transition.d = this;
        if (this.a >= 0) {
            transition.setDuration(this.a);
        }
        return this;
    }

    public int getTransitionCount() {
        return this.g.size();
    }

    public Transition getTransitionAt(int i) {
        if (i < 0 || i >= this.g.size()) {
            return null;
        }
        return (Transition) this.g.get(i);
    }

    @NonNull
    public TransitionSet setDuration(long j) {
        super.setDuration(j);
        if (this.a >= 0) {
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                ((Transition) this.g.get(i)).setDuration(j);
            }
        }
        return this;
    }

    @NonNull
    public TransitionSet setStartDelay(long j) {
        return (TransitionSet) super.setStartDelay(j);
    }

    @NonNull
    public TransitionSet setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        return (TransitionSet) super.setInterpolator(timeInterpolator);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull View view) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).addTarget(view);
        }
        return (TransitionSet) super.addTarget(view);
    }

    @NonNull
    public TransitionSet addTarget(@IdRes int i) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            ((Transition) this.g.get(i2)).addTarget(i);
        }
        return (TransitionSet) super.addTarget(i);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull String str) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).addTarget(str);
        }
        return (TransitionSet) super.addTarget(str);
    }

    @NonNull
    public TransitionSet addTarget(@NonNull Class cls) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).addTarget(cls);
        }
        return (TransitionSet) super.addTarget(cls);
    }

    @NonNull
    public TransitionSet addListener(@NonNull TransitionListener transitionListener) {
        return (TransitionSet) super.addListener(transitionListener);
    }

    @NonNull
    public TransitionSet removeTarget(@IdRes int i) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            ((Transition) this.g.get(i2)).removeTarget(i);
        }
        return (TransitionSet) super.removeTarget(i);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull View view) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).removeTarget(view);
        }
        return (TransitionSet) super.removeTarget(view);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull Class cls) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).removeTarget(cls);
        }
        return (TransitionSet) super.removeTarget(cls);
    }

    @NonNull
    public TransitionSet removeTarget(@NonNull String str) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).removeTarget(str);
        }
        return (TransitionSet) super.removeTarget(str);
    }

    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    @NonNull
    public Transition excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            ((Transition) this.g.get(i2)).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class cls, boolean z) {
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).excludeTarget(cls, z);
        }
        return super.excludeTarget(cls, z);
    }

    @NonNull
    public TransitionSet removeListener(@NonNull TransitionListener transitionListener) {
        return (TransitionSet) super.removeListener(transitionListener);
    }

    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        for (int i = 0; i < this.g.size(); i++) {
            ((Transition) this.g.get(i)).setPathMotion(pathMotion);
        }
    }

    @NonNull
    public TransitionSet removeTransition(@NonNull Transition transition) {
        this.g.remove(transition);
        transition.d = null;
        return this;
    }

    private void d() {
        TransitionListener aVar = new a(this);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).addListener(aVar);
        }
        this.i = this.g.size();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void a(ViewGroup viewGroup, bl blVar, bl blVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.g.size();
        int i = 0;
        while (i < size) {
            Transition transition = (Transition) this.g.get(i);
            if (startDelay > 0 && (this.h || i == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.a(viewGroup, blVar, blVar2, arrayList, arrayList2);
            i++;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void a() {
        if (this.g.isEmpty()) {
            b();
            c();
            return;
        }
        d();
        if (this.h) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).a();
            }
            return;
        }
        for (int i = 1; i < this.g.size(); i++) {
            ((Transition) this.g.get(i - 1)).addListener(new bj(this, (Transition) this.g.get(i)));
        }
        Transition transition = (Transition) this.g.get(0);
        if (transition != null) {
            transition.a();
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (b(transitionValues.view)) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.b(transitionValues.view)) {
                    transition.captureStartValues(transitionValues);
                    transitionValues.a.add(transition);
                }
            }
        }
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (b(transitionValues.view)) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.b(transitionValues.view)) {
                    transition.captureEndValues(transitionValues);
                    transitionValues.a.add(transition);
                }
            }
        }
    }

    void a(TransitionValues transitionValues) {
        super.a(transitionValues);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).a(transitionValues);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void pause(View view) {
        super.pause(view);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).pause(view);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void resume(View view) {
        super.resume(view);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).resume(view);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void b(ViewGroup viewGroup) {
        super.b(viewGroup);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).b(viewGroup);
        }
    }

    TransitionSet d(ViewGroup viewGroup) {
        super.c(viewGroup);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).c(viewGroup);
        }
        return this;
    }

    void b(boolean z) {
        super.b(z);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).b(z);
        }
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.g.get(i)).setEpicenterCallback(epicenterCallback);
        }
    }

    String a(String str) {
        String a = super.a(str);
        int i = 0;
        while (i < this.g.size()) {
            String str2 = a + "\n" + ((Transition) this.g.get(i)).a(str + "  ");
            i++;
            a = str2;
        }
        return a;
    }

    public Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.g = new ArrayList();
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            transitionSet.addTransition(((Transition) this.g.get(i)).clone());
        }
        return transitionSet;
    }
}
