package qsbk.app.ye.videotools.utils;

import android.media.AudioRecord;
import android.os.SystemClock;
import android.util.Log;

public class MicrophoneDetection {
    public static boolean detect() {
        int i;
        int i2;
        int i3 = 0;
        int[] iArr = new int[]{16000, 22050, 32000, 44100};
        int i4 = 0;
        int i5 = -1;
        for (int i42 : iArr) {
            i5 = AudioRecord.getMinBufferSize(i42, 16, 2);
            if (i5 > 0) {
                i2 = i5;
                i5 = i42;
                break;
            }
        }
        i2 = i5;
        i5 = i42;
        if (i2 <= 0) {
            Log.e("microphone", "getMinBufferSize failed!");
            return false;
        }
        AudioRecord audioRecord;
        try {
            audioRecord = new AudioRecord(1, i5, 16, 2, i2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            audioRecord = null;
        }
        if (audioRecord == null) {
            Log.e("microphone", "create AudioRecord failed!");
            return false;
        }
        boolean z;
        try {
            audioRecord.startRecording();
            i = 1;
        } catch (IllegalStateException e2) {
            i = 0;
        } catch (IllegalArgumentException e3) {
            i = 0;
        } catch (Exception e4) {
            i = 0;
        }
        Log.i("microphone", "start Recording " + (i != 0 ? "success!" : "failed!"));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (i != 0) {
            byte[] bArr = new byte[i2];
            i = 0;
            i5 = 0;
            i42 = 0;
            while (i < 20) {
                int read = audioRecord.read(bArr, 0, i2);
                i5 += read;
                if (read != 0) {
                    for (int i6 = 0; i6 < read; i6++) {
                        if (bArr[i6] != (byte) 0) {
                            i42 = 1;
                            break;
                        }
                    }
                    if (i42 != 0) {
                        i3 = i5;
                        break;
                    }
                } else if (SystemClock.elapsedRealtime() - elapsedRealtime > 3000) {
                    i3 = i5;
                    break;
                } else {
                    i--;
                }
                i++;
            }
            i3 = i5;
        } else {
            z = false;
        }
        Log.i("microphone", (z ? "yeah, get voice success!" : "oh, no, it's silence!") + " size:" + i3 + " use time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        try {
            audioRecord.stop();
        } catch (IllegalStateException e5) {
        }
        audioRecord.release();
        return z;
    }
}
