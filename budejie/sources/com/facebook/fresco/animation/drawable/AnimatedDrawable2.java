package com.facebook.fresco.animation.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.drawable.DrawableProperties;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.frame.DropFramesFrameScheduler;
import com.facebook.fresco.animation.frame.FrameScheduler;
import javax.annotation.Nullable;

public class AnimatedDrawable2 extends Drawable implements Animatable, DrawableWithCaches {
    private static final int DEFAULT_FRAME_SCHEDULING_DELAY_MS = 8;
    private static final int DEFAULT_FRAME_SCHEDULING_OFFSET_MS = 0;
    private static final AnimationListener NO_OP_LISTENER = new BaseAnimationListener();
    private static final Class<?> TAG = AnimatedDrawable2.class;
    @Nullable
    private AnimationBackend mAnimationBackend;
    private volatile AnimationListener mAnimationListener;
    @Nullable
    private volatile AnimatedDrawable2$DrawListener mDrawListener;
    @Nullable
    private DrawableProperties mDrawableProperties;
    private int mDroppedFrames;
    @Nullable
    private FrameScheduler mFrameScheduler;
    private long mFrameSchedulingDelayMs;
    private long mFrameSchedulingOffsetMs;
    private final Runnable mInvalidateRunnable;
    private volatile boolean mIsRunning;
    private long mLastFrameAnimationTimeMs;
    private long mStartTimeMs;

    public AnimatedDrawable2() {
        this(null);
    }

    public AnimatedDrawable2(@Nullable AnimationBackend animationBackend) {
        this.mFrameSchedulingDelayMs = 8;
        this.mFrameSchedulingOffsetMs = 0;
        this.mAnimationListener = NO_OP_LISTENER;
        this.mDrawListener = null;
        this.mInvalidateRunnable = new AnimatedDrawable2$1(this);
        this.mAnimationBackend = animationBackend;
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(this.mAnimationBackend);
    }

