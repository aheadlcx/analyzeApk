package qsbk.app.core.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import java.io.IOException;

public class PlayerManager {
    private static final String a = PlayerManager.class.getSimpleName();
    private MediaPlayer b;
    private PlayCallback c;
    private Context d = AppUtils.getInstance().getAppContext();
    private boolean e = false;
    private String f;

    public static class PlayCallback {
        public void onPrepared() {
        }

        public void onComplete() {
        }

        public void onStop() {
        }
    }

    public static synchronized PlayerManager getInstance() {
        PlayerManager playerManager;
        synchronized (PlayerManager.class) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }

    private PlayerManager() {
        b();
    }

    private void b() {
        this.b = new MediaPlayer();
        this.b.setAudioStreamType(3);
    }

    public void play(String str) {
        play(str, false);
    }

    public void play(String str, boolean z) {
        play(str, z, null);
    }

    public void play(String str, boolean z, PlayCallback playCallback) {
        this.f = str;
        this.c = playCallback;
        try {
            this.b.reset();
            this.b.setDataSource(this.d, Uri.parse(str));
            this.b.setLooping(z);
            this.b.prepareAsync();
            this.b.setOnPreparedListener(new q(this, playCallback));
            this.b.setOnInfoListener(new r(this));
            this.b.setOnErrorListener(new s(this));
            this.b.setOnCompletionListener(new t(this, playCallback));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPause() {
        return this.e;
    }

    public void pause() {
        if (isPlaying()) {
            this.e = true;
            this.b.pause();
        }
    }

    public void resume() {
        if (this.e) {
            this.e = false;
            this.b.start();
        }
    }

    public void stop() {
        try {
            this.b.setLooping(false);
            this.b.stop();
            if (this.c != null) {
                this.c.onStop();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return this.b != null && this.b.isPlaying();
    }
}
