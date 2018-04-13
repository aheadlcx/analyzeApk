package com.yalantis.ucrop.util;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class SelectedStateListDrawable extends StateListDrawable {
    private int mSelectionColor;

    public SelectedStateListDrawable(Drawable drawable, int i) {
        this.mSelectionColor = i;
        addState(new int[]{16842913}, drawable);
        addState(new int[0], drawable);
    }

    protected boolean onStateChange(int[] iArr) {
        Object obj = null;
        for (int i : iArr) {
            if (i == 16842913) {
                obj = 1;
            }
        }
        if (obj != null) {
            super.setColorFilter(this.mSelectionColor, Mode.SRC_ATOP);
        } else {
            super.clearColorFilter();
        }
        return super.onStateChange(iArr);
    }

    public boolean isStateful() {
        return true;
    }
}
