package qsbk.app.video;

import android.text.TextUtils;
import android.view.Surface;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import qsbk.app.QsbkApp;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.ye.videotools.player.VideoPlayer;

public class SimpleVideoPlayer {
    public static final int FIRST_FRAME = 4;
    public static final int PAUSE = 2;
    public static final int PLAYING = 1;
    public static final int PREPARE_PLAY = 3;
    public static final int STATE_END = 5;
    public static final int STATE_PAUSE = 4;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    public static final int STATE_RESET = 0;
    public static final int STOP = 0;
    private VideoPlayer a;
    private Surface b;
    private int c = 0;
    private int d = 0;
    private boolean e;
    private String f;
    private boolean g;
    private boolean h = false;
    private long i;
    private int j = 100;
    private int k = 100;
    private long l;
    private String m;
    private int n;
    private int o;
    private OnVideoEventListener p;
    private OnVideoStateListener q;
    private OnVideoCacheListener r;

    public interface OnVideoEventListener {
        void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i);

        void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer);

        void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2);

        void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer);
    }

    public interface OnVideoStateListener {
        public static final int FIRST_FRAME = 4;
        public static final int PAUSE = 2;
        public static final int PLAYING = 1;
        public static final int PREPARE_PLAY = 3;
        public static final int STOP = 0;

        void onVideoState(SimpleVideoPlayer simpleVideoPlayer, int i);
    }

    public interface OnVideoCacheListener {
        void onVideoCachePercent(SimpleVideoPlayer simpleVideoPlayer, int i);
    }

    public int getPlayState() {
        return this.d;
    }

    int a() {
        return this.c;
    }

    public boolean isLoop() {
        return this.e;
    }

    public void setLoop(boolean z) {
        this.e = z;
    }

    public VideoPlayer getPlayer() {
        return this.a;
    }

    public Surface getSurface() {
        return this.b;
    }

    public void setSurface(Surface surface, int i, int i2) {
        this.n = i;
        this.o = i2;
        this.b = surface;
        if (this.a != null) {
            this.a.setSurface(surface, this.n, this.o);
        }
        if (this.c == 1) {
            prepare();
        }
    }

    public String getVideoUri() {
        return this.m;
    }

    public void setVideo(String str) {
        reset();
        this.m = str;
    }

    public String getCachePath() {
        return this.f;
    }

    public void setCachePath(String str) {
        this.f = str;
        this.g = false;
    }

    public boolean isCached() {
        return this.g;
    }

    public void setOnVideoEventListener(OnVideoEventListener onVideoEventListener) {
        this.p = onVideoEventListener;
    }

    public void setOnVideoStateListener(OnVideoStateListener onVideoStateListener) {
        this.q = onVideoStateListener;
    }

    public void setOnVideoCacheListener(OnVideoCacheListener onVideoCacheListener) {
        this.r = onVideoCacheListener;
    }

    public long getStartMs() {
        return this.l;
    }

    public void setStartMs(long j) {
        this.l = j;
    }

    public void prepare() {
        int i = 0;
        if (!TextUtils.isEmpty(this.m)) {
            this.c = 1;
            if (this.b != null && !b()) {
                if (this.a == null) {
                    this.a = VideoPlayer.create();
                    this.j = 100;
                }
                if (this.a == null) {
                    onPlayerError(7, 0);
                    return;
                }
                if (TextUtils.isEmpty(this.f)) {
                    this.a.setDataSource(this.m, this.l);
                } else {
                    File file = new File(this.f);
                    if (file.exists()) {
                        this.a.setDataSource(this.f, this.l);
                        this.g = true;
                    } else {
                        file = file.getParentFile();
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        this.a.setDataSource(this.m, null, this.l, 0, this.f + FileType.TEMP);
                    }
                }
                this.a.setSurface(this.b, this.n, this.o);
                this.a.setOnPreparedListener(new j(this));
                this.a.setOnFirstFrameListener(new k(this));
                this.a.setOnErrorListener(new l(this));
                if (this.k != this.j) {
                    this.j = this.k;
                    this.a.setVolumeRate((double) (((float) this.k) / 100.0f));
                }
                this.a.setOnInfoListener(new m(this));
                this.a.setOnCompletionListener(new n(this));
                this.a.prepareAsync();
                VideoPlayer videoPlayer = this.a;
                if (this.e) {
                    i = -1;
                }
                videoPlayer.setLoop(i);
            }
        }
    }

    public void onFirstFrameDone() {
        onVideoStateChange(4);
    }

    public void onPlayerPrepared() {
        this.c = 2;
        if (this.p != null) {
            this.p.onVideoPrepared(this);
        }
        if (this.h) {
            start();
        }
    }

    public void onPlayerError(int i, int i2) {
        switch (i) {
            case 2:
                this.c = 0;
                new File(this.f).delete();
                prepare();
                break;
            case 3:
            case 4:
                if (!HttpUtils.isNetworkConnected(QsbkApp.getInstance())) {
                    reset();
                    break;
                }
                this.c = 0;
                prepare();
                break;
        }
        if (this.p != null) {
            this.p.onVideoError(this, i, i2);
        }
    }

    public void onPlayerBuffering(int i) {
        if (this.p != null) {
            this.p.onVideoBuffering(this, i);
        }
    }

    public void onPlayerCompletion() {
        if (!TextUtils.isEmpty(this.f)) {
            onPlayerCached();
        }
        if (this.p != null) {
            this.p.onVideoCompletion(this);
        }
        if (!this.e) {
            this.c = 5;
            onVideoStateChange(0);
        }
    }

    public void onPlayerCached() {
        this.g = true;
        File file = new File(this.f + FileType.TEMP);
        File file2 = new File(this.f);
        if (!file2.exists()) {
            FileUtils.copyFileAsync(file, file2, null, true);
        }
    }

    private boolean b() {
        return this.n == 0 || this.o == 0;
    }

    public void start() {
        switch (this.c) {
            case 0:
                prepare();
                this.h = true;
                onVideoStateChange(3);
                return;
            case 1:
                if (!this.h) {
                    this.h = true;
                    onVideoStateChange(3);
                    return;
                }
                return;
            case 3:
                return;
            case 5:
                this.c = 0;
                prepare();
                this.h = true;
                onVideoStateChange(3);
                return;
            default:
                this.c = 3;
                this.h = true;
                if (this.i >= 0) {
                    this.a.seekTo(this.i);
                    this.i = -1;
                }
                this.a.start();
                onVideoStateChange(1);
                return;
        }
    }

    public void pause() {
        if (this.c == 3) {
            this.h = false;
            this.c = 4;
            this.a.pause();
            onVideoStateChange(2);
        } else if (this.h) {
            this.h = false;
            onVideoStateChange(2);
        }
    }

    public void reset() {
        stop();
        this.h = false;
        this.i = -1;
        this.c = 0;
    }

    public void stop() {
        if ((this.c == 3 || this.c == 4) && this.a != null) {
            this.a.stop();
        }
        this.c = 0;
        this.h = false;
        this.i = -1;
        this.l = 0;
        onVideoStateChange(0);
    }

    public void onVideoStateChange(int i) {
        if (i != this.d) {
            this.d = i;
            if (this.q != null) {
                this.q.onVideoState(this, i);
            }
        }
    }

    public void release() {
        this.b = null;
        reset();
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
    }

    public void seekTo(long j) {
        if (this.a == null || this.c < 1) {
            this.i = j;
        } else {
            this.a.seekTo(j);
        }
    }

    public long getDuration() {
        return (this.a == null || this.c < 2) ? -1 : this.a.getDuration();
    }

    public void setVolume(int i) {
        this.k = i;
        if (this.a != null && i != this.j) {
            this.j = i;
            this.a.setVolumeRate((double) (((float) i) / 100.0f));
        }
    }
}
