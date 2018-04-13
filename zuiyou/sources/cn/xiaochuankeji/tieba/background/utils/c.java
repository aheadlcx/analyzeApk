package cn.xiaochuankeji.tieba.background.utils;

import android.media.AudioRecord;

public class c {
    public static boolean a() {
        try {
            AudioRecord audioRecord = new AudioRecord(1, 16000, 16, 2, AudioRecord.getMinBufferSize(16000, 16, 2));
            try {
                audioRecord.startRecording();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            if (audioRecord.getRecordingState() != 3) {
                return false;
            }
            audioRecord.stop();
            audioRecord.release();
            return true;
        } catch (IllegalArgumentException e2) {
            return false;
        }
    }
}
