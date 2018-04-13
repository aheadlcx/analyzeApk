package cn.xiaochuankeji.tieba.ui.videomaker;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.a;
import cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.c;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(17)
public class b implements Renderer {
    private a a;
    private c b;
    private SurfaceTexture c;
    private int d = -1;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private int i;
    private int j;
    private int k;
    private final a l;
    private final cn.xiaochuankeji.tieba.c.b m;

    public b(a aVar) {
        this.l = aVar;
        this.m = new cn.xiaochuankeji.tieba.c.b();
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        if (!this.g) {
            this.g = true;
            this.d = cn.xiaochuankeji.tieba.c.c.a(36197);
            this.c = new SurfaceTexture(this.d);
            this.l.a();
            if (this.a != null) {
                this.a.a(EGL14.eglGetCurrentContext(), this.c);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.h = i;
        this.i = i2;
        if (this.a != null) {
            this.a.a(i, i2);
        }
    }

    public void a() {
        if (this.g) {
            this.g = false;
            GLES20.glDeleteTextures(1, new int[]{this.d}, 0);
            this.c.release();
            this.e = 0;
            this.f = 0;
            this.l.g();
            this.m.a();
            if (this.a != null) {
                this.a.m_();
            }
        }
    }

    public void onDrawFrame(GL10 gl10) {
        this.c.updateTexImage();
        float[] fArr = new float[16];
        this.c.getTransformMatrix(fArr);
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl10.glClear(16384);
        int a = this.l.a(this.d, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.c, fArr, cn.xiaochuankeji.tieba.c.c.d);
        this.m.a(a, cn.xiaochuankeji.tieba.c.c.a, (this.h - this.j) / 2, (this.i - this.k) / 2, this.j, this.k);
        gl10.glFinish();
        if (this.b != null) {
            this.b.a(a, this.e, this.f, SystemClock.elapsedRealtime());
        }
    }

    public void a(int i, int i2) {
        if (!this.g) {
            return;
        }
        if (i != this.e || i2 != this.f) {
            this.e = i;
            this.f = i2;
            b();
            this.l.a(this.e, this.f);
        }
    }

    private void b() {
        float f = (((float) this.e) * 1.0f) / ((float) this.f);
        if (f >= (((float) this.h) * 1.0f) / ((float) this.i)) {
            this.k = this.i;
            this.j = (int) (f * ((float) this.k));
            return;
        }
        this.j = this.h;
        this.k = (int) (((float) this.j) / f);
    }
}
