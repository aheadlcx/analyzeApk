package cn.xiaochuankeji.tieba.common.medialib;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Surface;
import cn.xiaochuankeji.tieba.d.i;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@TargetApi(16)
public class d {
    private static final String[] a = new String[]{"OMX.qcom.", "OMX.Intel.", "OMX.MTK.VIDEO.DECODER.AVC", "OMX.Exynos.AVC.", "OMX.hisi.video.", "OMX.k3.video.decoder.avc"};
    private static final List<Integer> b = Arrays.asList(new Integer[]{Integer.valueOf(19), Integer.valueOf(21), Integer.valueOf(2141391872), Integer.valueOf(2141391876)});
    private MediaCodec c;
    private Surface d;
    private ByteBuffer[] e;
    private ByteBuffer[] f;
    private boolean g;

    private static class a {
        public final String a;
        public final int b;

        public a(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public static class b {
        public final int a;
        public final ByteBuffer b;

        public b(int i, ByteBuffer byteBuffer) {
            this.a = i;
            this.b = byteBuffer;
        }
    }

    public static class c {
        public final int a;
        public final BufferInfo b;

        public c(int i, BufferInfo bufferInfo) {
            this.a = i;
            this.b = bufferInfo;
        }
    }

    private static a a(String str, String[] strArr) {
        if (VERSION.SDK_INT < 19) {
            return null;
        }
        Log.d("MediaCodecVideoDecoder", "Trying to find HW decoder for mime " + str);
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfoAt.isEncoder()) {
                String name;
                for (String equals : codecInfoAt.getSupportedTypes()) {
                    if (equals.equals(str)) {
                        name = codecInfoAt.getName();
                        break;
                    }
                }
                name = null;
                if (name != null) {
                    Object obj;
                    Log.d("MediaCodecVideoDecoder", "Found candidate decoder " + name);
                    for (String equals2 : strArr) {
                        if (name.startsWith(equals2)) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        int intValue;
                        CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                        for (int toHexString : capabilitiesForType.colorFormats) {
                            Log.v("MediaCodecVideoDecoder", "   Color: 0x" + Integer.toHexString(toHexString));
                        }
                        for (Integer intValue2 : b) {
                            intValue = intValue2.intValue();
                            for (int i2 : capabilitiesForType.colorFormats) {
                                if (i2 == intValue) {
                                    Log.d("MediaCodecVideoDecoder", "Found target decoder " + name + ". Color: 0x" + Integer.toHexString(i2));
                                    return new a(name, i2);
                                }
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        Log.d("MediaCodecVideoDecoder", "No HW decoder found for mime " + str);
        return null;
    }

    public boolean a(MediaFormat mediaFormat, Surface surface) {
        a a = a("video/avc", a);
        if (a == null) {
            throw new RuntimeException("Cannot find HW decoder");
        }
        try {
            this.d = surface;
            this.c = e.a(a.a);
            if (this.c == null) {
                Log.e("MediaCodecVideoDecoder", "Can not create media decoder");
                return false;
            }
            this.c.configure(mediaFormat, surface, null, 0);
            this.c.start();
            this.e = this.c.getInputBuffers();
            this.f = this.c.getOutputBuffers();
            return true;
        } catch (Throwable e) {
            Log.e("MediaCodecVideoDecoder", "initDecode failed", e);
            return false;
        }
    }

    public void a() {
        Log.d("MediaCodecVideoDecoder", "releaseDecoder");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                try {
                    Log.d("MediaCodecVideoDecoder", "Java releaseDecoder on release thread");
                    if (this.b.c != null) {
                        this.b.c.stop();
                        this.b.c.release();
                    }
                    Log.d("MediaCodecVideoDecoder", "Java releaseDecoder on release thread done");
                } catch (Throwable e) {
                    Log.e("MediaCodecVideoDecoder", "Media decoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }, "MediaCodecVideoDecoder").start();
        if (!i.a(countDownLatch, 5000)) {
            Log.e("MediaCodecVideoDecoder", "Media decoder release timeout");
        }
        this.c = null;
        if (this.d != null) {
            this.d.release();
            this.d = null;
        }
        Log.d("MediaCodecVideoDecoder", "releaseDecoder done");
    }

    public b b() {
        try {
            ByteBuffer byteBuffer;
            int dequeueInputBuffer = this.c.dequeueInputBuffer(0);
            if (dequeueInputBuffer >= 0) {
                byteBuffer = this.e[dequeueInputBuffer];
            } else {
                byteBuffer = null;
            }
            return new b(dequeueInputBuffer, byteBuffer);
        } catch (Throwable e) {
            Log.e("MediaCodecVideoDecoder", "dequeueIntputBuffer failed", e);
            return new b(-2, null);
        }
    }

    public boolean a(int i, int i2, long j) {
        try {
            this.e[i].position(0);
            this.e[i].limit(i2);
            this.c.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (Throwable e) {
            Log.e("MediaCodecVideoDecoder", "decode failed", e);
            return false;
        }
    }

    public boolean a(int i) {
        try {
            this.e[i].position(0);
            this.e[i].limit(0);
            this.c.queueInputBuffer(i, 0, 0, -1, 4);
            return true;
        } catch (Throwable e) {
            Log.e("MediaCodecVideoDecoder", "decode failed", e);
            return false;
        }
    }

    public c c() {
        BufferInfo bufferInfo = new BufferInfo();
        while (true) {
            int dequeueOutputBuffer = this.c.dequeueOutputBuffer(bufferInfo, 0);
            switch (dequeueOutputBuffer) {
                case -3:
                    this.f = this.c.getOutputBuffers();
                    Log.d("MediaCodecVideoDecoder", "Decoder output buffers changed: " + this.f.length);
                    if (!this.g) {
                        break;
                    }
                    throw new RuntimeException("Unexpected output buffer change event.");
                case -2:
                    Log.d("MediaCodecVideoDecoder", "Decoder format changed: " + this.c.getOutputFormat().toString());
                    break;
                case -1:
                    return new c(-1, null);
                default:
                    this.g = true;
                    return new c(dequeueOutputBuffer, bufferInfo);
            }
        }
    }

    public void b(int i) {
        this.c.releaseOutputBuffer(i, true);
    }
}
