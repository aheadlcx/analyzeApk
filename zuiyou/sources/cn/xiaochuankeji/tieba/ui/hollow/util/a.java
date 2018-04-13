package cn.xiaochuankeji.tieba.ui.hollow.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.MediaController.MediaPlayerControl;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import com.izuiyou.a.a.b;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;

public class a implements IAudioPlayer {
    private MediaPlayerControl a;
    private cn.xiaochuankeji.tieba.common.d.a b;
    private PlayerStatus c;
    private cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.a d;
    private String e;
    private long f;
    private boolean g;
    private long h;
    private Handler i;

    public a(Context context) {
        a(context);
        f();
    }

    private void a(Context context) {
        this.b = new cn.xiaochuankeji.tieba.common.d.a(context);
        this.b.a(new IMediaPlayer$OnInfoListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                switch (i) {
                    case 701:
                        b.c("CACHE");
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                        this.a.f = this.a.f == 0 ? (long) this.a.b.getDuration() : this.a.f;
                        b.c("END -> AudioDuration : " + this.a.f);
                        this.a.g();
                        break;
                    case 10002:
                        b.c("START");
                        this.a.g();
                        break;
                }
                return false;
            }
        });
        this.b.a(new IMediaPlayer$OnCompletionListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCompletion(IMediaPlayer iMediaPlayer) {
                b.c("OnCompletion -> playListener : " + this.a.d);
                this.a.c = PlayerStatus.END;
                this.a.h();
                if (this.a.d != null) {
                    this.a.d.a(PlayerStatus.END);
                }
            }
        });
        this.b.a(new IMediaPlayer$OnErrorListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                g.a("哇哦，音频文件坏了");
                return false;
            }
        });
        this.a = this.b.a();
    }

    @SuppressLint({"HandlerLeak"})
    private void f() {
        this.i = new Handler(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                if (!this.a.g && this.a.d != null) {
                    long currentPosition = this.a.h == 0 ? (long) this.a.a.getCurrentPosition() : this.a.h;
                    b.c("PresentDuration : " + currentPosition);
                    this.a.d.a((this.a.f - currentPosition) / 1000);
                    this.a.h = 0;
                    this.a.i.sendMessageDelayed(this.a.i.obtainMessage(0), 1000);
                }
            }
        };
        this.g = true;
    }

    public void a() {
        b.c("start");
        switch (this.c) {
            case PREPARE:
                if (this.d != null) {
                    this.d.a(PlayerStatus.LOADING);
                }
                this.b.a(this.e);
                this.b.start();
                return;
            case END:
                this.a.start();
                return;
            default:
                return;
        }
    }

    private void g() {
        if (this.g) {
            if (this.d != null) {
                this.d.a(PlayerStatus.PLAYING);
            }
            this.g = false;
            this.i.sendMessageDelayed(this.i.obtainMessage(0), 1000);
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.a(PlayerStatus.PAUSE);
        }
        this.h = (long) this.a.getCurrentPosition();
        this.b.pause();
        h();
    }

    public void c() {
        b.c("restart");
        this.a.start();
        g();
    }

    public void d() {
        if (this.d != null) {
            this.d.a(PlayerStatus.END);
        }
        this.c = PlayerStatus.PREPARE;
        this.h = 0;
        this.g = true;
        if (this.b != null) {
            this.b.g();
        }
    }

    public void e() {
        if (this.b != null) {
            this.b.g();
        }
        this.i.removeMessages(0);
    }

    private void h() {
        this.g = true;
    }

    public void a(AudioDataBean audioDataBean, cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.a aVar) {
        if (audioDataBean != null && aVar != null) {
            b(audioDataBean.url, audioDataBean.dur * 1000, aVar);
        }
    }

    public void a(String str, long j, cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.a aVar) {
        if (str != null && aVar != null) {
            b(str, 1000 * j, aVar);
        }
    }

    private void b(String str, long j, cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.a aVar) {
        b.c("AUDIO_SOURCE : " + str + "  playListener : " + aVar);
        i();
        aVar.a(PlayerStatus.PREPARE);
        this.c = PlayerStatus.PREPARE;
        this.d = aVar;
        this.f = j;
        this.e = str;
        this.b.g();
        this.g = true;
        this.h = 0;
    }

    private void i() {
        if (this.d != null) {
            this.d.a(PlayerStatus.END);
        }
    }
}
