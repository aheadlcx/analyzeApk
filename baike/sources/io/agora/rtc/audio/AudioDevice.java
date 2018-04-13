package io.agora.rtc.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect.Descriptor;
import android.os.Build.VERSION;
import android.os.Process;
import com.alipay.android.phone.mrpc.core.RpcException.ErrorCode;
import com.sina.weibo.sdk.constant.WBConstants;
import io.agora.rtc.internal.Logging;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

class AudioDevice {
    final String TAG = "AudioDevice Java";
    private AudioManager _audioManager;
    private AudioRecord _audioRecord = null;
    private AudioTrack _audioTrack = null;
    private int _bufferedPlaySamples = 0;
    private int _bufferedRecSamples = 0;
    private Context _context;
    private boolean _doPlayInit = true;
    private boolean _doRecInit = true;
    private boolean _isPlaying = false;
    private boolean _isRecording = false;
    private int _playBufSize = 0;
    private ByteBuffer _playBuffer;
    private int _playChannel = 0;
    private final ReentrantLock _playLock = new ReentrantLock();
    private int _playPosition = 0;
    private int _playbackRestartCount = 0;
    private int _playbackSampleRate = 0;
    private ByteBuffer _recBuffer;
    private final ReentrantLock _recLock = new ReentrantLock();
    private int _recordBufSize = 0;
    private int _recordChannel = 0;
    private int _recordRestartCount = 0;
    private int _recordSampleRate = 0;
    private int _recordSource = 0;
    private int _streamType = 0;
    private byte[] _tempBufPlay;
    private byte[] _tempBufRec;
    private AcousticEchoCanceler aec = null;
    private boolean useBuiltInAEC = false;

