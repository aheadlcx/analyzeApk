package com.youth.banner.transformer;

import android.view.View;

public class ZoomInTransformer extends ABaseTransformer {
    protected void onTransform(View view, float f) {
        float abs = f < 0.0f ? f + 1.0f : Math.abs(1.0f - f);
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        abs = (f < -1.0f || f > 1.0f) ? 0.0f : 1.0f - (abs - 1.0f);
        view.setAlpha(abs);
    }
}
