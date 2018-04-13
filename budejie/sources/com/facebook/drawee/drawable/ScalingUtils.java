package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

public class ScalingUtils {

    public static class InterpolatingScaleType implements ScalingUtils$ScaleType, ScalingUtils$StatefulScaleType {
        @Nullable
        private final Rect mBoundsFrom;
        @Nullable
        private final Rect mBoundsTo;
        private float mInterpolatingValue;
        private final float[] mMatrixValuesFrom;
        private final float[] mMatrixValuesInterpolated;
        private final float[] mMatrixValuesTo;
        private final ScalingUtils$ScaleType mScaleTypeFrom;
        private final ScalingUtils$ScaleType mScaleTypeTo;

        public InterpolatingScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType, ScalingUtils$ScaleType scalingUtils$ScaleType2, @Nullable Rect rect, @Nullable Rect rect2) {
            this.mMatrixValuesFrom = new float[9];
            this.mMatrixValuesTo = new float[9];
            this.mMatrixValuesInterpolated = new float[9];
            this.mScaleTypeFrom = scalingUtils$ScaleType;
            this.mScaleTypeTo = scalingUtils$ScaleType2;
            this.mBoundsFrom = rect;
            this.mBoundsTo = rect2;
        }

        public InterpolatingScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType, ScalingUtils$ScaleType scalingUtils$ScaleType2) {
            this(scalingUtils$ScaleType, scalingUtils$ScaleType2, null, null);
        }

        public ScalingUtils$ScaleType getScaleTypeFrom() {
            return this.mScaleTypeFrom;
        }

        public ScalingUtils$ScaleType getScaleTypeTo() {
            return this.mScaleTypeTo;
        }

        @Nullable
        public Rect getBoundsFrom() {
            return this.mBoundsFrom;
        }

        @Nullable
        public Rect getBoundsTo() {
            return this.mBoundsTo;
        }

        public void setValue(float f) {
            this.mInterpolatingValue = f;
        }

        public float getValue() {
            return this.mInterpolatingValue;
        }

        public Object getState() {
            return Float.valueOf(this.mInterpolatingValue);
        }

        public Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2) {
            Rect rect2 = this.mBoundsFrom != null ? this.mBoundsFrom : rect;
            if (this.mBoundsTo != null) {
                rect = this.mBoundsTo;
            }
            this.mScaleTypeFrom.getTransform(matrix, rect2, i, i2, f, f2);
            matrix.getValues(this.mMatrixValuesFrom);
            this.mScaleTypeTo.getTransform(matrix, rect, i, i2, f, f2);
            matrix.getValues(this.mMatrixValuesTo);
            for (int i3 = 0; i3 < 9; i3++) {
                this.mMatrixValuesInterpolated[i3] = (this.mMatrixValuesFrom[i3] * (1.0f - this.mInterpolatingValue)) + (this.mMatrixValuesTo[i3] * this.mInterpolatingValue);
            }
            matrix.setValues(this.mMatrixValuesInterpolated);
            return matrix;
        }

        public String toString() {
            return String.format("InterpolatingScaleType(%s -> %s)", new Object[]{String.valueOf(this.mScaleTypeFrom), String.valueOf(this.mScaleTypeTo)});
        }
    }

    @Nullable
    public static ScaleTypeDrawable getActiveScaleTypeDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) drawable;
        }
        if (drawable instanceof DrawableParent) {
            return getActiveScaleTypeDrawable(((DrawableParent) drawable).getDrawable());
        }
        if (drawable instanceof ArrayDrawable) {
            ArrayDrawable arrayDrawable = (ArrayDrawable) drawable;
            int numberOfLayers = arrayDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                Drawable activeScaleTypeDrawable = getActiveScaleTypeDrawable(arrayDrawable.getDrawable(i));
                if (activeScaleTypeDrawable != null) {
                    return activeScaleTypeDrawable;
                }
            }
        }
        return null;
    }
}
