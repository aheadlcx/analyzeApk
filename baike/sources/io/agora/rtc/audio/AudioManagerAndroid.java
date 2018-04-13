package io.agora.rtc.audio;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;

class AudioManagerAndroid {
    private static final int DEFAULT_FRAMES_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLING_RATE = 44100;
    private AudioManager audioManager;
    private int mAudioLowLatencyOutputFrameSize = 256;
    private boolean mAudioLowLatencySupported;
    private int mNativeOutputSampleRate = DEFAULT_SAMPLING_RATE;

    private AudioManagerAndroid(Context context) {
        this.audioManager = (AudioManager) context.getSystemService("audio");
        if (VERSION.SDK_INT >= 17) {
            String property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if (property != null) {
                this.mNativeOutputSampleRate = Integer.parseInt(property);
            }
            property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
            if (property != null) {
                this.mAudioLowLatencyOutputFrameSize = Integer.parseInt(property);
            }
        }
        this.mAudioLowLatencySupported = context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private int getNativeOutputSampleRate() {
        return this.mNativeOutputSampleRate;
    }

    private boolean isAudioLowLatencySupported() {
        return this.mAudioLowLatencySupported;
    }

    private int getAudioLowLatencyOutputFrameSize() {
        return this.mAudioLowLatencyOutputFrameSize;
    }

    private int QuerySpeakerStatus() {
        if (this.audioManager.isBluetoothScoOn()) {
            return 5;
        }
        if (this.audioManager.isWiredHeadsetOn()) {
            return 0;
        }
        if (this.audioManager.isSpeakerphoneOn()) {
            return 3;
        }
        return 1;
    }

    private int SetPlayoutSpeaker(boolean z) {
        this.audioManager.setSpeakerphoneOn(z);
        return 0;
    }

    private int SetAudioMode(int i) {
        switch (i) {
            case 0:
                this.audioManager.setMode(0);
                break;
            case 1:
                this.audioManager.setMode(1);
                break;
            case 2:
                this.audioManager.setMode(2);
                break;
            case 3:
                this.audioManager.setMode(3);
                break;
            default:
                this.audioManager.setMode(0);
                break;
        }
        return 0;
    }

    private int GetAudioMode(int i) {
        return this.audioManager.getMode();
    }
}
