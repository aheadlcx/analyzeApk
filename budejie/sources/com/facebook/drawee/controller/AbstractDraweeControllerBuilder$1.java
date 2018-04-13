package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

class AbstractDraweeControllerBuilder$1 extends BaseControllerListener<Object> {
    AbstractDraweeControllerBuilder$1() {
    }

    public void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
        if (animatable != null) {
            animatable.start();
        }
    }
}
