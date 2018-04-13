package cn.xiaochuankeji.tieba.common.medialib;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import cn.xiaochuankeji.tieba.d.i;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

@TargetApi(18)
public class e {
    private static final String[] a = new String[]{"OMX.qcom.", "OMX.IMG.TOPAZ.VIDEO.", "OMX.MTK.VIDEO.ENCODER.AVC", "OMX.Exynos.AVC.", "OMX.hisi.video.", "OMX.k3.video.encoder.avc"};
    private static final String[] b = new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final int[] c = new int[]{2130708361};
    private final i d;
    private String e;
    private MediaFormat f;
    private MediaCodec g;
    private ByteBuffer[] h;
    private ByteBuffer i;
    private Surface j;
    private MediaMuxer k;
    private boolean l;
    private int m;
    private long n;
    private long o;
    private int p;

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
        public final boolean c;
        public final BufferInfo d;

        public b(int i, ByteBuffer byteBuffer, boolean z, BufferInfo bufferInfo) {
            this.a = i;
            this.b = byteBuffer;
            this.c = z;
            this.d = bufferInfo;
        }
    }

    public e(i iVar) {
        this.d = iVar;
    }

    static MediaCodec a(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception e) {
            return null;
        }
    }

    public void a() {
        a a = a("video/avc", a, c);
        if (a == null) {
            throw new RuntimeException("Can not find HW encoder");
        }
        this.e = a.a;
        this.f = MediaFormat.createVideoFormat("video/avc", this.d.a, this.d.b);
        this.f.setInteger("bitrate", this.d.c);
        this.f.setInteger("profile", 1);
        this.f.setInteger("bitrate-mode", 1);
        this.f.setInteger("color-format", a.b);
        this.f.setInteger("frame-rate", 30);
        this.f.setInteger("capture-rate", 30);
        this.f.setInteger("i-frame-interval", 1);
        this.f.setInteger("level", 1);
        this.f.setInteger("intra-refresh-period", 4);
    }

    public i b() {
        return this.d;
    }

    public void c() {
        this.g = a(this.e);
        this.g.configure(this.f, null, null, 1);
        this.j = this.g.createInputSurface();
        this.g.start();
        this.h = this.g.getOutputBuffers();
        Log.d("MediaCodecVideoEncoder", "Output buffers: " + this.h.length);
    }

    public Surface d() {
        return this.j;
    }

    public void b(String str) throws IOException {
        this.n = -66666;
        this.o = -33333;
        this.p = 0;
        if (this.g == null) {
            c();
        }
        this.k = new MediaMuxer(str, 0);
    }

    public void a(boolean z) {
        a(z, 0);
    }

    public void a(boolean z, long j) {
        if (this.g != null) {
            if (z) {
                this.g.signalEndOfInputStream();
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                b g = g();
                if (g.a >= 0) {
                    if (!this.l) {
                        this.m = this.k.addTrack(this.g.getOutputFormat());
                        this.k.start();
                        this.l = true;
                    }
                    try {
                        this.k.writeSampleData(this.m, g.b, g.d);
                        if (g.d.presentationTimeUs > this.o) {
                            this.n = this.o;
                            this.o = g.d.presentationTimeUs;
                            this.p++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    a(g.a);
                    if (z && g.d.flags == 4) {
                        return;
                    }
                } else if (SystemClock.elapsedRealtime() - elapsedRealtime >= j) {
                    return;
                }
            }
        }
    }

    private b g() {
        try {
            int dequeueOutputBuffer;
            ByteBuffer byteBuffer;
            boolean z;
            ByteBuffer allocateDirect;
            BufferInfo bufferInfo = new BufferInfo();
            int dequeueOutputBuffer2 = this.g.dequeueOutputBuffer(bufferInfo, 1000);
            if (dequeueOutputBuffer2 >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Log.d("MediaCodecVideoEncoder", "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                    this.i = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.h[dequeueOutputBuffer2].position(bufferInfo.offset);
                    this.h[dequeueOutputBuffer2].limit(bufferInfo.offset + bufferInfo.size);
                    this.i.put(this.h[dequeueOutputBuffer2]);
                    this.g.releaseOutputBuffer(dequeueOutputBuffer2, false);
                    dequeueOutputBuffer = this.g.dequeueOutputBuffer(bufferInfo, 1000);
                    if (dequeueOutputBuffer >= 0) {
                        byteBuffer = this.h[dequeueOutputBuffer];
                        if ((bufferInfo.flags & 2) != 0) {
                            bufferInfo.size = 0;
                        }
                        byteBuffer.position(bufferInfo.offset);
                        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        z = (bufferInfo.flags & 1) == 0;
                        if (z) {
                            Log.d("MediaCodecVideoEncoder", "Sync frame generated");
                        }
                        if (z) {
                            return new b(dequeueOutputBuffer, byteBuffer.slice(), z, bufferInfo);
                        }
                        Log.d("MediaCodecVideoEncoder", "Appending config frame of size " + this.i.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                        allocateDirect = ByteBuffer.allocateDirect(this.i.capacity() + bufferInfo.size);
                        this.i.rewind();
                        allocateDirect.put(byteBuffer);
                        allocateDirect.position(0);
                        return new b(dequeueOutputBuffer, allocateDirect, z, bufferInfo);
                    } else if (dequeueOutputBuffer == -3) {
                        this.h = this.g.getOutputBuffers();
                        return g();
                    } else if (dequeueOutputBuffer == -2) {
                        return g();
                    } else {
                        if (dequeueOutputBuffer == -1) {
                            return new b(-1, null, false, null);
                        }
                        throw new RuntimeException("dequeueOutputBuffer: " + dequeueOutputBuffer);
                    }
                }
            }
            dequeueOutputBuffer = dequeueOutputBuffer2;
            if (dequeueOutputBuffer >= 0) {
                byteBuffer = this.h[dequeueOutputBuffer];
                if ((bufferInfo.flags & 2) != 0) {
                    bufferInfo.size = 0;
                }
                byteBuffer.position(bufferInfo.offset);
                byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                if ((bufferInfo.flags & 1) == 0) {
                }
                if (z) {
                    Log.d("MediaCodecVideoEncoder", "Sync frame generated");
                }
                if (z) {
                    return new b(dequeueOutputBuffer, byteBuffer.slice(), z, bufferInfo);
                }
                Log.d("MediaCodecVideoEncoder", "Appending config frame of size " + this.i.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                allocateDirect = ByteBuffer.allocateDirect(this.i.capacity() + bufferInfo.size);
                this.i.rewind();
                allocateDirect.put(byteBuffer);
                allocateDirect.position(0);
                return new b(dequeueOutputBuffer, allocateDirect, z, bufferInfo);
            } else if (dequeueOutputBuffer == -3) {
                this.h = this.g.getOutputBuffers();
                return g();
            } else if (dequeueOutputBuffer == -2) {
                return g();
            } else {
                if (dequeueOutputBuffer == -1) {
                    return new b(-1, null, false, null);
                }
                throw new RuntimeException("dequeueOutputBuffer: " + dequeueOutputBuffer);
            }
        } catch (Throwable e) {
            Log.e("MediaCodecVideoEncoder", "dequeueOutputBuffer failed", e);
            return new b(-1, null, false, null);
        }
    }

    private boolean a(int i) {
        try {
            this.g.releaseOutputBuffer(i, false);
            return true;
        } catch (Throwable e) {
            Log.e("MediaCodecVideoEncoder", "releaseOutputBuffer failed", e);
            return false;
        }
    }

    public k e() {
        h();
        if (this.k != null) {
            try {
                if (this.l) {
                    this.k.stop();
                }
                this.k.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.k = null;
        }
        this.l = false;
        this.m = -1;
        return new k((int) (((2 * this.o) - this.n) / 1000), this.p);
    }

    public void f() {
        h();
    }

    private void h() {
        Log.d("MediaCodecVideoEncoder", "Java releaseEncoder");
        if (this.g != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    try {
                        Log.d("MediaCodecVideoEncoder", "Java releaseEncoder on release thread");
                        if (this.b.g != null) {
                            this.b.g.stop();
                            this.b.g.release();
                        }
                        Log.d("MediaCodecVideoEncoder", "Java releaseEncoder on release thread done");
                    } catch (Throwable e) {
                        Log.e("MediaCodecVideoEncoder", "Media encoder release failed", e);
                    }
                    countDownLatch.countDown();
                }
            }, "MediaCodecVideoEncoder").start();
            if (!i.a(countDownLatch, 5000)) {
                Log.e("MediaCodecVideoEncoder", "Media encoder release timeout");
            }
            this.g = null;
            if (this.j != null) {
                this.j.release();
                this.j = null;
            }
            Log.d("MediaCodecVideoEncoder", "Java releaseEncoder done");
        }
    }

    private static a a(String str, String[] strArr, int[] iArr) {
        if (Arrays.asList(b).contains(Build.MODEL)) {
            Log.w("MediaCodecVideoEncoder", "Model: " + Build.MODEL + " has black listed H.264 encoder.");
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            System.out.println("codec name:" + codecInfoAt.getName());
            if (codecInfoAt.isEncoder()) {
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
                    Log.v("MediaCodecVideoEncoder", "Found candidate encoder " + name);
                    for (String equals2 : strArr) {
                        if (name.startsWith(equals2)) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                        for (int toHexString : capabilitiesForType.colorFormats) {
                            Log.v("MediaCodecVideoEncoder", "   Color: 0x" + Integer.toHexString(toHexString));
                        }
                        for (int toHexString2 : iArr) {
                            for (int i2 : capabilitiesForType.colorFormats) {
                                if (i2 == toHexString2) {
                                    Log.d("MediaCodecVideoEncoder", "Found target encoder for mime " + str + " : " + name + ". Color: 0x" + Integer.toHexString(i2));
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
        return null;
    }
}
