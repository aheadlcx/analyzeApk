package qsbk.app.ye.videotools.camera;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.opengl.GLES20;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.video.VideoEditActivity;
import qsbk.app.ye.videotools.camera.CameraFilter.CameraFilterTextureListener;
import qsbk.app.ye.videotools.filter.VideoFilter;

public class CameraRender implements PreviewCallback, SurfaceTextureListener, CameraFilterTextureListener {
    private SurfaceTexture a = null;
    private TextureView b = null;
    private VideoFrameSink c = null;
    private Object d = new Object();
    private int e = VideoEditActivity.MAX_VIDEO_WIDTH;
    private int f = ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j = 0;
    private long k = 0;
    private long l = 0;
    private long m = 0;
    private long n = 0;
    private int o = 0;
    private boolean p = false;
    private boolean q = false;
    private CameraFilter r = null;
    private int s = -1;
    private int t = 0;
    private int u = 0;
    private boolean v = false;
    private boolean w = false;
    private Object x = new Object();
    private Object y = new Object();
    private SurfaceListener z = null;

    public interface SurfaceListener {
        void onSurfaceTextureAvailable();

        void onSurfaceTextureDestroyed();
    }

    public CameraRender(SurfaceListener surfaceListener) {
        this.z = surfaceListener;
    }

    public void setTextureView(TextureView textureView) {
        setTextureView(textureView, false, 0, 0);
    }

    public void setTextureView(TextureView textureView, boolean z, int i, int i2) {
        this.b = textureView;
        this.v = z;
        this.t = i;
        this.u = i2;
    }

    public SurfaceTexture getSurfaceTexture() {
        SurfaceTexture surfaceTexture;
        synchronized (this.x) {
            surfaceTexture = this.a;
        }
        return surfaceTexture;
    }

    public PreviewCallback getPreviewCallback() {
        if (this.v && this.w) {
            return null;
        }
        return this;
    }

    public void setSink(VideoFrameSink videoFrameSink) {
        synchronized (this.d) {
            this.c = videoFrameSink;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = 0;
        }
    }

    public void setFilter(VideoFilter videoFilter) {
        setFilter(videoFilter, true);
    }

    public void setFilter(VideoFilter videoFilter, boolean z) {
        if (this.r != null) {
            this.r.setFilter(videoFilter, z);
        }
    }

    public int getImageWidth() {
        if (this.o % 180 != 0) {
            return this.f;
        }
        return this.e;
    }

    public int getImageHeight() {
        if (this.o % 180 != 0) {
            return this.e;
        }
        return this.f;
    }

    public int getSurfaceTextureWidth() {
        return this.g;
    }

    public int getSurfaceTextureHeight() {
        return this.h;
    }

    public void setImageSize(int i, int i2) {
        float f = 1.0f;
        this.e = i;
        this.f = i2;
        if (this.b != null && this.g > 0 && this.r == null) {
            float f2;
            if (this.g > this.h) {
                f2 = ((float) this.g) / ((float) this.h);
            } else {
                f2 = ((float) this.h) / ((float) this.g);
            }
            float f3 = ((float) this.e) / ((float) this.f);
            Object obj = this.o % 180 != 0 ? 1 : null;
            if (obj != null && f3 > r0) {
                f2 = ((float) ((int) ((((float) this.e) / ((float) this.f)) * ((float) this.g)))) / ((float) this.h);
            } else if (obj != null && f3 < r0) {
                f = ((float) ((int) (((float) this.h) / (((float) this.e) / ((float) this.f))))) / ((float) this.g);
                f2 = 1.0f;
            } else if (obj == null && f3 < r0) {
                f2 = ((float) ((int) (((float) this.g) / (((float) this.e) / ((float) this.f))))) / ((float) this.h);
            } else if (obj != null || f3 <= r0) {
                f2 = 1.0f;
            } else {
                f = ((float) ((int) ((((float) this.e) / ((float) this.f)) * ((float) this.g)))) / ((float) this.g);
                f2 = 1.0f;
            }
            Matrix matrix = new Matrix();
            matrix.setScale(f, f2);
            this.b.setTransform(matrix);
        }
        if (this.r != null) {
            this.r.setImageSize(this.e, this.f);
        }
    }

    public void setRotation(int i, boolean z, boolean z2) {
        this.o = i;
        this.p = z;
        this.q = z2;
    }

    public void setFpsRange(int i, int i2) {
        if (i2 != 0) {
            this.i = 1000000 / i2;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.g = i;
        this.h = i2;
        synchronized (this.x) {
            if (this.v) {
                this.r = new CameraFilter(new Surface(surfaceTexture), this, i, i2, this.e, this.f, this.t, this.u);
            } else {
                this.a = surfaceTexture;
            }
        }
        synchronized (this.y) {
            if (!(this.v || this.z == null)) {
                this.z.onSurfaceTextureAvailable();
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.z != null) {
            this.z.onSurfaceTextureDestroyed();
        }
        if (this.r != null) {
            this.r.release();
            this.r = null;
        }
        synchronized (this.x) {
            if (this.a != null) {
                this.a.release();
                this.a = null;
            }
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.g = i;
        this.h = i2;
        if (this.r != null) {
            this.r.resetSize(i, i2);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        synchronized (this.d) {
            if (!(this.c == null || this.a == null)) {
                long j;
                long timestamp = this.a.getTimestamp();
                int i = this.i;
                long j2 = this.j;
                if (this.m == 0) {
                    this.m = timestamp;
                    this.n = timestamp;
                    j = j2;
                } else {
                    if (this.n != timestamp) {
                        j2 = (timestamp - this.m) / 1000000;
                        this.j = j2;
                        this.n = timestamp;
                    }
                    j = j2;
                }
                this.j += (long) i;
                if (!this.c.encodeVideo(bArr, j - this.l, i, this.e, this.f, this.o, this.q)) {
                    this.l += j - this.k;
                }
                this.k = j;
            }
        }
        camera.addCallbackBuffer(bArr);
    }

    public void onSurfaceTextureCreate(boolean z) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.s = iArr[0];
        this.w = z;
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        this.a = new SurfaceTexture(this.s);
        this.a.setOnFrameAvailableListener(new b(this));
        synchronized (this.y) {
            if (this.v && this.z != null) {
                this.z.onSurfaceTextureAvailable();
            }
        }
    }

    public int onSurfaceTextureUpdate() {
        if (this.a != null) {
            try {
                this.a.updateTexImage();
            } catch (Exception e) {
            }
            float[] fArr = new float[16];
            this.a.getTransformMatrix(fArr);
            if (this.r != null) {
                this.r.setMatrix(fArr);
            }
        }
        return this.s;
    }

    public int onProcessTexture(int i) {
        return i;
    }

    public void onSurfaceTextureFrame(long j) {
        synchronized (this.d) {
            if (!(this.c == null || this.a == null)) {
                long j2;
                long timestamp = this.a.getTimestamp();
                int i = this.i;
                long j3 = this.j;
                if (this.m == 0) {
                    this.m = timestamp;
                    this.n = timestamp;
                    j2 = j3;
                } else {
                    if (this.n != timestamp) {
                        j3 = (timestamp - this.m) / 1000000;
                        this.j = j3;
                        this.n = timestamp;
                    }
                    j2 = j3;
                }
                this.j += (long) i;
                if (!this.c.encodeVideo(j, j2 - this.l, i, this.t, this.u, 0, true)) {
                    this.l += j2 - this.k;
                }
                this.k = j2;
            }
        }
    }
}
