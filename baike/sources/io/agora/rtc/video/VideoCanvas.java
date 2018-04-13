package io.agora.rtc.video;

import android.view.SurfaceView;

public class VideoCanvas {
    public static final int RENDER_MODE_ADAPTIVE = 3;
    public static final int RENDER_MODE_FIT = 2;
    public static final int RENDER_MODE_HIDDEN = 1;
    public int renderMode;
    public int uid;
    public SurfaceView view;

    public VideoCanvas(SurfaceView surfaceView) {
        this.view = surfaceView;
        this.renderMode = 1;
    }

    public VideoCanvas(SurfaceView surfaceView, int i, int i2) {
        this.view = surfaceView;
        this.renderMode = i;
        this.uid = i2;
    }
}
