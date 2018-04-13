package com.yalantis.ucrop.view;

import com.yalantis.ucrop.callback.CropBoundsChangeListener;

class UCropView$1 implements CropBoundsChangeListener {
    final /* synthetic */ UCropView this$0;

    UCropView$1(UCropView uCropView) {
        this.this$0 = uCropView;
    }

    public void onCropAspectRatioChanged(float f) {
        UCropView.access$000(this.this$0).setTargetAspectRatio(f);
    }
}
