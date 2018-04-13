package qsbk.app.live.ui;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;

class dx implements SurfaceTextureListener {
    final /* synthetic */ LivePullActivity a;

    dx(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.a.bw = new Surface(surfaceTexture);
        this.a.bx = i;
        this.a.by = i2;
        this.a.j();
        this.a.ac();
        this.a.J();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.a.bw != null) {
            this.a.bw.release();
            this.a.bw = null;
        }
        this.a.aX = true;
        this.a.ad();
        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
