package cn.xiaochuankeji.tieba.common.d;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.MediaController.MediaPlayerControl;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import com.iflytek.cloud.SpeechConstant;
import java.io.IOException;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnBufferingUpdateListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnSeekCompleteListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

public class a implements MediaPlayerControl {
    private static final String b = a.class.getName();
    private IMediaPlayer$OnPreparedListener A = new IMediaPlayer$OnPreparedListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onPrepared(IMediaPlayer iMediaPlayer) {
            this.a.j = 2;
            if (this.a.t != null) {
                this.a.t.onPrepared(this.a.q);
            }
            if (this.a.l != 0) {
                this.a.seekTo(this.a.l);
            }
            if (this.a.m != 1.0f) {
                this.a.b(this.a.m);
            }
            if (this.a.k == 3) {
                this.a.start();
            }
        }
    };
    private IMediaPlayer$OnSeekCompleteListener B = new IMediaPlayer$OnSeekCompleteListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            if (this.a.x != null) {
                this.a.x.onSeekComplete(iMediaPlayer);
            }
        }
    };
    private IMediaPlayer$OnCompletionListener C = new IMediaPlayer$OnCompletionListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onCompletion(IMediaPlayer iMediaPlayer) {
            this.a.j = 5;
            this.a.k = 5;
            if (this.a.u != null) {
                this.a.u.onCompletion(this.a.q);
            }
        }
    };
    private IMediaPlayer$OnErrorListener D = new IMediaPlayer$OnErrorListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            this.a.j = -1;
            this.a.k = -1;
            boolean z = false;
            if (this.a.s && iMediaPlayer.getCurrentPosition() <= 0 && this.a.c()) {
                z = true;
            }
            if (z || this.a.w == null) {
                return true;
            }
            return this.a.w.onError(iMediaPlayer, i, i2);
        }
    };
    private IMediaPlayer$OnBufferingUpdateListener E = new IMediaPlayer$OnBufferingUpdateListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            this.a.n = i;
            if (this.a.z != null) {
                this.a.z.onBufferingUpdate(iMediaPlayer, i);
            }
        }
    };
    private IMediaPlayer$OnInfoListener F = new IMediaPlayer$OnInfoListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (this.a.y != null) {
                this.a.y.onInfo(iMediaPlayer, i, i2);
            }
            return true;
        }
    };
    IMediaPlayer$OnVideoSizeChangedListener a = new IMediaPlayer$OnVideoSizeChangedListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            if (this.a.v != null) {
                this.a.v.onVideoSizeChanged(iMediaPlayer, i, i2, i3, i4);
            }
        }
    };
    private final Context c;
    private Uri d;
    private IMediaDataSource e;
    private int f;
    private String g;
    private int h;
    private long i;
    private int j = 0;
    private int k = 0;
    private int l;
    private float m;
    private int n;
    private SurfaceHolder o;
    private Surface p;
    private IMediaPlayer q;
    private boolean r;
    private boolean s = true;
    private IMediaPlayer$OnPreparedListener t;
    private IMediaPlayer$OnCompletionListener u;
    private IMediaPlayer$OnVideoSizeChangedListener v;
    private IMediaPlayer$OnErrorListener w;
    private IMediaPlayer$OnSeekCompleteListener x;
    private IMediaPlayer$OnInfoListener y;
    private IMediaPlayer$OnBufferingUpdateListener z;

    public a(Context context) {
        this.c = context;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 1.0f;
    }

    public MediaPlayerControl a() {
        return this;
    }

    public void a(SurfaceHolder surfaceHolder) {
        this.o = surfaceHolder;
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.p = new Surface(surfaceTexture);
    }

    public void a(String str) {
        a(Uri.parse(str));
    }

    public void a(Uri uri) {
        this.d = uri;
        this.f = IjkMediaPlayer.SDL_FCC_RV32;
        this.g = null;
        this.h = 0;
    }

    public void a(float f) {
        if (this.q != null) {
            this.q.setVolume(f, f);
        }
    }

    public void b() {
        this.r = true;
    }

    public void a(boolean z) {
        this.s = z;
    }

    private void i() throws IOException {
        if (this.r) {
            this.q = k();
        } else {
            this.q = j();
        }
        this.q.setOnPreparedListener(this.A);
        this.q.setOnVideoSizeChangedListener(this.a);
        this.q.setOnCompletionListener(this.C);
        this.q.setOnErrorListener(this.D);
        this.q.setOnBufferingUpdateListener(this.E);
        this.q.setOnInfoListener(this.F);
        this.q.setOnSeekCompleteListener(this.B);
        this.q.setAudioStreamType(3);
        this.q.setScreenOnWhilePlaying(true);
        if (this.d != null) {
            this.q.setDataSource(this.c, this.d);
        } else if (this.e != null) {
            this.q.setDataSource(this.e);
        }
        if (this.o != null) {
            this.q.setDisplay(this.o);
        } else if (this.p != null) {
            this.q.setSurface(this.p);
        }
    }

    public boolean c() {
        if (this.r) {
            return false;
        }
        this.r = true;
        start();
        return true;
    }

    private IMediaPlayer j() {
        IMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setOption(4, "overlay-format", (long) this.f);
        ijkMediaPlayer.setOption(4, "framedrop", 12);
        ijkMediaPlayer.setOption(4, "start-on-prepared", 0);
        ijkMediaPlayer.setOption(1, "reconnect", 1);
        ijkMediaPlayer.setOption(1, SpeechConstant.NET_TIMEOUT, 60000000);
        ijkMediaPlayer.setOption(1, "http-detect-range-support", 1);
        ijkMediaPlayer.setOption(1, "user_agent", "IjkMediaPlayer");
        ijkMediaPlayer.setOption(2, "skip_loop_filter", 0);
        ijkMediaPlayer.setOption(4, "soundtouch", 1);
        if (!TextUtils.isEmpty(this.g)) {
            ijkMediaPlayer.setOption(4, "iformat", this.g);
        }
        if (this.h > 0) {
            ijkMediaPlayer.setOption(1, FFmpegMediaMetadataRetriever.METADATA_KEY_FRAMERATE, (long) this.h);
        }
        GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
        if (C != null && C.enableVideoPlayCodec == 1) {
            ijkMediaPlayer.setOption(4, "mediacodec", 1);
            ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1);
            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1);
            ijkMediaPlayer.setSpeed(1.0f);
        }
        return ijkMediaPlayer;
    }

    private IMediaPlayer k() {
        return new AndroidMediaPlayer();
    }

    public void d() {
        if (this.d != null || this.e != null) {
            b(false);
            try {
                this.i = -1;
                this.n = 0;
                i();
                this.q.prepareAsync();
                this.j = 1;
            } catch (Throwable e) {
                Log.w(b, "Unable to open content: " + this.d, e);
                this.j = -1;
                this.k = -1;
                if (this.w != null) {
                    this.w.onError(this.q, 1, 0);
                }
            } catch (Throwable e2) {
                Log.w(b, "Unable to open content: " + this.d, e2);
                this.j = -1;
                this.k = -1;
                if (this.w != null) {
                    this.w.onError(this.q, 1, 0);
                }
            }
        }
    }

    public void start() {
        if (h()) {
            this.q.start();
            this.j = 3;
            return;
        }
        d();
        this.k = 3;
    }

    public boolean e() {
        return this.d != null && ("http".equalsIgnoreCase(this.d.getScheme()) || "https".equalsIgnoreCase(this.d.getScheme()));
    }

    public void pause() {
        if (h() && this.q.isPlaying()) {
            this.q.pause();
            this.j = 4;
        }
        this.k = 4;
    }

    public void f() {
        if (this.q != null) {
            this.q.stop();
        }
    }

    public void g() {
        b(true);
    }

    public void b(boolean z) {
        if (this.q != null) {
            this.q.reset();
            this.q.release();
            this.q = null;
            this.o = null;
            if (this.p != null) {
                this.p.release();
                this.p = null;
            }
            this.j = 0;
            if (z) {
                this.k = 0;
            }
        }
    }

    public int getDuration() {
        if (!h()) {
            this.i = -1;
            return (int) this.i;
        } else if (this.i > 0) {
            return (int) this.i;
        } else {
            this.i = this.q.getDuration();
            return (int) this.i;
        }
    }

    public int getCurrentPosition() {
        if (h()) {
            return (int) this.q.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int i) {
        if (h()) {
            this.q.seekTo((long) i);
            this.l = 0;
            return;
        }
        this.l = i;
    }

    public void b(float f) {
        if (h()) {
            if (this.q instanceof IjkMediaPlayer) {
                ((IjkMediaPlayer) this.q).setSpeed(f);
            }
            this.m = 0.0f;
            return;
        }
        this.m = f;
    }

    public boolean isPlaying() {
        return h() && this.q.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.q != null) {
            return this.n;
        }
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public boolean h() {
        return (this.q == null || this.j == -1 || this.j == 0 || this.j == 1) ? false : true;
    }

    public void a(IMediaPlayer$OnPreparedListener iMediaPlayer$OnPreparedListener) {
        this.t = iMediaPlayer$OnPreparedListener;
    }

    public void a(IMediaPlayer$OnCompletionListener iMediaPlayer$OnCompletionListener) {
        this.u = iMediaPlayer$OnCompletionListener;
    }

    public void a(IMediaPlayer$OnVideoSizeChangedListener iMediaPlayer$OnVideoSizeChangedListener) {
        this.v = iMediaPlayer$OnVideoSizeChangedListener;
    }

    public void a(IMediaPlayer$OnErrorListener iMediaPlayer$OnErrorListener) {
        this.w = iMediaPlayer$OnErrorListener;
    }

    public void a(IMediaPlayer$OnSeekCompleteListener iMediaPlayer$OnSeekCompleteListener) {
        this.x = iMediaPlayer$OnSeekCompleteListener;
    }

    public void a(IMediaPlayer$OnInfoListener iMediaPlayer$OnInfoListener) {
        this.y = iMediaPlayer$OnInfoListener;
    }
}
