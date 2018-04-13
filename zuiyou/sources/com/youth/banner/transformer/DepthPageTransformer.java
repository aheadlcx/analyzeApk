package com.youth.banner.transformer;

import android.view.View;

public class DepthPageTransformer extends ABaseTransformer {
    private static final float MIN_SCALE = 0.75f;

    protected void onTransform(View view, float f) {
        if (f <= 0.0f) {
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        } else if (f <= 1.0f) {
            float abs = MIN_SCALE + (0.25f * (1.0f - Math.abs(f)));
            view.setAlpha(1.0f - f);
            view.setPivotY(0.5f * ((float) view.getHeight()));
            view.setTranslationX(((float) view.getWidth()) * (-f));
            view.setScaleX(abs);
            view.setScaleY(abs);
        }
    }

    protected boolean isPagingEnabled() {
        return true;
    }
}
