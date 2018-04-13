package b.a.a.b;

import android.support.v4.view.ViewCompat;
import android.view.View;

public final class a {
    public static void a(View view) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setScaleY(view, 1.0f);
        ViewCompat.setScaleX(view, 1.0f);
        ViewCompat.setTranslationY(view, 0.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setRotation(view, 0.0f);
        ViewCompat.setRotationY(view, 0.0f);
        ViewCompat.setRotationX(view, 0.0f);
        ViewCompat.setPivotY(view, (float) (view.getMeasuredHeight() / 2));
        ViewCompat.setPivotX(view, (float) (view.getMeasuredWidth() / 2));
        ViewCompat.animate(view).setInterpolator(null).setStartDelay(0);
    }
}
