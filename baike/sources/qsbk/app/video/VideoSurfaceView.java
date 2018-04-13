package qsbk.app.video;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class VideoSurfaceView extends SurfaceView implements Callback {
    private VideoCamera a;
    private SurfaceHolder b = getHolder();
    private SurfaceCallback c;

    public interface SurfaceCallback {
        void handleSurfaceChanged();
    }

    public VideoSurfaceView(Context context, VideoCamera videoCamera, SurfaceCallback surfaceCallback) {
        super(context);
        this.a = videoCamera;
        this.c = surfaceCallback;
        this.b.setType(3);
        this.b.addCallback(this);
        this.b.setFormat(-2);
        setFocusable(false);
        setEnabled(false);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.stopPreview();
        this.a.setPreviewDisplay(this.b);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.c != null) {
            this.c.handleSurfaceChanged();
        }
        this.a.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.stopPreview();
        this.b.addCallback(null);
    }
}
