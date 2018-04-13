package cn.xiaochuankeji.tieba.ui.videomaker;

public class JNIHelper {
    public static native void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6);

    static {
        System.loadLibrary("MagicBeautify");
    }
}
