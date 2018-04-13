package io.agora.rtc.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Log;
import java.nio.ByteBuffer;

public class MediaCodecAudioDecoder {
    private String TAG = "MediaCodec Audio Decoder";
    private boolean eoInputStream = false;
    private boolean eoOutputStream = false;
    private MediaCodec mAACDecoder = null;
    private ByteBuffer mAACOutputBuffer = ByteBuffer.allocateDirect(4096);
    private Context mContext = null;
    private boolean mDecodedDataReady = false;
    private ByteBuffer mDecodedRAWBuffer;
    private MediaExtractor mExtractor = null;
    private ByteBuffer[] mInputBuffers;
    private MediaCodec mMediaCodec = null;
    private ByteBuffer[] mOutputBuffers;
    private MediaFormat mTrackFormat = null;

    public boolean createStreaming(String str) {
        try {
            int i;
            Log.i("AgoraAudioDecoder", "try to decode audio file : " + str);
            String str2 = "/assets/";
            boolean startsWith = str.startsWith(str2);
            this.mExtractor = new MediaExtractor();
            if (!startsWith) {
                this.mExtractor.setDataSource(str);
            } else if (this.mContext == null) {
                return false;
            } else {
                AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str.substring(str2.length()));
                this.mExtractor.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            }
            int trackCount = this.mExtractor.getTrackCount();
            for (i = 0; i < trackCount; i++) {
                this.mExtractor.unselectTrack(i);
            }
            for (i = 0; i < trackCount; i++) {
                this.mTrackFormat = this.mExtractor.getTrackFormat(i);
                String string = this.mTrackFormat.getString("mime");
                if (string.contains("audio/")) {
                    this.mExtractor.selectTrack(i);
                    this.mMediaCodec = MediaCodec.createDecoderByType(string);
                    this.mMediaCodec.configure(this.mTrackFormat, null, null, 0);
                    break;
                }
            }
            if (this.mMediaCodec != null) {
                this.mMediaCodec.start();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getAndroidVersion() {
        return VERSION.SDK_INT;
    }

    public int getChannelCount() {
        return this.mTrackFormat.getInteger("channel-count");
    }

    public int getSampleRate() {
        return this.mTrackFormat.getInteger("sample-rate");
    }

    public long getFileLength() {
        return this.mTrackFormat.getLong("durationUs");
    }

    public long getCurrentFilePosition() {
        return this.mExtractor.getSampleTime();
    }

    public void setCurrentFilePosition(long j) {
        this.mExtractor.seekTo(j, 2);
    }

    public boolean getDecodeDataReadyFlag() {
        return this.mDecodedDataReady;
    }

    public void releaseStreaming() {
        if (this.mMediaCodec != null) {
            this.mMediaCodec.stop();
            this.mMediaCodec.release();
            this.mMediaCodec = null;
        }
        if (this.mExtractor != null) {
            this.mExtractor.release();
            this.mExtractor = null;
        }
        this.eoOutputStream = false;
        this.eoInputStream = false;
    }

    public void rewindStreaming() {
        this.mExtractor.seekTo(0, 1);
        this.mMediaCodec.flush();
        this.eoInputStream = false;
        this.eoOutputStream = false;
        this.mDecodedDataReady = false;
    }

    public boolean decodeFrame() {
        int dequeueInputBuffer;
        if (!this.eoInputStream) {
            dequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(0);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer inputBuffer;
                if (VERSION.SDK_INT >= 21) {
                    inputBuffer = this.mMediaCodec.getInputBuffer(dequeueInputBuffer);
                } else {
                    this.mInputBuffers = this.mMediaCodec.getInputBuffers();
                    inputBuffer = this.mInputBuffers[dequeueInputBuffer];
                }
                int readSampleData = this.mExtractor.readSampleData(inputBuffer, 0);
                if (readSampleData <= 0) {
                    this.eoInputStream = true;
                    readSampleData = 0;
                }
                long sampleTime = this.mExtractor.getSampleTime();
                int sampleFlags = this.mExtractor.getSampleFlags();
                if (this.eoInputStream) {
                    sampleFlags |= 4;
                }
                this.mMediaCodec.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, sampleFlags);
                this.mExtractor.advance();
            }
        }
        if (!this.eoOutputStream) {
            BufferInfo bufferInfo = new BufferInfo();
            dequeueInputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            this.mDecodedDataReady = false;
            switch (dequeueInputBuffer) {
                case -3:
                case -2:
                case -1:
                    break;
                default:
                    if (dequeueInputBuffer >= 0) {
                        if ((bufferInfo.flags & 4) == 4) {
                            this.eoOutputStream = true;
                        }
                        if (VERSION.SDK_INT >= 21) {
                            cloneByteBuffer(this.mMediaCodec.getOutputBuffer(dequeueInputBuffer));
                        } else {
                            this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
                            cloneByteBufferByLength(this.mOutputBuffers[dequeueInputBuffer], bufferInfo.size);
                        }
                        this.mMediaCodec.releaseOutputBuffer(dequeueInputBuffer, false);
                        this.mDecodedDataReady = true;
                        break;
                    }
                    break;
            }
        }
        return this.eoOutputStream;
    }

