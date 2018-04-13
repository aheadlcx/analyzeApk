package com.facebook.fresco.animation.frame;

public interface FrameScheduler {
    public static final int FRAME_NUMBER_DONE = -1;
    public static final int NO_NEXT_TARGET_RENDER_TIME = -1;

    int getFrameNumberToRender(long j, long j2);

    long getLoopDurationMs();

    long getTargetRenderTimeForNextFrameMs(long j);

    long getTargetRenderTimeMs(int i);

    boolean isInfiniteAnimation();
}
