package android.support.transition;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

final class az extends b {
    az() {
        super();
    }

    public float getGoneX(ViewGroup viewGroup, View view) {
        Object obj = 1;
        if (ViewCompat.getLayoutDirection(viewGroup) != 1) {
            obj = null;
        }
        if (obj != null) {
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
        return view.getTranslationX() - ((float) viewGroup.getWidth());
    }
}
