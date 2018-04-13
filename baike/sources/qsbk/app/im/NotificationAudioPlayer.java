package qsbk.app.im;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.umeng.analytics.pro.b;
import qsbk.app.utils.LogUtil;

public class NotificationAudioPlayer {
    private static MediaPlayer a;
    private static long b = 0;

    public static void playSound(Context context) {
        playSound(context, "audio/notification.mp3");
    }

    public static synchronized void playSound(Context context, String str) {
        synchronized (NotificationAudioPlayer.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b >= 1000) {
                b = currentTimeMillis;
                if (a != null && a.isPlaying()) {
                    a.stop();
                }
                try {
                    if (a == null) {
                        a = new MediaPlayer();
                    } else {
                        a.reset();
                    }
                    AssetFileDescriptor openFd = context.getAssets().openFd(str);
                    a.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    a.setAudioStreamType(5);
                    LogUtil.d("AudioPath:" + str);
                    a.prepare();
                    a.start();
                } catch (Exception e) {
                    LogUtil.d(b.J + e.getLocalizedMessage());
                }
            }
        }
    }

    public synchronized void stopPlay() {
        if (a != null) {
            LogUtil.d("in Stop play");
            if (a.isPlaying()) {
                a.stop();
            }
            a.release();
            a = null;
        }
    }
}
