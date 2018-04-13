package com.budejie.www.goddubbing.c;

import android.media.AudioRecord;
import android.text.TextUtils;
import com.budejie.www.util.aa;

public class f {
    public static boolean a() {
        try {
            AudioRecord b = b();
            b.startRecording();
            byte[] bArr = new byte[2048];
            int read = b.read(bArr, 0, bArr.length);
            b.stop();
            if (-3 != read) {
                return true;
            }
            return false;
        } catch (Exception e) {
            if (TextUtils.isEmpty(e.getMessage())) {
                return false;
            }
            aa.e("PermissionUtil", e.getMessage());
            return false;
        }
    }

    private static AudioRecord b() {
        return new AudioRecord(1, 44100, 12, 2, AudioRecord.getMinBufferSize(44100, 12, 2));
    }
}
