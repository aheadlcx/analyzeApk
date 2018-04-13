package io.agora.rtc.audio;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import io.agora.rtc.internal.Logging;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaCodecAudioEncoder {
    private String TAG = "MediaCodec Audio Encoder";
    private ByteBuffer mAACEncodedBuffer = ByteBuffer.allocateDirect(1024);
    private MediaCodec mAACEncoder = null;
    private MediaFormat mAACFormat = null;
    private ByteBuffer[] mAACInputBuffers;
    private ByteBuffer[] mAACOutputBuffers;
    private String mCodecString = null;
    private ByteBuffer[] mInputBuffers;
    private MediaCodec mMediaCodec = null;
    private ByteBuffer[] mOutputBuffers;
    private MediaFormat mTrackFormat = null;
    private File outputFile = null;
    private BufferedOutputStream outputStream = null;

    public boolean createStreaming(String str, int i, int i2) {
        try {
            Logging.i(this.TAG, "Recording aac with fs = " + i + ", ch = " + i2);
            String substring = str.substring(str.length() - 3);
            if (substring.equalsIgnoreCase("3gp") || substring.equalsIgnoreCase("amr")) {
                if (i == 8000) {
                    this.mMediaCodec = MediaCodec.createEncoderByType("audio/3gpp");
                    this.mTrackFormat = MediaFormat.createAudioFormat("audio/3gpp", i, i2);
                    this.mTrackFormat.setInteger("bitrate", 12200);
                    this.mCodecString = "audio/3gpp";
                } else if (i == 16000) {
                    this.mMediaCodec = MediaCodec.createEncoderByType("audio/amr-wb");
                    this.mTrackFormat = MediaFormat.createAudioFormat("audio/amr-wb", i, i2);
                    this.mTrackFormat.setInteger("bitrate", 23850);
                    this.mCodecString = "audio/amr-wb";
                }
            } else if (!substring.equalsIgnoreCase("aac")) {
                return false;
            } else {
                this.mMediaCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
                this.mTrackFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, i2);
                int[] iArr = new int[]{2, 5, 39};
                this.mTrackFormat.setInteger("aac-profile", 2);
                this.mTrackFormat.setInteger("sample-rate", i);
                this.mTrackFormat.setInteger("channel-count", i2);
                this.mTrackFormat.setInteger("bitrate", 64000);
                this.mCodecString = "audio/mp4a-latm";
            }
            this.mMediaCodec.configure(this.mTrackFormat, null, null, 1);
            if (this.mMediaCodec != null) {
                this.mMediaCodec.start();
            }
            this.outputFile = new File(str);
            touch(this.outputFile);
            try {
                this.outputStream = new BufferedOutputStream(new FileOutputStream(this.outputFile));
                Logging.i(this.TAG, "outputStream initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.mCodecString == "audio/3gpp") {
                this.outputStream.write(new byte[]{(byte) 35, (byte) 33, (byte) 65, (byte) 77, (byte) 82, (byte) 10});
            } else if (this.mCodecString == "audio/amr-wb") {
                this.outputStream.write(new byte[]{(byte) 35, (byte) 33, (byte) 65, (byte) 77, (byte) 82, (byte) 45, (byte) 87, (byte) 66, (byte) 10});
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int getAndroidVersion() {
        return VERSION.SDK_INT;
    }

    public void setChannelCount(int i) {
        this.mTrackFormat.setInteger("channel-count", i);
    }

    public void setSampleRate(int i) {
        this.mTrackFormat.setInteger("sample-rate", i);
    }

    public void releaseStreaming() {
        try {
            if (this.mMediaCodec != null) {
                this.mMediaCodec.stop();
                this.mMediaCodec.release();
                this.mMediaCodec = null;
            }
            if (this.outputStream != null) {
                this.outputStream.flush();
                this.outputStream.close();
                this.outputStream = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encodeFrame(byte[] bArr) {
        try {
            ByteBuffer inputBuffer;
            int dequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(2000);
            if (dequeueInputBuffer != -1) {
                if (VERSION.SDK_INT >= 21) {
                    inputBuffer = this.mMediaCodec.getInputBuffer(dequeueInputBuffer);
                } else {
                    this.mInputBuffers = this.mMediaCodec.getInputBuffers();
                    inputBuffer = this.mInputBuffers[dequeueInputBuffer];
                }
                inputBuffer.clear();
                inputBuffer.put(bArr);
                this.mMediaCodec.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
            }
            BufferInfo bufferInfo = new BufferInfo();
            dequeueInputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            while (dequeueInputBuffer >= 0) {
                int i = bufferInfo.size;
                if (VERSION.SDK_INT >= 21) {
                    inputBuffer = this.mMediaCodec.getOutputBuffer(dequeueInputBuffer);
                } else {
                    this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
                    inputBuffer = this.mOutputBuffers[dequeueInputBuffer];
                }
                inputBuffer.position(bufferInfo.offset);
                inputBuffer.limit(bufferInfo.offset + i);
                if (this.mCodecString == "audio/mp4a-latm") {
                    int i2 = i + 7;
                    byte[] bArr2 = new byte[i2];
                    addADTStoPacket(bArr2, i2);
                    inputBuffer.get(bArr2, 7, i);
                    inputBuffer.position(bufferInfo.offset);
                    this.outputStream.write(bArr2, 0, bArr2.length);
                } else if (this.mCodecString == "audio/3gpp" || this.mCodecString == "audio/amr-wb") {
                    byte[] bArr3 = new byte[i];
                    inputBuffer.get(bArr3, 0, i);
                    inputBuffer.position(bufferInfo.offset);
                    this.outputStream.write(bArr3, 0, bArr3.length);
                }
                this.mMediaCodec.releaseOutputBuffer(dequeueInputBuffer, false);
                dequeueInputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void addADTStoPacket(byte[] bArr, int i) {
        bArr[0] = (byte) -1;
        bArr[1] = (byte) -7;
        bArr[2] = (byte) 84;
        bArr[3] = (byte) ((i >> 11) + 64);
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = (byte) -4;
    }

    private void touch(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createAACStreaming(int i, int i2) {
        try {
            Logging.i(this.TAG, "Encoding aac with fs = " + i + ", bitrate = " + i2);
            this.mAACEncoder = MediaCodec.createEncoderByType("audio/mp4a-latm");
            this.mAACFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, 1);
            int[] iArr = new int[]{2, 5, 39};
            this.mAACFormat.setInteger("aac-profile", 2);
            this.mAACFormat.setInteger("sample-rate", i);
            this.mAACFormat.setInteger("channel-count", 1);
            this.mAACFormat.setInteger("bitrate", i2);
            this.mAACEncoder.configure(this.mAACFormat, null, null, 1);
            if (this.mAACEncoder == null) {
                return true;
            }
            this.mAACEncoder.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void releaseAACStreaming() {
        try {
            if (this.mAACEncoder != null) {
                this.mAACEncoder.stop();
                this.mAACEncoder.release();
                this.mAACEncoder = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int encodeAACFrame(byte[] bArr) {
        int i;
        Throwable th;
        try {
            int dequeueInputBuffer = this.mAACEncoder.dequeueInputBuffer(2000);
            if (dequeueInputBuffer != -1) {
                ByteBuffer inputBuffer;
                if (VERSION.SDK_INT >= 21) {
                    inputBuffer = this.mAACEncoder.getInputBuffer(dequeueInputBuffer);
                } else {
                    this.mAACInputBuffers = this.mAACEncoder.getInputBuffers();
                    inputBuffer = this.mAACInputBuffers[dequeueInputBuffer];
                }
                inputBuffer.clear();
                inputBuffer.put(bArr);
                this.mAACEncoder.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
            }
            BufferInfo bufferInfo = new BufferInfo();
            int dequeueOutputBuffer = this.mAACEncoder.dequeueOutputBuffer(bufferInfo, 0);
            if (dequeueOutputBuffer < 0) {
                return 0;
            }
            ByteBuffer outputBuffer;
            int i2 = bufferInfo.size;
            if (VERSION.SDK_INT >= 21) {
                outputBuffer = this.mAACEncoder.getOutputBuffer(dequeueOutputBuffer);
            } else {
                this.mAACOutputBuffers = this.mAACEncoder.getOutputBuffers();
                outputBuffer = this.mAACOutputBuffers[dequeueOutputBuffer];
            }
            if ((bufferInfo.flags & 2) == 2) {
                i = 0;
            } else {
                i = bufferInfo.size;
            }
            try {
                outputBuffer.position(bufferInfo.offset);
                outputBuffer.limit(bufferInfo.offset + i2);
                this.mAACEncodedBuffer.position(0);
                this.mAACEncodedBuffer.put(outputBuffer);
                this.mAACEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                return i;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            i = 0;
            Logging.e(this.TAG, "encode aac frame failed: ", th);
            return i;
        }
    }
}
