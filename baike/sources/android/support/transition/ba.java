package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

final class ba extends c {
    ba() {
        super();
    }

    public float getGoneY(ViewGroup viewGroup, View view) {
        return view.getTranslationY() - ((float) viewGroup.getHeight());
    }
}