    private void cloneByteBuffer(ByteBuffer byteBuffer) {
        if (this.mDecodedRAWBuffer == null || this.mDecodedRAWBuffer.limit() != byteBuffer.limit()) {
            if (this.mDecodedRAWBuffer != null) {
                this.mDecodedRAWBuffer.clear();
                this.mDecodedRAWBuffer = null;
            }
            this.mDecodedRAWBuffer = ByteBuffer.allocateDirect(byteBuffer.limit());
        }
        this.mDecodedRAWBuffer.position(0);
        this.mDecodedRAWBuffer.put(byteBuffer);
    }

    private void cloneByteBufferByLength(ByteBuffer byteBuffer, int i) {
        if (this.mDecodedRAWBuffer == null || this.mDecodedRAWBuffer.capacity() < i) {
            if (this.mDecodedRAWBuffer != null) {
                this.mDecodedRAWBuffer.clear();
                this.mDecodedRAWBuffer = null;
            }
            this.mDecodedRAWBuffer = ByteBuffer.allocateDirect(i);
        }
        this.mDecodedRAWBuffer.position(0);
        byteBuffer.limit(i);
        this.mDecodedRAWBuffer.put(byteBuffer);
    }

    public boolean checkAACSupported() {
        int i;
        if (VERSION.SDK_INT >= 21) {
            for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(1).getCodecInfos()) {
                if (!mediaCodecInfo.isEncoder() && mediaCodecInfo.getName().toLowerCase().contains("nvidia")) {
                    return false;
                }
            }
        } else {
            int codecCount = MediaCodecList.getCodecCount();
            for (i = 0; i < codecCount; i++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
                if (!codecInfoAt.isEncoder() && codecInfoAt.getName().toLowerCase().contains("nvidia")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean createAACStreaming(int i) {
        try {
            this.mAACDecoder = MediaCodec.createDecoderByType("audio/mp4a-latm");
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, 1);
            createAudioFormat.setInteger("sample-rate", i);
            createAudioFormat.setInteger("channel-count", 1);
            String str = "csd-0";
            createAudioFormat.setByteBuffer(str, ByteBuffer.wrap(new byte[]{(byte) 18, (byte) -120}));
            this.mAACDecoder.configure(createAudioFormat, null, null, 0);
            if (this.mAACDecoder == null) {
                return true;
            }
            this.mAACDecoder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void releaseAACStreaming() {
        if (this.mAACDecoder != null) {
            this.mAACDecoder.stop();
            this.mAACDecoder.release();
            this.mAACDecoder = null;
        }
    }

    public int decodeAACFrame(byte[] bArr) {
        ByteBuffer inputBuffer;
        int dequeueInputBuffer = this.mAACDecoder.dequeueInputBuffer(200);
        if (dequeueInputBuffer >= 0) {
            if (VERSION.SDK_INT >= 21) {
                inputBuffer = this.mAACDecoder.getInputBuffer(dequeueInputBuffer);
            } else {
                inputBuffer = this.mAACDecoder.getInputBuffers()[dequeueInputBuffer];
            }
            inputBuffer.clear();
            inputBuffer.put(bArr);
            this.mAACDecoder.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, 0, 0);
        }
        BufferInfo bufferInfo = new BufferInfo();
        int dequeueOutputBuffer = this.mAACDecoder.dequeueOutputBuffer(bufferInfo, 0);
        switch (dequeueOutputBuffer) {
            case -3:
            case -2:
            case -1:
                return 0;
            default:
                if (dequeueOutputBuffer < 0) {
                    return 0;
                }
                if (VERSION.SDK_INT >= 21) {
                    inputBuffer = this.mAACDecoder.getOutputBuffer(dequeueOutputBuffer);
                } else {
                    inputBuffer = this.mAACDecoder.getOutputBuffers()[dequeueOutputBuffer];
                }
                dequeueInputBuffer = bufferInfo.size;
                this.mAACOutputBuffer.position(0);
                inputBuffer.limit(dequeueInputBuffer);
                this.mAACOutputBuffer.put(inputBuffer);
                this.mAACDecoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                return dequeueInputBuffer;
        }
    }
}
