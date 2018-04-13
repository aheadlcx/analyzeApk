package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

final class bb extends b {
    bb() {
        super();
    }

    public float getGoneX(ViewGroup viewGroup, View view) {
        return view.getTranslationX() + ((float) viewGroup.getWidth());
    }
}
