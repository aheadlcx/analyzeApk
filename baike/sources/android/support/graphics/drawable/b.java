package android.support.graphics.drawable;

import android.graphics.drawable.Animatable2.AnimationCallback;
import android.graphics.drawable.Drawable;

class b extends AnimationCallback {
    final /* synthetic */ Animatable2Compat.AnimationCallback a;

    b(Animatable2Compat.AnimationCallback animationCallback) {
        this.a = animationCallback;
    }

    public void onAnimationStart(Drawable drawable) {
        this.a.onAnimationStart(drawable);
    }

    public void onAnimationEnd(Drawable drawable) {
        this.a.onAnimationEnd(drawable);
    }
}