    public int getIntrinsicWidth() {
        if (this.mAnimationBackend == null) {
            return super.getIntrinsicWidth();
        }
        return this.mAnimationBackend.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.mAnimationBackend == null) {
            return super.getIntrinsicHeight();
        }
        return this.mAnimationBackend.getIntrinsicHeight();
    }

    public void start() {
        if (!this.mIsRunning && this.mAnimationBackend != null && this.mAnimationBackend.getFrameCount() > 1) {
            this.mIsRunning = true;
            this.mStartTimeMs = now();
            this.mLastFrameAnimationTimeMs = -1;
            invalidateSelf();
            this.mAnimationListener.onAnimationStart(this);
        }
    }

    public void stop() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            this.mStartTimeMs = 0;
            this.mLastFrameAnimationTimeMs = -1;
            unscheduleSelf(this.mInvalidateRunnable);
            this.mAnimationListener.onAnimationStop(this);
        }
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setBounds(rect);
        }
    }

    public void draw(Canvas canvas) {
        if (this.mAnimationBackend != null && this.mFrameScheduler != null) {
            long j;
            long now = now();
            if (this.mIsRunning) {
                j = (now - this.mStartTimeMs) + this.mFrameSchedulingOffsetMs;
            } else {
                j = Math.max(this.mLastFrameAnimationTimeMs, 0);
            }
            int frameNumberToRender = this.mFrameScheduler.getFrameNumberToRender(j, this.mLastFrameAnimationTimeMs);
            if (frameNumberToRender == -1) {
                frameNumberToRender = this.mAnimationBackend.getFrameCount() - 1;
                this.mAnimationListener.onAnimationStop(this);
                this.mIsRunning = false;
            } else if (frameNumberToRender == 0) {
                this.mAnimationListener.onAnimationRepeat(this);
            }
            this.mAnimationListener.onAnimationFrame(this, frameNumberToRender);
            boolean drawFrame = this.mAnimationBackend.drawFrame(this, canvas, frameNumberToRender);
            if (!drawFrame) {
                onFrameDropped();
            }
            long j2 = -1;
            long j3 = -1;
            long now2 = now();
            if (this.mIsRunning) {
                j2 = this.mFrameScheduler.getTargetRenderTimeForNextFrameMs(now2 - this.mStartTimeMs);
                if (j2 != -1) {
                    j3 = j2 + this.mFrameSchedulingDelayMs;
                    scheduleNextFrame(j3);
                }
            }
            if (this.mDrawListener != null) {
                this.mDrawListener.onDraw(this, this.mFrameScheduler, frameNumberToRender, drawFrame, this.mIsRunning, this.mStartTimeMs, j, this.mLastFrameAnimationTimeMs, now, now2, j2, j3);
            }
            this.mLastFrameAnimationTimeMs = j;
        }
    }

    public void setAlpha(int i) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setAlpha(i);
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setColorFilter(colorFilter);
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAnimationBackend(@Nullable AnimationBackend animationBackend) {
        this.mAnimationBackend = animationBackend;
        if (this.mAnimationBackend != null) {
            this.mFrameScheduler = new DropFramesFrameScheduler(this.mAnimationBackend);
            this.mAnimationBackend.setBounds(getBounds());
            if (this.mDrawableProperties != null) {
                this.mDrawableProperties.applyTo(this);
            }
        }
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(this.mAnimationBackend);
        stop();
    }

    @Nullable
    public AnimationBackend getAnimationBackend() {
        return this.mAnimationBackend;
    }

    public long getDroppedFrames() {
        return (long) this.mDroppedFrames;
    }

    public long getStartTimeMs() {
        return this.mStartTimeMs;
    }

    public boolean isInfiniteAnimation() {
        return this.mFrameScheduler != null && this.mFrameScheduler.isInfiniteAnimation();
    }

    public void jumpToFrame(int i) {
        if (this.mAnimationBackend != null && this.mFrameScheduler != null) {
            this.mLastFrameAnimationTimeMs = this.mFrameScheduler.getTargetRenderTimeMs(i);
            this.mStartTimeMs = now() - this.mLastFrameAnimationTimeMs;
            invalidateSelf();
        }
    }

    public long getLoopDurationMs() {
        int i = 0;
        if (this.mAnimationBackend == null) {
            return 0;
        }
        if (this.mFrameScheduler != null) {
            return this.mFrameScheduler.getLoopDurationMs();
        }
        int i2 = 0;
        while (i < this.mAnimationBackend.getFrameCount()) {
            i2 += this.mAnimationBackend.getFrameDurationMs(i);
            i++;
        }
        return (long) i2;
    }

    public int getFrameCount() {
        return this.mAnimationBackend == null ? 0 : this.mAnimationBackend.getFrameCount();
    }

    public int getLoopCount() {
        if (this.mAnimationBackend == null) {
            return 0;
        }
        return this.mAnimationBackend.getLoopCount();
    }

    public void setFrameSchedulingDelayMs(long j) {
        this.mFrameSchedulingDelayMs = j;
    }

    public void setFrameSchedulingOffsetMs(long j) {
        this.mFrameSchedulingOffsetMs = j;
    }

    public void setAnimationListener(@Nullable AnimationListener animationListener) {
        if (animationListener == null) {
            animationListener = NO_OP_LISTENER;
        }
        this.mAnimationListener = animationListener;
    }

    public void setDrawListener(@Nullable AnimatedDrawable2$DrawListener animatedDrawable2$DrawListener) {
        this.mDrawListener = animatedDrawable2$DrawListener;
    }

    private void scheduleNextFrame(long j) {
        scheduleSelf(this.mInvalidateRunnable, this.mStartTimeMs + j);
    }

    private void onFrameDropped() {
        this.mDroppedFrames++;
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "Dropped a frame. Count: %s", Integer.valueOf(this.mDroppedFrames));
        }
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    @Nullable
    private static FrameScheduler createSchedulerForBackendAndDelayMethod(@Nullable AnimationBackend animationBackend) {
        if (animationBackend == null) {
            return null;
        }
        return new DropFramesFrameScheduler(animationBackend);
    }

    protected boolean onLevelChange(int i) {
        if (this.mIsRunning || this.mLastFrameAnimationTimeMs == ((long) i)) {
            return false;
        }
        this.mLastFrameAnimationTimeMs = (long) i;
        invalidateSelf();
        return true;
    }

    public void dropCaches() {
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.clear();
        }
    }
}
