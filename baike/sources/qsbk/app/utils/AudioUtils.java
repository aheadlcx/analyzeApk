package qsbk.app.utils;

import android.content.Context;
import android.media.AudioManager;

public class AudioUtils {
    public static boolean isDeviceSilentMode(Context context) {
        return ((AudioManager) context.getSystemService("audio")).getRingerMode() != 2;
    }
}
