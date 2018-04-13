package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

public class BaseControllerListener<INFO> implements ControllerListener<INFO> {
    private static final ControllerListener<Object> NO_OP_LISTENER = new BaseControllerListener();

    public static <INFO> ControllerListener<INFO> getNoOpListener() {
        return NO_OP_LISTENER;
    }

    public void onSubmit(String str, Object obj) {
    }

    public void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
    }

    public void onIntermediateImageSet(String str, @Nullable INFO info) {
    }

    public void onIntermediateImageFailed(String str, Throwable th) {
    }

    public void onFailure(String str, Throwable th) {
    }

    public void onRelease(String str) {
    }
}
