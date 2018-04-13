package qsbk.app.ye.videotools.camera;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;

class b implements OnFrameAvailableListener {
    final /* synthetic */ CameraRender a;

    b(CameraRender cameraRender) {
        this.a = cameraRender;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.a.r != null) {
            this.a.r.updateTexture();
        }
    }
}
