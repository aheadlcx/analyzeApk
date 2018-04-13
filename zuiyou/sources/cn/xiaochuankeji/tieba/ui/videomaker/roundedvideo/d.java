package cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import cn.xiaochuankeji.tieba.c.a.h;
import cn.xiaochuankeji.tieba.c.c;
import cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo.RoundedVideoSurfaceView.a;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class d implements OnFrameAvailableListener, Renderer {
    private final GLSurfaceView a;
    private final boolean b;
    private a c;
    private boolean d;
    private int e;
    private SurfaceTexture f;
    private boolean g;
    private h h;
    private b i;
    private final float[] j = new float[16];

    public d(GLSurfaceView gLSurfaceView, boolean z) {
        this.a = gLSurfaceView;
        this.b = z;
        this.h = new h();
        this.i = new b();
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(float f, float f2, float f3, float f4) {
        this.i.a(f, f2, f3, f4);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        if (!this.d) {
            this.d = true;
            this.e = c.a(36197);
            this.f = new SurfaceTexture(this.e);
            this.f.setOnFrameAvailableListener(this);
            this.h.a();
            if (this.c != null) {
                this.c.a(this.f);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.h.a(i, i2);
        if (this.c != null) {
            this.c.b(i, i2);
        }
    }

    public void a() {
        if (this.d) {
            this.d = false;
            GLES20.glDeleteTextures(1, new int[]{this.e}, 0);
            this.f.release();
            this.h.g();
            this.i.a();
            if (this.c != null) {
                this.c.k();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDrawFrame(javax.microedition.khronos.opengles.GL10 r9) {
        /*
        r8 = this;
        r7 = 0;
        r2 = 0;
        monitor-enter(r8);
        r0 = r8.g;	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r8);	 Catch:{ all -> 0x004e }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r8.f;	 Catch:{ all -> 0x004e }
        r0.updateTexImage();	 Catch:{ all -> 0x004e }
        r0 = r8.f;	 Catch:{ all -> 0x004e }
        r1 = r8.j;	 Catch:{ all -> 0x004e }
        r0.getTransformMatrix(r1);	 Catch:{ all -> 0x004e }
        r0 = 0;
        r8.g = r0;	 Catch:{ all -> 0x004e }
        monitor-exit(r8);	 Catch:{ all -> 0x004e }
        android.opengl.GLES20.glClearColor(r2, r2, r2, r2);
        r0 = 16640; // 0x4100 float:2.3318E-41 double:8.2213E-320;
        r1 = r8.b;
        if (r1 == 0) goto L_0x0025;
    L_0x0022:
        r0 = 49408; // 0xc100 float:6.9235E-41 double:2.4411E-319;
    L_0x0025:
        android.opengl.GLES20.glClear(r0);
        r0 = r8.h;
        r1 = r8.e;
        r2 = cn.xiaochuankeji.tieba.c.c.a;
        r3 = cn.xiaochuankeji.tieba.c.c.c;
        r4 = r8.j;
        r5 = cn.xiaochuankeji.tieba.c.c.d;
        r1 = r0.a(r1, r2, r3, r4, r5);
        r0 = r8.i;
        r2 = cn.xiaochuankeji.tieba.c.c.a;
        r3 = r8.h;
        r5 = r3.d();
        r3 = r8.h;
        r6 = r3.e();
        r3 = r7;
        r4 = r7;
        r0.a(r1, r2, r3, r4, r5, r6);
        goto L_0x0008;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x004e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo.d.onDrawFrame(javax.microedition.khronos.opengles.GL10):void");
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this) {
            this.g = true;
        }
        this.a.requestRender();
    }
}
