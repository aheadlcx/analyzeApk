package qsbk.app.video;

import android.graphics.SurfaceTexture;
import android.view.TextureView.SurfaceTextureListener;
import qsbk.app.utils.DebugUtil;

class ap implements SurfaceTextureListener {
    final /* synthetic */ VideoEditPlayView a;

    ap(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        DebugUtil.debug(VideoEditPlayView.a, "onSurfaceTextureUpdated");
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        DebugUtil.debug(VideoEditPlayView.a, "onSurfaceTextureSizeChanged");
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        DebugUtil.debug(VideoEditPlayView.a, "onSurfaceTextureDestroyed");
        this.a.stop();
        return false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        DebugUtil.debug(VideoEditPlayView.a, "onSurfaceTextureAvailable width:" + i + "  height:" + i2);
        this.a.a(surfaceTexture);
    }
}
