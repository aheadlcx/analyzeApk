package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

public class cb {
    Renderer a;
    int b;
    int c;
    Bitmap d;
    EGL10 e = ((EGL10) EGLContext.getEGL());
    EGLDisplay f = this.e.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
    EGLConfig[] g;
    EGLConfig h;
    EGLContext i;
    EGLSurface j;
    GL10 k;
    String l;

    public cb(int i, int i2) {
        this.b = i;
        this.c = i2;
        r1 = new int[2];
        int[] iArr = new int[]{12375, this.b, 12374, this.c, 12344};
        this.e.eglInitialize(this.f, r1);
        this.h = c();
        this.i = this.e.eglCreateContext(this.f, this.h, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        this.j = this.e.eglCreatePbufferSurface(this.f, this.h, iArr);
        this.e.eglMakeCurrent(this.f, this.j, this.j, this.i);
        this.k = (GL10) this.i.getGL();
        this.l = Thread.currentThread().getName();
    }

    public void a(Renderer renderer) {
        this.a = renderer;
        if (Thread.currentThread().getName().equals(this.l)) {
            this.a.onSurfaceCreated(this.k, this.h);
            this.a.onSurfaceChanged(this.k, this.b, this.c);
            return;
        }
        Log.e("PixelBuffer", "setRenderer: This thread does not own the OpenGL context.");
    }

    public Bitmap a() {
        if (this.a == null) {
            Log.e("PixelBuffer", "getBitmap: Renderer was not set.");
            return null;
        } else if (Thread.currentThread().getName().equals(this.l)) {
            this.a.onDrawFrame(this.k);
            this.a.onDrawFrame(this.k);
            d();
            return this.d;
        } else {
            Log.e("PixelBuffer", "getBitmap: This thread does not own the OpenGL context.");
            return null;
        }
    }

    public void b() {
        this.a.onDrawFrame(this.k);
        this.a.onDrawFrame(this.k);
        this.e.eglMakeCurrent(this.f, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.e.eglDestroySurface(this.f, this.j);
        this.e.eglDestroyContext(this.f, this.i);
        this.e.eglTerminate(this.f);
    }

    private EGLConfig c() {
        int[] iArr = new int[]{12325, 0, 12326, 0, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344};
        int[] iArr2 = new int[1];
        this.e.eglChooseConfig(this.f, iArr, null, 0, iArr2);
        int i = iArr2[0];
        this.g = new EGLConfig[i];
        this.e.eglChooseConfig(this.f, iArr, this.g, i, iArr2);
        return this.g[0];
    }

    private void d() {
        int[] iArr = new int[(this.b * this.c)];
        if (this.b * this.c >= 0) {
            Buffer allocate;
            try {
                allocate = IntBuffer.allocate(this.b * this.c);
            } catch (OutOfMemoryError e) {
                allocate = null;
            }
            if (allocate != null) {
                this.k.glReadPixels(0, 0, this.b, this.c, 6408, 5121, allocate);
                int[] array = allocate.array();
                for (int i = 0; i < this.c; i++) {
                    for (int i2 = 0; i2 < this.b; i2++) {
                        iArr[(((this.c - i) - 1) * this.b) + i2] = array[(this.b * i) + i2];
                    }
                }
                try {
                    this.d = Bitmap.createBitmap(this.b, this.c, Config.ARGB_8888);
                } catch (OutOfMemoryError e2) {
                    try {
                        this.d = Bitmap.createBitmap(this.b, this.c, Config.ARGB_4444);
                    } catch (OutOfMemoryError e3) {
                        this.d = null;
                    }
                }
                if (this.d != null) {
                    this.d.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
                }
            }
        }
    }
}
