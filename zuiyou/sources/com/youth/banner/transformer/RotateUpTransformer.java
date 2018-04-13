package com.youth.banner.transformer;

import android.view.View;

public class RotateUpTransformer extends ABaseTransformer {
    private static final float ROT_MOD = -15.0f;

    protected void onTransform(View view, float f) {
        float f2 = ROT_MOD * f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(0.0f);
        view.setTranslationX(0.0f);
        view.setRotation(f2);
    }

    protected boolean isPagingEnabled() {
        return true;
    }
}
