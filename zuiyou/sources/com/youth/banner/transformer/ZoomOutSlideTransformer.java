package com.youth.banner.transformer;

import android.view.View;

public class ZoomOutSlideTransformer extends ABaseTransformer {
    private static final float MIN_ALPHA = 0.5f;
    private static final float MIN_SCALE = 0.85f;

    protected void onTransform(View view, float f) {
        if (f >= -1.0f || f <= 1.0f) {
            float height = (float) view.getHeight();
            float width = (float) view.getWidth();
            float max = Math.max(MIN_SCALE, 1.0f - Math.abs(f));
            float f2 = ((1.0f - max) * height) / 2.0f;
            float f3 = ((1.0f - max) * width) / 2.0f;
            view.setPivotY(height * MIN_ALPHA);
            view.setPivotX(MIN_ALPHA * width);
            if (f < 0.0f) {
                view.setTranslationX(f3 - (f2 / 2.0f));
            } else {
                view.setTranslationX((-f3) + (f2 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - MIN_SCALE) / 0.14999998f) * MIN_ALPHA) + MIN_ALPHA);
        }
    }
}
