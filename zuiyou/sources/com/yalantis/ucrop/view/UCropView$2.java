package com.yalantis.ucrop.view;

import android.graphics.RectF;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;

class UCropView$2 implements OverlayViewChangeListener {
    final /* synthetic */ UCropView this$0;

    UCropView$2(UCropView uCropView) {
        this.this$0 = uCropView;
    }

    public void onCropRectUpdated(RectF rectF) {
        UCropView.access$100(this.this$0).setCropRect(rectF);
    }
}
