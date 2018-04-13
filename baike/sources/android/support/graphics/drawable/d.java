package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.graphics.drawable.Animatable2Compat.AnimationCallback;
import java.util.ArrayList;

class d extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatedVectorDrawableCompat a;

    d(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        this.a = animatedVectorDrawableCompat;
    }

    public void onAnimationStart(Animator animator) {
        ArrayList arrayList = new ArrayList(this.a.h);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((AnimationCallback) arrayList.get(i)).onAnimationStart(this.a);
        }
    }

    public void onAnimationEnd(Animator animator) {
        ArrayList arrayList = new ArrayList(this.a.h);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((AnimationCallback) arrayList.get(i)).onAnimationEnd(this.a);
        }
    }
}
