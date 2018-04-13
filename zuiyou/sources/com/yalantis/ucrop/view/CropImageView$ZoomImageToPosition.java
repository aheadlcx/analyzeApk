package com.yalantis.ucrop.view;

import com.yalantis.ucrop.util.CubicEasing;
import java.lang.ref.WeakReference;

class CropImageView$ZoomImageToPosition implements Runnable {
    private final WeakReference<CropImageView> mCropImageView;
    private final float mDeltaScale;
    private final float mDestX;
    private final float mDestY;
    private final long mDurationMs;
    private final float mOldScale;
    private final long mStartTime = System.currentTimeMillis();

    public CropImageView$ZoomImageToPosition(CropImageView cropImageView, long j, float f, float f2, float f3, float f4) {
        this.mCropImageView = new WeakReference(cropImageView);
        this.mDurationMs = j;
        this.mOldScale = f;
        this.mDeltaScale = f2;
        this.mDestX = f3;
        this.mDestY = f4;
    }

    public void run() {
        CropImageView cropImageView = (CropImageView) this.mCropImageView.get();
        if (cropImageView != null) {
            float min = (float) Math.min(this.mDurationMs, System.currentTimeMillis() - this.mStartTime);
            float easeInOut = CubicEasing.easeInOut(min, 0.0f, this.mDeltaScale, (float) this.mDurationMs);
            if (min < ((float) this.mDurationMs)) {
                cropImageView.zoomInImage(this.mOldScale + easeInOut, this.mDestX, this.mDestY);
                cropImageView.post(this);
                return;
            }
            cropImageView.setImageToWrapCropBounds();
        }
    }
}
