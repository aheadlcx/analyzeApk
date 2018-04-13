package cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class c implements EGLConfigChooser {
    private static final String a = c.class.getSimpleName();
    private int[] b;
    private boolean c;

    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        this.b = new int[1];
        int[] iArr = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12352, 4, 12338, 1, 12337, 4, 12344};
        int i = this.b[0];
        if (i <= 0) {
            iArr = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12352, 4, 12512, 1, 12513, 2, 12344};
            i = this.b[0];
            if (i <= 0) {
                iArr = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12352, 4, 12344};
                if (egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, this.b)) {
                    i = this.b[0];
                    if (i <= 0) {
                        throw new IllegalArgumentException("No configs match configSpec");
                    }
                }
                throw new IllegalArgumentException("3rd eglChooseConfig failed");
            }
            this.c = true;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        if (egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i, this.b)) {
            int i2 = 0;
            while (i2 < eGLConfigArr.length) {
                if (a(egl10, eGLDisplay, eGLConfigArr[i2], 12321, 0) == 8) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 == -1) {
                Log.w(a, "Did not find sane config, using first");
            }
            EGLConfig eGLConfig = eGLConfigArr.length > 0 ? eGLConfigArr[i2] : null;
            if (eGLConfig != null) {
                return eGLConfig;
            }
            throw new IllegalArgumentException("No config chosen");
        }
        throw new IllegalArgumentException("data eglChooseConfig failed");
    }

    private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.b)) {
            return this.b[0];
        }
        return i2;
    }

    public boolean a() {
        return this.c;
    }
}
