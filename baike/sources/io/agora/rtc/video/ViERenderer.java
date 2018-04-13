package io.agora.rtc.video;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ViERenderer {
    private static SurfaceHolder g_localRenderer;

    public static SurfaceView CreateLocalRenderer(Context context) {
        return new SurfaceView(context);
    }

    public static SurfaceHolder GetLocalRenderer() {
        return g_localRenderer;
    }

    public static void setLocalView(SurfaceView surfaceView, int i, int i2, int i3, int i4) {
        if (surfaceView == null) {
            g_localRenderer = null;
        } else {
            g_localRenderer = surfaceView.getHolder();
        }
    }
}
