package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

final class bd extends c {
    bd() {
        super();
    }

    public float getGoneY(ViewGroup viewGroup, View view) {
        return view.getTranslationY() + ((float) viewGroup.getHeight());
    }
}
