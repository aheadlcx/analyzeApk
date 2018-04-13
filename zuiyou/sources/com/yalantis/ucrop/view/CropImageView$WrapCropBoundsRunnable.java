package com.yalantis.ucrop.view;

import com.yalantis.ucrop.util.CubicEasing;
import java.lang.ref.WeakReference;

class CropImageView$WrapCropBoundsRunnable implements Runnable {
    private final float mCenterDiffX;
    private final float mCenterDiffY;
    private final WeakReference<CropImageView> mCropImageView;
    private final float mDeltaScale;
    private final long mDurationMs;
    private final float mOldScale;
    private final float mOldX;
    private final float mOldY;
    private final long mStartTime = System.currentTimeMillis();
    private final boolean mWillBeImageInBoundsAfterTranslate;

    public CropImageView$WrapCropBoundsRunnable(CropImageView cropImageView, long j, float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        this.mCropImageView = new WeakReference(cropImageView);
        this.mDurationMs = j;
        this.mOldX = f;
        this.mOldY = f2;
        this.mCenterDiffX = f3;
        this.mCenterDiffY = f4;
        this.mOldScale = f5;
        this.mDeltaScale = f6;
        this.mWillBeImageInBoundsAfterTranslate = z;
    }

    public void run() {
        CropImageView cropImageView = (CropImageView) this.mCropImageView.get();
        if (cropImageView != null) {
            float min = (float) Math.min(this.mDurationMs, System.currentTimeMillis() - this.mStartTime);
            float easeOut = CubicEasing.easeOut(min, 0.0f, this.mCenterDiffX, (float) this.mDurationMs);
            float easeOut2 = CubicEasing.easeOut(min, 0.0f, this.mCenterDiffY, (float) this.mDurationMs);
            float easeInOut = CubicEasing.easeInOut(min, 0.0f, this.mDeltaScale, (float) this.mDurationMs);
            if (min < ((float) this.mDurationMs)) {
                cropImageView.postTranslate(easeOut - (cropImageView.mCurrentImageCenter[0] - this.mOldX), easeOut2 - (cropImageView.mCurrentImageCenter[1] - this.mOldY));
                if (!this.mWillBeImageInBoundsAfterTranslate) {
                    cropImageView.zoomInImage(this.mOldScale + easeInOut, CropImageView.access$000(cropImageView).centerX(), CropImageView.access$000(cropImageView).centerY());
                }
                if (!cropImageView.isImageWrapCropBounds()) {
                    cropImageView.post(this);
                }
            }
        }
    }
}
