package qsbk.app.core.utils;

import android.media.AudioManager;

public class AudioManagerUtils {
    private static AudioManagerUtils b;
    private String a = "AudioManagerUtils";
    private AudioManager c;
    private int d;
    private int e;

    private AudioManagerUtils() {
    }

    public static synchronized AudioManagerUtils getInstance() {
        AudioManagerUtils audioManagerUtils;
        synchronized (AudioManagerUtils.class) {
            if (b == null) {
                b = new AudioManagerUtils();
            }
            audioManagerUtils = b;
        }
        return audioManagerUtils;
    }

    public AudioManager getAudioManager() {
        if (this.c == null) {
            this.c = (AudioManager) AppUtils.getInstance().getAppContext().getSystemService("audio");
        }
        return this.c;
    }

    public int getMaxVolume(int i) {
        if (this.d <= 0) {
            this.d = getAudioManager().getStreamMaxVolume(i);
        }
        return this.d;
    }

    public int getCurrentVolume(int i) {
        this.e = getAudioManager().getStreamVolume(i);
        return this.e;
    }
}
