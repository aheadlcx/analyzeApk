package com.youth.banner.transformer;

import android.view.View;

public class DefaultTransformer extends ABaseTransformer {
    protected void onTransform(View view, float f) {
    }

    public boolean isPagingEnabled() {
        return true;
    }
}
