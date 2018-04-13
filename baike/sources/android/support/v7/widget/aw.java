package android.support.v7.widget;

import android.support.v7.widget.GridLayout.Alignment;
import android.view.View;

final class aw extends Alignment {
    aw() {
    }

    int a(View view, int i) {
        return 0;
    }

    public int getAlignmentValue(View view, int i, int i2) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        int baseline = view.getBaseline();
        return baseline == -1 ? Integer.MIN_VALUE : baseline;
    }

    public d getBounds() {
        return new ax(this);
    }

    String a() {
        return "BASELINE";
    }
}
