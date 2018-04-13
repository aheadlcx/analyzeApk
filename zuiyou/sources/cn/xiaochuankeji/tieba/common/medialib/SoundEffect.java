package cn.xiaochuankeji.tieba.common.medialib;

import cn.xiaochuan.base.BaseApplication;
import com.getkeepsafe.relinker.b;
import java.nio.ByteBuffer;

public class SoundEffect {
    private long mNativeHandler;

    public native void initialize();

    public native void putSamples(ByteBuffer byteBuffer, float f, float f2, int i, int i2, int i3, int i4);

    public native int receiveSamples(ByteBuffer byteBuffer, int i, int i2, int i3);

    public native void release();

    static {
        b.a(BaseApplication.getAppContext().getApplicationContext(), "soundeffect");
    }
}
