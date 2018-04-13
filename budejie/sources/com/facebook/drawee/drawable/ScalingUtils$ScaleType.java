package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

public interface ScalingUtils$ScaleType {
    public static final ScalingUtils$ScaleType CENTER = ScalingUtils$ScaleTypeCenter.INSTANCE;
    public static final ScalingUtils$ScaleType CENTER_CROP = ScalingUtils$ScaleTypeCenterCrop.INSTANCE;
    public static final ScalingUtils$ScaleType CENTER_INSIDE = ScalingUtils$ScaleTypeCenterInside.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_CENTER = ScalingUtils$ScaleTypeFitCenter.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_END = ScalingUtils$ScaleTypeFitEnd.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_START = ScalingUtils$ScaleTypeFitStart.INSTANCE;
    public static final ScalingUtils$ScaleType FIT_XY = ScalingUtils$ScaleTypeFitXY.INSTANCE;
    public static final ScalingUtils$ScaleType FOCUS_CROP = ScalingUtils$ScaleTypeFocusCrop.INSTANCE;

    Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2);
}
