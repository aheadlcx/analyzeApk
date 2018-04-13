package io.agora.rtc.video;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Surface;
import com.baidu.mobstat.Config;
import io.agora.rtc.internal.Logging;
import io.agora.rtc.video.EglBase14.Context;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@TargetApi(19)
public class MediaCodecVideoEncoder {
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS = new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static MediaCodecVideoEncoder runningInstance = null;
    private static final int[] supportedColorList = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final String[] supportedH264HwCodecPrefixes = new String[]{"OMX.qcom."};
    private static final int[] supportedSurfaceColorList = new int[]{2130708361};
    private static final String[] supportedVp8HwCodecPrefixes = new String[]{"OMX.qcom.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = new String[]{"OMX.qcom."};
    private int adjusted_kbps;
    private int colorFormat;
    private ByteBuffer configData = null;
    private GlRectDrawer drawer;
    private EglBase eglBase;
    private FileOutputStream fos = null;
    private int height;
    private Surface inputSurface;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private VideoCodecType type;
    private int width;

    private static class EncoderProperties {
        public final String codecName;
        public final int colorFormat;
        public final boolean supportedList;

        public EncoderProperties(String str, int i, boolean z) {
            this.codecName = str;
            this.colorFormat = i;
            this.supportedList = z;
        }
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;
        public final int size;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j, int i2) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
            this.size = i2;
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes, supportedColorList) == null) ? false : true;
    }

    public static boolean isVp9HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes, supportedColorList) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        try {
            return (hwEncoderDisabledTypes.contains(H264_MIME_TYPE) || findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, supportedColorList) == null) ? false : true;
        } catch (Exception e) {
            Logging.e(TAG, "isH264HwSupported failed!");
            return false;
        }
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes, supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes, supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        try {
            return (hwEncoderDisabledTypes.contains(H264_MIME_TYPE) || findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, supportedSurfaceColorList) == null) ? false : true;
        } catch (Exception e) {
            Logging.e(TAG, "isH264HwSupportedUsingTextures failed!");
            return false;
        }
    }

    private static EncoderProperties findHwEncoder(String str, String[] strArr, int[] iArr) {
        try {
            return do_findHwEncoder(str, strArr, iArr);
        } catch (Exception e) {
            return null;
        }
    }

    private static EncoderProperties do_findHwEncoder(String str, String[] strArr, int[] iArr) {
        if (VERSION.SDK_INT < 19) {
            return null;
        }
        Object obj = iArr[0] == 2130708361 ? 1 : null;
        if (str.equals(H264_MIME_TYPE) && Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            Logging.w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
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
                    boolean z;
                    Logging.i(TAG, "Found candidate encoder " + name);
                    for (String equals2 : strArr) {
                        if (name.startsWith(equals2)) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    if (z || obj != null) {
                        CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                        for (int toHexString : capabilitiesForType.colorFormats) {
                            Logging.d(TAG, "   Color: 0x" + Integer.toHexString(toHexString));
                        }
                        for (int toHexString2 : iArr) {
                            for (int i2 : capabilitiesForType.colorFormats) {
                                if (i2 == toHexString2) {
                                    Logging.d(TAG, "Found target encoder for mime " + str + " : " + name + ". Color: 0x" + Integer.toHexString(i2));
                                    return new EncoderProperties(name, i2, z);
                                }
                            }
                        }
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private void checkOnMediaCodecThread() {
    }

    public static void printStackTrace() {
        if (runningInstance != null && runningInstance.mediaCodecThread != null) {
            StackTraceElement[] stackTrace = runningInstance.mediaCodecThread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception e) {
            return null;
        }
    }

    boolean initEncode(int i, int i2, int i3, int i4, int i5, int i6, int i7, EGLContext eGLContext) {
        try {
            if (createEncoder(i, i2, i3, i4, i5, i6, i7, eGLContext != null)) {
                if (eGLContext != null) {
                    this.eglBase = new EglBase14(new Context(eGLContext), EglBase.CONFIG_RECORDABLE);
                    this.inputSurface = this.mediaCodec.createInputSurface();
                    this.eglBase.createSurface(this.inputSurface);
                    this.drawer = new GlRectDrawer();
                }
                this.mediaCodec.start();
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
                return true;
            }
            Logging.e(TAG, "failed to create hardware encoder!!");
            return false;
        } catch (Throwable e) {
            Logging.e(TAG, "failed to create hardware encoder,", e);
            return false;
        }
    }

    boolean initEncode(int i, int i2, int i3, int i4, int i5, int i6, int i7, javax.microedition.khronos.egl.EGLContext eGLContext) {
        try {
            if (createEncoder(i, i2, i3, i4, i5, i6, i7, eGLContext != null)) {
                if (eGLContext != null) {
                    this.eglBase = new EglBase10(new EglBase10.Context(eGLContext), EglBase.CONFIG_RECORDABLE);
                    this.inputSurface = this.mediaCodec.createInputSurface();
                    this.eglBase.createSurface(this.inputSurface);
                    this.drawer = new GlRectDrawer();
                }
                this.mediaCodec.start();
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                Logging.d(TAG, "Output buffers: " + this.outputBuffers.length);
                return true;
            }
            Logging.e(TAG, "failed to create hardware encoder!!");
            return false;
        } catch (Throwable e) {
            Logging.e(TAG, "failed to create hardware encoder,", e);
            return false;
        }
    }

    private boolean createEncoder(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) throws RuntimeException {
        Logging.d(TAG, "Java initEncode: " + this.type + " : " + i2 + " x " + i3 + ". @ " + i4 + " kbps. Fps: " + i5 + " key interval: " + i6 + ". Encode from texture : " + z);
        this.width = i2;
        this.height = i3;
        this.adjusted_kbps = i4;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        EncoderProperties encoderProperties = null;
        String str = null;
        if (i5 < 1) {
            i5 = 1;
        }
        int i8 = (i6 + 1) / i5;
        if (i8 < 2) {
            i8 = 2;
        }
        VideoCodecType videoCodecType = VideoCodecType.values()[i];
        String str2;
        if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
            int[] iArr;
            String str3 = VP8_MIME_TYPE;
            String str4 = VP8_MIME_TYPE;
            String[] strArr = supportedVp8HwCodecPrefixes;
            if (z) {
                iArr = supportedSurfaceColorList;
            } else {
                iArr = supportedColorList;
            }
            str2 = str3;
            encoderProperties = findHwEncoder(str4, strArr, iArr);
            str = str2;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
            str2 = VP9_MIME_TYPE;
            encoderProperties = findHwEncoder(VP9_MIME_TYPE, supportedH264HwCodecPrefixes, z ? supportedSurfaceColorList : supportedColorList);
            str = str2;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
            str2 = H264_MIME_TYPE;
            encoderProperties = findHwEncoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes, z ? supportedSurfaceColorList : supportedColorList);
            str = str2;
        }
        if (encoderProperties == null) {
            throw new RuntimeException("Can not find HW encoder for " + videoCodecType);
        }
        runningInstance = this;
        this.colorFormat = encoderProperties.colorFormat;
        Logging.d(TAG, "Color format: " + this.colorFormat);
        this.mediaCodecThread = Thread.currentThread();
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i2, i3);
        if (!encoderProperties.supportedList) {
            i7 = 66;
        }
        if (VERSION.SDK_INT > 19 && r16 == 100) {
            createVideoFormat.setInteger("profile", 8);
            createVideoFormat.setInteger("level", 512);
        }
        createVideoFormat.setInteger("bitrate", i4 * 1000);
        createVideoFormat.setInteger("color-format", encoderProperties.colorFormat);
        createVideoFormat.setInteger("frame-rate", i5);
        createVideoFormat.setInteger("i-frame-interval", i8);
        Logging.d(TAG, "  Format: " + createVideoFormat);
        this.mediaCodec = createByCodecName(encoderProperties.codecName);
        this.type = videoCodecType;
        if (this.mediaCodec == null) {
            throw new RuntimeException("Can not create media encoder");
        }
        this.mediaCodec.configure(createVideoFormat, null, null, 1);
        return true;
    }

    ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        if (z) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle bundle = new Bundle();
                bundle.putInt("request-sync", 0);
                this.mediaCodec.setParameters(bundle);
            } catch (Throwable e) {
                Logging.e(TAG, "encodeBuffer failed", e);
                return false;
            }
        }
        this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
        return true;
    }

    boolean encodeTexture(boolean z, int i, int i2, float[] fArr, int i3, int i4, long j) {
        checkOnMediaCodecThread();
        if (z) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle bundle = new Bundle();
                bundle.putInt("request-sync", 0);
                this.mediaCodec.setParameters(bundle);
            } catch (Throwable e) {
                Logging.e(TAG, "encodeTexture failed", e);
                return false;
            }
        }
        Logging.d(TAG, "enter encodeTexture:" + i3 + "x" + i4 + "->" + this.width + "x" + this.height);
        this.eglBase.makeCurrent();
        GLES20.glClear(16384);
        if (i2 == 10) {
            this.drawer.drawRgb(i, fArr, 0, 0, i3, i4, this.width, this.height);
        } else {
            this.drawer.drawOes(i, fArr, 0, 0, i3, i4, this.width, this.height);
        }
        this.eglBase.swapBuffers();
        return true;
    }

    void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    MediaCodecVideoEncoder.this.mediaCodec.stop();
                    MediaCodecVideoEncoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                } catch (Throwable e) {
                    Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, Config.BPLUS_DELAY_TIME)) {
            Logging.e(TAG, "Media encoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        if (this.drawer != null) {
            this.drawer.release();
            this.drawer = null;
        }
        if (this.eglBase != null) {
            this.eglBase.release();
            this.eglBase = null;
        }
        if (this.inputSurface != null) {
            this.inputSurface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        Logging.d(TAG, "Java releaseEncoder done");
    }

    private boolean setRates(int i, int i2) {
        checkOnMediaCodecThread();
        Logging.d(TAG, "Bwe setRates: " + i + " kbps");
        try {
            Bundle bundle;
            if (i > this.adjusted_kbps + 25) {
                this.adjusted_kbps = i;
                bundle = new Bundle();
                bundle.putInt("video-bitrate", this.adjusted_kbps * 950);
                this.mediaCodec.setParameters(bundle);
            }
            if (i >= this.adjusted_kbps) {
                return true;
            }
            this.adjusted_kbps = Math.min(i, this.adjusted_kbps - 50);
            bundle = new Bundle();
            bundle.putInt("video-bitrate", this.adjusted_kbps * 950);
            this.mediaCodec.setParameters(bundle);
            return false;
        } catch (Throwable e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (Throwable e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    OutputBufferInfo dequeueOutputBuffer() {
        boolean z = true;
        checkOnMediaCodecThread();
        try {
            BufferInfo bufferInfo = new BufferInfo();
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            if (dequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[dequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[dequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[dequeueOutputBuffer]);
                    this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                    dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                }
            }
            if (dequeueOutputBuffer >= 0) {
                ByteBuffer duplicate = this.outputBuffers[dequeueOutputBuffer].duplicate();
                duplicate.position(bufferInfo.offset);
                duplicate.limit(bufferInfo.offset + bufferInfo.size);
                if ((bufferInfo.flags & 1) == 0) {
                    z = false;
                }
                if (z) {
                    Logging.d(TAG, "Sync frame generated");
                }
                if (!z || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                    return new OutputBufferInfo(dequeueOutputBuffer, duplicate.slice(), z, bufferInfo.presentationTimeUs, bufferInfo.size);
                }
                Logging.d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
                this.configData.rewind();
                allocateDirect.put(this.configData);
                allocateDirect.put(duplicate);
                allocateDirect.position(0);
                return new OutputBufferInfo(dequeueOutputBuffer, allocateDirect, z, bufferInfo.presentationTimeUs, bufferInfo.size + this.configData.capacity());
            } else if (dequeueOutputBuffer == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (dequeueOutputBuffer == -2) {
                return dequeueOutputBuffer();
            } else {
                if (dequeueOutputBuffer == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + dequeueOutputBuffer);
            }
        } catch (Throwable e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, null, false, -1, 0);
        }
    }

    boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (Throwable e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }

    void dumpIntoFile(OutputBufferInfo outputBufferInfo) {
        if (this.fos == null) {
            try {
                this.fos = new FileOutputStream(String.format("/sdcard/java_dump_video_%d_%d.h264", new Object[]{Integer.valueOf(this.width), Integer.valueOf(this.height)}), true);
            } catch (Exception e) {
                Logging.i(TAG, "dumpIntoFile: failed to open java_dump_video.h264");
                return;
            }
        }
        if (outputBufferInfo != null && outputBufferInfo.index >= 0) {
            Logging.i(TAG, "Dump nal: " + outputBufferInfo.buffer);
            try {
                byte[] bArr = new byte[outputBufferInfo.buffer.remaining()];
                outputBufferInfo.buffer.get(bArr);
                this.fos.write(bArr, 0, outputBufferInfo.size);
            } catch (Throwable e2) {
                Logging.e(TAG, "Run: 4.1 Exception ", e2);
            }
        }
    }
}
