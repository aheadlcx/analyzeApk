package com.facebook.fresco.animation.drawable;

import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2.DrawListener;
import com.facebook.fresco.animation.frame.FrameScheduler;

public class AnimatedDrawable2DebugDrawListener implements DrawListener {
    private static final Class<?> TAG = AnimatedDrawable2DebugDrawListener.class;
    private int mDrawCalls;
    private int mDuplicateFrames;
    private int mLastFrameNumber = -1;
    private int mSkippedFrames;

    public void onDraw(AnimatedDrawable2 animatedDrawable2, FrameScheduler frameScheduler, int i, boolean z, boolean z2, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        if (animatedDrawable2.getAnimationBackend() != null) {
            int frameCount = animatedDrawable2.getAnimationBackend().getFrameCount();
            long j8 = j2 - j3;
            this.mDrawCalls++;
            int i2 = (this.mLastFrameNumber + 1) % frameCount;
            if (i2 != i) {
                if (this.mLastFrameNumber == i) {
                    this.mDuplicateFrames++;
                } else {
                    i2 = (i - i2) % frameCount;
                    if (i2 < 0) {
                        i2 += frameCount;
                    }
                    this.mSkippedFrames = i2 + this.mSkippedFrames;
                }
            }
            this.mLastFrameNumber = i;
            FLog.d(TAG, "draw: frame: %2d, drawn: %b, delay: %3d ms, rendering: %3d ms, prev: %3d ms ago, duplicates: %3d, skipped: %3d, draw calls: %4d, anim time: %6d ms, next start: %6d ms, next scheduled: %6d ms", Integer.valueOf(i), Boolean.valueOf(z), Long.valueOf((j2 % frameScheduler.getLoopDurationMs()) - frameScheduler.getTargetRenderTimeMs(i)), Long.valueOf(j5 - j4), Long.valueOf(j8), Integer.valueOf(this.mDuplicateFrames), Integer.valueOf(this.mSkippedFrames), Integer.valueOf(this.mDrawCalls), Long.valueOf(j2), Long.valueOf(j6), Long.valueOf(j7));
        }
    }
}
