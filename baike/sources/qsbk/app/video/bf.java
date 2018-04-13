package qsbk.app.video;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;

class bf implements SurfaceTextureListener {
    final /* synthetic */ VideoPlayerView a;

    bf(VideoPlayerView videoPlayerView) {
        this.a = videoPlayerView;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.a.q = i;
        this.a.r = i2;
        this.a.s = new Surface(surfaceTexture);
        this.a.e.setSurface(this.a.s, i, i2);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.a.q = i;
        this.a.r = i2;
        this.a.e.setSurface(this.a.e.getSurface(), i, i2);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.a.e.release();
        if (this.a.s != null) {
            this.a.s.release();
            this.a.s = null;
        }
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
