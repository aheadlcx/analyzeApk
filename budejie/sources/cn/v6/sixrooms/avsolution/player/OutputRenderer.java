package cn.v6.sixrooms.avsolution.player;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Surface;

public abstract class OutputRenderer extends GLSurfaceView {
    public abstract long getJNI();

    public abstract Surface getSurface();

    public abstract void release();

    public abstract int rendererFrame(boolean z);

    public abstract int videoResize(int i, int i2, double d);

    public OutputRenderer(Context context) {
        super(context);
    }
}
