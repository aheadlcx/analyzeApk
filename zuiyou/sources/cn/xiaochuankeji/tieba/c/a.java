package cn.xiaochuankeji.tieba.c;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.util.Log;
import android.view.Surface;

@TargetApi(18)
public final class a {
    public static final int[] a = new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12339, 1, 12344};
    public static final int[] b = new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344};
    private EGLContext c;
    private EGLConfig d;
    private EGLDisplay e = g();
    private EGLSurface f = EGL14.EGL_NO_SURFACE;

    public static a a(EGLContext eGLContext, int[] iArr) {
        return new a(eGLContext, iArr);
    }

    private a(EGLContext eGLContext, int[] iArr) {
        this.d = a(this.e, iArr);
        this.c = a(eGLContext, this.e, this.d);
    }

    public void a(Surface surface) {
        a((Object) surface);
    }

    private void a(Object obj) {
        if ((obj instanceof Surface) || (obj instanceof SurfaceTexture)) {
            f();
            if (this.f != EGL14.EGL_NO_SURFACE) {
                Log.e("EglCore", "Already has an EGLSurface");
                return;
            }
            this.f = EGL14.eglCreateWindowSurface(this.e, this.d, obj, new int[]{12344}, 0);
            if (this.f == EGL14.EGL_NO_SURFACE) {
                Log.e("EglCore", "Failed to create window surface");
                return;
            }
            return;
        }
        throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
    }

    public void a(int i, int i2) {
        f();
        if (this.f != EGL14.EGL_NO_SURFACE) {
            Log.e("EglCore", "Already has an EGLSurface");
            return;
        }
        this.f = EGL14.eglCreatePbufferSurface(this.e, this.d, new int[]{12375, i, 12374, i2, 12344}, 0);
        if (this.f == EGL14.EGL_NO_SURFACE) {
            Log.e("EglCore", "Failed to create pixel buffer surface");
        }
    }

    public void a() {
        if (this.f != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.e, this.f);
            this.f = EGL14.EGL_NO_SURFACE;
        }
    }

    private void f() {
        if (this.e == EGL14.EGL_NO_DISPLAY || this.c == EGL14.EGL_NO_CONTEXT || this.d == null) {
            Log.e("EglCore", "This object has been released");
        }
    }

    public void b() {
        f();
        a();
        d();
        EGL14.eglDestroyContext(this.e, this.c);
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.e);
        this.c = EGL14.EGL_NO_CONTEXT;
        this.e = EGL14.EGL_NO_DISPLAY;
        this.d = null;
    }

    public void c() {
        f();
        if (this.f == EGL14.EGL_NO_SURFACE) {
            Log.e("EglCore", "No EGLSurface - can't make current");
        } else if (!EGL14.eglMakeCurrent(this.e, this.f, this.f, this.c)) {
            Log.e("EglCore", "eglMakeCurrent failed");
        }
    }

    public void d() {
        if (!EGL14.eglMakeCurrent(this.e, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
            Log.e("EglCore", "eglMakeCurrent failed");
        }
    }

    public void e() {
        f();
        if (this.f == EGL14.EGL_NO_SURFACE) {
            Log.e("EglCore", "No EGLSurface - can't swap buffers");
        } else {
            EGL14.eglSwapBuffers(this.e, this.f);
        }
    }

    public void a(long j) {
        f();
        if (this.f == EGL14.EGL_NO_SURFACE) {
            Log.e("EglCore", "No EGLSurface - can't swap buffers");
            return;
        }
        EGLExt.eglPresentationTimeANDROID(this.e, this.f, j);
        EGL14.eglSwapBuffers(this.e, this.f);
    }

    private static EGLDisplay g() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            Log.e("EglCore", "Unable to get EGL14 display");
            return null;
        }
        int[] iArr = new int[2];
        if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
            return eglGetDisplay;
        }
        Log.e("EglCore", "Unable to initialize EGL14");
        return null;
    }

    private static EGLConfig a(EGLDisplay eGLDisplay, int[] iArr) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        Log.e("EglCore", "Unable to find any matching EGL config");
        return null;
    }

    private static EGLContext a(EGLContext eGLContext, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        int[] iArr = new int[]{12440, 2, 12344};
        if (eGLContext == null) {
            eGLContext = EGL14.EGL_NO_CONTEXT;
        }
        EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr, 0);
        if (eglCreateContext != EGL14.EGL_NO_CONTEXT) {
            return eglCreateContext;
        }
        Log.e("EglCore", "Failed to create EGL context");
        return null;
    }
}
