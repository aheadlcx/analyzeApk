package qsbk.app.video;

import android.media.MediaPlayer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.core.AsyncTask;

public class MediaPlayerPool {
    static List<MediaPlayer> a = Collections.synchronizedList(new LinkedList());

    public static MediaPlayer get() {
        if (a.size() > 0) {
            return (MediaPlayer) a.remove(0);
        }
        return new MediaPlayer();
    }

    public static synchronized void stopAndRelease(MediaPlayer mediaPlayer) {
        synchronized (MediaPlayerPool.class) {
            if (mediaPlayer != null) {
                mediaPlayer.setOnPreparedListener(null);
                mediaPlayer.setOnErrorListener(null);
                mediaPlayer.setOnInfoListener(null);
                mediaPlayer.setOnCompletionListener(null);
            }
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new g(mediaPlayer));
        }
    }
}