    AudioDevice() {
        try {
            this._playBuffer = ByteBuffer.allocateDirect(WBConstants.SDK_NEW_PAY_VERSION);
            this._recBuffer = ByteBuffer.allocateDirect(WBConstants.SDK_NEW_PAY_VERSION);
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "failed to allocate bytebuffer", e);
        }
        this._tempBufPlay = new byte[WBConstants.SDK_NEW_PAY_VERSION];
        this._tempBufRec = new byte[WBConstants.SDK_NEW_PAY_VERSION];
    }

    private boolean BuiltInAECIsAvailable() {
        try {
            if (VERSION.SDK_INT >= 16) {
                return AcousticEchoCanceler.isAvailable();
            }
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Unable to create AEC object ", e);
        } catch (Exception e2) {
            Logging.e("AudioDevice Java", "Unable to query Audio Effect: Acoustic Echo Cancellation");
        }
        return false;
    }

    private boolean EnableBuiltInAEC(boolean z) {
        if (VERSION.SDK_INT < 16) {
            return false;
        }
        this.useBuiltInAEC = z;
        if (this.aec != null) {
            if (this.aec.setEnabled(z) != 0) {
                Logging.e("AudioDevice Java", "AcousticEchoCanceler.setEnabled failed");
                return false;
            }
            Logging.e("AudioDevice Java", "AcousticEchoCanceler.getEnabled: " + this.aec.getEnabled());
        }
        return true;
    }

    private boolean BuiltInAECIsEnabled() {
        return this.useBuiltInAEC;
    }

    private int InitRecording(int i, int i2, int i3) {
        int minBufferSize = AudioRecord.getMinBufferSize(i2, i3 == 2 ? 12 : 16, 2);
        Logging.d("AudioDevice Java", "Java minimum recording buffer size is " + minBufferSize);
        int i4 = minBufferSize * 2;
        this._bufferedRecSamples = (i2 * 5) / 200;
        if (this.aec != null) {
            this.aec.release();
            this.aec = null;
        }
        if (this._audioRecord != null) {
            this._audioRecord.release();
            this._audioRecord = null;
        }
        try {
            int i5;
            if (i3 == 2) {
                i5 = 12;
            } else {
                i5 = 16;
            }
            this._audioRecord = new AudioRecord(i, i2, i5, 2, i4);
            if (this._audioRecord.getState() != 1) {
                Logging.e("AudioDevice Java", "Java recording not initialized " + i2);
                return -2;
            }
            this._recordSampleRate = i2;
            this._recordChannel = i3;
            this._recordSource = i;
            this._recordBufSize = i4;
            this._recordRestartCount = 0;
            Logging.d("AudioDevice Java", "Java recording sample rate set to " + i2);
            Logging.d("AudioDevice Java", "AcousticEchoCanceler.isAvailable: " + BuiltInAECIsAvailable());
            if (!BuiltInAECIsAvailable()) {
                return this._bufferedRecSamples;
            }
            this.aec = AcousticEchoCanceler.create(this._audioRecord.getAudioSessionId());
            if (this.aec == null) {
                Logging.e("AudioDevice Java", "AcousticEchoCanceler.create failed");
            } else {
                Descriptor descriptor = this.aec.getDescriptor();
                Logging.d("AudioDevice Java", "AcousticEchoCanceler name: " + descriptor.name + ", " + "implementor: " + descriptor.implementor + ", " + "uuid: " + descriptor.uuid);
                EnableBuiltInAEC(this.useBuiltInAEC);
            }
            return this._bufferedRecSamples;
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Unable to new AudioRecord: ", e);
            return -1;
        }
    }

    private int StartRecording() {
        try {
            this._audioRecord.startRecording();
            this._isRecording = true;
            return 0;
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "failed to startRecording", e);
            return -1;
        }
    }

    private int InitPlayback(int i, int i2, int i3) {
        this._streamType = i3;
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2 == 2 ? 12 : 4, 2);
        Logging.d("AudioDevice Java", "Java minimum playback buffer size is " + minBufferSize);
        if (minBufferSize < ErrorCode.SERVER_SERVICENOTFOUND) {
            minBufferSize *= 2;
        }
        this._bufferedPlaySamples = 0;
        Logging.d("AudioDevice Java", "Java playback buffer size is " + minBufferSize);
        if (this._audioTrack != null) {
            this._audioTrack.release();
            this._audioTrack = null;
        }
        try {
            int i4;
            int i5 = this._streamType;
            if (i2 == 2) {
                i4 = 12;
            } else {
                i4 = 4;
            }
            this._audioTrack = new AudioTrack(i5, i, i4, 2, minBufferSize, 1);
            this._playbackSampleRate = i;
            this._playChannel = i2;
            this._playBufSize = minBufferSize;
            this._playbackRestartCount = 0;
            if (this._audioTrack.getState() != 1) {
                Logging.e("AudioDevice Java", "Java playback not initialized " + i);
                return -1;
            }
            Logging.d("AudioDevice Java", "Java play sample rate is set to " + i);
            if (this._audioManager == null && this._context != null) {
                this._audioManager = (AudioManager) this._context.getSystemService("audio");
            }
            if (this._audioManager == null) {
                return 0;
            }
            return this._audioManager.getStreamMaxVolume(this._streamType);
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Unable to new AudioTrack: ", e);
            return -1;
        }
    }

    private int StartPlayback() {
        try {
            this._audioTrack.play();
            this._isPlaying = true;
            return 0;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int StopRecording() {
        boolean z = false;
        this._recLock.lock();
        try {
            if (this._audioRecord.getRecordingState() == 3) {
                this._audioRecord.stop();
            }
            if (this.aec != null) {
                this.aec.release();
                this.aec = null;
            }
            this._audioRecord.release();
            this._audioRecord = null;
            this._isRecording = z;
            return z;
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Unable to stop recording: ", e);
            int i = -1;
            return i;
        } finally {
            this._doRecInit = true;
            this._recLock.unlock();
        }
    }

    private int StopPlayback() {
        boolean z = false;
        this._playLock.lock();
        try {
            if (this._audioTrack.getPlayState() == 3) {
                this._audioTrack.stop();
                this._audioTrack.flush();
            }
            this._audioTrack.release();
            this._audioTrack = null;
            this._isPlaying = z;
            return z;
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Unable to stop playback: ", e);
            int i = -1;
            return i;
        } finally {
            this._doPlayInit = true;
            this._playLock.unlock();
        }
    }

    private int PlayAudio(int i) {
        int i2 = 0;
        this._playLock.lock();
        try {
            if (this._audioTrack == null) {
                this._playLock.unlock();
                return -2;
            }
            if (this._doPlayInit) {
                Process.setThreadPriority(-19);
                this._doPlayInit = false;
            }
            this._playBuffer.get(this._tempBufPlay);
            int write = this._audioTrack.write(this._tempBufPlay, 0, i);
            this._playBuffer.rewind();
            this._bufferedPlaySamples += write >> 1;
            int playbackHeadPosition = this._audioTrack.getPlaybackHeadPosition() * this._playChannel;
            if (playbackHeadPosition < this._playPosition) {
                this._playPosition = 0;
            }
            this._bufferedPlaySamples -= playbackHeadPosition - this._playPosition;
            this._playPosition = playbackHeadPosition;
            if (!this._isRecording) {
                i2 = this._bufferedPlaySamples;
            }
            if (write == i) {
                this._playLock.unlock();
                return i2;
            } else if (this._playbackRestartCount > 20) {
                this._playLock.unlock();
                return write;
            } else {
                Logging.e("AudioDevice Java", "Error writing AudioTrack! Restart AudioTrack " + this._playbackRestartCount);
                this._playbackRestartCount++;
                this._audioTrack.stop();
                this._audioTrack.release();
                this._audioTrack = null;
                this._audioTrack = new AudioTrack(this._streamType, this._playbackSampleRate, this._playChannel == 2 ? 12 : 4, 2, this._playBufSize, 1);
                this._audioTrack.play();
                this._playLock.unlock();
                return write;
            }
        } catch (Throwable e) {
            Logging.e("AudioDevice Java", "Set play thread priority failed: ", e);
        } catch (Throwable th) {
            this._playLock.unlock();
        }
    }

    private int RecordAudio(int i) {
        this._recLock.lock();
        try {
            if (this._audioRecord == null) {
                return -2;
            }
            if (this._doRecInit) {
                try {
                    Process.setThreadPriority(-19);
                } catch (Throwable e) {
                    Logging.e("AudioDevice Java", "Set rec thread priority failed: ", e);
                }
                this._doRecInit = false;
            }
            this._recBuffer.rewind();
            int read = this._audioRecord.read(this._tempBufRec, 0, i);
            this._recBuffer.put(this._tempBufRec);
            if (read != i) {
                Logging.e("AudioDevice Java", "Error reading AudioRecord! Restart AudioRecord " + this._recordRestartCount);
                this._recordRestartCount++;
                this._audioRecord.stop();
                this._audioRecord.release();
                this._audioRecord = null;
                this._audioRecord = new AudioRecord(this._recordSource, this._recordSampleRate, this._recordChannel == 2 ? 12 : 16, 2, this._recordBufSize);
                this._audioRecord.startRecording();
                this._recLock.unlock();
                return read;
            }
            this._recLock.unlock();
            return this._bufferedPlaySamples;
        } catch (Throwable e2) {
            Logging.e("AudioDevice Java", "RecordAudio try failed: ", e2);
        } finally {
            this._recLock.unlock();
        }
    }

    private int QuerySpeakerStatus() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            Logging.e("AudioDevice Java", "Could not get audio routing - no audio manager");
            return -1;
        } else if (this._audioManager.isSpeakerphoneOn()) {
            return 3;
        } else {
            if (this._audioManager.isBluetoothScoOn()) {
                return 5;
            }
            if (this._audioManager.isWiredHeadsetOn()) {
                return 0;
            }
            return 1;
        }
    }

    private int SetPlayoutSpeaker(boolean z) {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            Logging.e("AudioDevice Java", "Could not change audio routing - no audio manager");
            return -1;
        }
        this._audioManager.setSpeakerphoneOn(z);
        return 0;
    }

    private int SetPlayoutVolume(int i) {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            return -1;
        }
        int streamMaxVolume = this._audioManager.getStreamMaxVolume(this._streamType);
        if (i < 255) {
            streamMaxVolume = (streamMaxVolume * i) / 255;
        }
        this._audioManager.setStreamVolume(this._streamType, streamMaxVolume, 0);
        return 0;
    }

    private int GetPlayoutVolume() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager != null) {
            return this._audioManager.getStreamVolume(this._streamType);
        }
        return -1;
    }

    private int GetPlayoutMaxVolume() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager != null) {
            return this._audioManager.getStreamMaxVolume(this._streamType);
        }
        return -1;
    }

    private int SetAudioMode(int i) {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            Logging.e("AudioDevice Java", "Could not change audio routing - no audio manager");
            return -1;
        }
        switch (i) {
            case 0:
                this._audioManager.setMode(0);
                break;
            case 1:
                this._audioManager.setMode(1);
                break;
            case 2:
                this._audioManager.setMode(2);
                break;
            case 3:
                this._audioManager.setMode(3);
                break;
            default:
                this._audioManager.setMode(0);
                break;
        }
        return 0;
    }

    private int GetAudioMode() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager != null) {
            return this._audioManager.getMode();
        }
        Logging.e("AudioDevice Java", "Could not change audio routing - no audio manager");
        return -1;
    }

    private int GetNativeSampleRate() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            Logging.w("AudioDevice Java", "Could not set audio mode - no audio manager");
            return 44100;
        }
        int parseInt;
        if (VERSION.SDK_INT >= 17) {
            String property = this._audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if (property != null) {
                parseInt = Integer.parseInt(property);
                return parseInt;
            }
        }
        parseInt = 44100;
        return parseInt;
    }
}
