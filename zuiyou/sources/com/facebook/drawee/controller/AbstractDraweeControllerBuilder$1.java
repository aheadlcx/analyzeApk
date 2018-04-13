package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

class AbstractDraweeControllerBuilder$1 extends b<Object> {
    AbstractDraweeControllerBuilder$1() {
    }

    public void a(String str, @Nullable Object obj, @Nullable Animatable animatable) {
        if (animatable != null) {
            animatable.start();
        }
    }
}
