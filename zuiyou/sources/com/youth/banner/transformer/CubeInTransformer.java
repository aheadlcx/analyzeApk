package com.youth.banner.transformer;

import android.view.View;

public class CubeInTransformer extends ABaseTransformer {
    protected void onTransform(View view, float f) {
        view.setPivotX(f > 0.0f ? 0.0f : (float) view.getWidth());
        view.setPivotY(0.0f);
        view.setRotationY(-90.0f * f);
    }

    public boolean isPagingEnabled() {
        return true;
    }
}
