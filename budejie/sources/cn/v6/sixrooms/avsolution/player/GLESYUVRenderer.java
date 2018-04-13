package cn.v6.sixrooms.avsolution.player;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.Surface;
import cn.v6.sixrooms.utils.LogUtils;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

public class GLESYUVRenderer extends OutputRenderer implements Renderer {
    private static final boolean DEBUG = false;
    private static String TAG = "GLESYUVRenderer";
    private int mHeight;
    private long mJNI = 0;
    private int mRotation = 0;
    private int mWidth;
    private int screenHeight;
    private int screenWidth;
    private double videoAspectRatio = 0.0d;
    private int videoHeight;
    private int videoWidth;
    private int viewHeight = 0;
    private int viewWidth = 0;
    private int xCoord = 0;
    private int yCoord = 0;

    private static class ConfigChooser implements EGLConfigChooser {
        private static int EGL_OPENGL_ES2_BIT = 4;
        private static int[] s_configAttribs2 = new int[]{12324, 4, 12323, 4, 12322, 4, 12352, EGL_OPENGL_ES2_BIT, 12344};
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue = new int[1];

        public ConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mRedSize = i;
            this.mGreenSize = i2;
            this.mBlueSize = i3;
            this.mAlphaSize = i4;
            this.mDepthSize = i5;
            this.mStencilSize = i6;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            egl10.eglChooseConfig(eGLDisplay, s_configAttribs2, null, 0, iArr);
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            egl10.eglChooseConfig(eGLDisplay, s_configAttribs2, eGLConfigArr, i, iArr);
            return chooseConfig(egl10, eGLDisplay, eGLConfigArr);
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int findConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int findConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (findConfigAttrib >= this.mDepthSize && findConfigAttrib2 >= this.mStencilSize) {
                    findConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int findConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int findConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    findConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (findConfigAttrib == this.mRedSize && findConfigAttrib3 == this.mGreenSize && findConfigAttrib4 == this.mBlueSize && findConfigAttrib2 == this.mAlphaSize) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.mValue)) {
                return this.mValue[0];
            }
            return i2;
        }

        private void printConfigs(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            Log.w(GLESYUVRenderer.TAG, String.format("%d configurations", new Object[]{Integer.valueOf(eGLConfigArr.length)}));
            for (EGLConfig printConfig : eGLConfigArr) {
                Log.w(GLESYUVRenderer.TAG, String.format("Configuration %d:\n", new Object[]{Integer.valueOf(r0)}));
                printConfig(egl10, eGLDisplay, printConfig);
            }
        }

        private void printConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{12320, 12321, 12322, 12323, 12324, 12325, 12326, 12327, 12328, 12329, 12330, 12331, 12332, 12333, 12334, 12335, 12336, 12337, 12338, 12339, 12340, 12343, 12342, 12341, 12345, 12346, 12347, 12348, 12349, 12350, 12351, 12352, 12354};
            String[] strArr = new String[]{"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT"};
            int[] iArr2 = new int[1];
            for (int i = 0; i < 33; i++) {
                int i2 = iArr[i];
                Object obj = strArr[i];
                if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, iArr2)) {
                    Log.w(GLESYUVRenderer.TAG, String.format("  %s: %d\n", new Object[]{obj, Integer.valueOf(iArr2[0])}));
                } else {
                    do {
                    } while (egl10.eglGetError() != 12288);
                }
            }
        }
    }

    private static class ContextFactory implements EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 12440;

        private ContextFactory() {
        }

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            Log.w(GLESYUVRenderer.TAG, "creating OpenGL ES 2.0 context");
            GLESYUVRenderer.checkEglError("Before eglCreateContext", egl10);
            EGLContext eglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            GLESYUVRenderer.checkEglError("After eglCreateContext", egl10);
            return eglCreateContext;
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    private native long create();

    private native int drawFrame(long j);

    private native void release(long j);

    private native int setup(int i, int i2, long j);

    static {
        try {
            System.loadLibrary("GLESYUVRenderer");
        } catch (Exception e) {
            Log.w(TAG, "Can't load library libGLESYUVRenderer.so");
        }
    }

    public static boolean UseOpenGL2(Object obj) {
        return GLESYUVRenderer.class.isInstance(obj);
    }

    public GLESYUVRenderer(Context context) {
        super(context);
        init(false, 0, 0);
        this.mJNI = create();
    }

    public GLESYUVRenderer(Context context, boolean z, int i, int i2) {
        super(context);
        init(z, i, i2);
        this.mJNI = create();
    }

    public void drawFrame() {
        super.requestRender();
    }

    private void init(boolean z, int i, int i2) {
        if (z) {
            getHolder().setFormat(-3);
        }
        setEGLContextFactory(new ContextFactory());
        setEGLConfigChooser(z ? new ConfigChooser(8, 8, 8, 8, i, i2) : new ConfigChooser(5, 6, 5, 0, i, i2));
        setRenderer(this);
        setRenderMode(0);
    }

    private static void checkEglError(String str, EGL10 egl10) {
        while (egl10.eglGetError() != 12288) {
            Log.e(TAG, String.format("%s: EGL error: 0x%x", new Object[]{str, Integer.valueOf(r0)}));
        }
    }

    public static boolean IsSupported(Context context) {
        if (((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072) {
            return true;
        }
        return false;
    }

    public void onDrawFrame(GL10 gl10) {
        drawFrame(this.mJNI);
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        setup(i, i2, this.mJNI);
        screenResize(i, i2);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }

    private void resize() {
        double d;
        double d2 = ((double) this.screenWidth) / ((double) this.screenHeight);
        if (this.mRotation == 90 || this.mRotation == 270) {
            d = ((double) this.videoHeight) / ((double) this.videoWidth);
        } else {
            d = ((double) this.videoWidth) / ((double) this.videoHeight);
        }
        if (this.videoAspectRatio > 0.0d) {
            d *= this.videoAspectRatio;
        }
        if (d2 > d) {
            this.mWidth = (int) (d * ((double) this.screenHeight));
            this.mHeight = this.screenHeight;
            this.xCoord = (this.screenWidth - this.mWidth) / 2;
            this.yCoord = 0;
        } else if (d2 < d) {
            this.mWidth = this.screenWidth;
            this.mHeight = (int) (((double) this.screenWidth) / d);
            this.xCoord = 0;
            this.yCoord = (this.screenHeight - this.mHeight) / 2;
        } else {
            this.mWidth = this.screenWidth;
            this.mHeight = this.screenHeight;
            this.xCoord = 0;
            this.yCoord = 0;
        }
    }

    public void screenResize(final int i, final int i2) {
        queueEvent(new Runnable() {
            public void run() {
                GLESYUVRenderer.this.screenWidth = i;
                GLESYUVRenderer.this.screenHeight = i2;
                if (GLESYUVRenderer.this.videoWidth * GLESYUVRenderer.this.videoHeight == 0) {
                    GLES20.glViewport(0, 0, i, i2);
                    return;
                }
                GLESYUVRenderer.this.resize();
                GLES20.glViewport(GLESYUVRenderer.this.xCoord, GLESYUVRenderer.this.yCoord, GLESYUVRenderer.this.mWidth, GLESYUVRenderer.this.mHeight);
            }
        });
    }

    public void videoResize(int i, int i2) {
        queueEvent(new Runnable() {
            public void run() {
            }
        });
    }

    public Surface getSurface() {
        return null;
    }

    public int rendererFrame(boolean z) {
        drawFrame();
        return 0;
    }

    public int videoResize(int i, int i2, double d) {
        LogUtils.i(TAG, "Video aspect ratio == " + d);
        this.videoAspectRatio = d;
        videoResize(i, i2);
        return 0;
    }

    public long getJNI() {
        return this.mJNI;
    }

    public void release() {
        release(this.mJNI);
        this.mJNI = 0;
        try {
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
