package com.youth.banner.transformer;

import android.view.View;

public class RotateDownTransformer extends ABaseTransformer {
    private static final float ROT_MOD = -15.0f;

    protected void onTransform(View view, float f) {
        float height = (float) view.getHeight();
        float f2 = (ROT_MOD * f) * -1.25f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(height);
        view.setRotation(f2);
    }

    protected boolean isPagingEnabled() {
        return true;
    }
}
