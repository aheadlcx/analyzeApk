package cn.xiaochuankeji.tieba.ui.chat.a;

import android.media.AudioManager;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.utils.g;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;

public class c {
    private cn.xiaochuankeji.tieba.common.d.a a;
    private cn.xiaochuankeji.tieba.ui.voice.c.a b;
    private a c;

    public interface a {
        @MainThread
        void a();

        @MainThread
        void a(cn.xiaochuankeji.tieba.ui.voice.c.a aVar);

        @MainThread
        void b();
    }

    public void a() {
        if (this.a != null) {
            this.a.b(true);
            this.a = null;
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.f();
            this.b = null;
        }
        if (this.c != null) {
            this.c.a(this.b);
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.voice.c.a aVar) {
        if (this.a == null) {
            this.a = new cn.xiaochuankeji.tieba.common.d.a(BaseApplication.getAppContext());
        }
        if (this.a == null) {
            return;
        }
        if (aVar == null) {
            c();
        } else if (!aVar.equals(this.b)) {
            this.b = aVar;
            b(this.b);
        } else if (this.a.isPlaying()) {
            this.a.pause();
        } else if (this.a.getBufferPercentage() > 50) {
            this.a.seekTo(0);
            this.a.start();
        } else {
            this.a.g();
            this.a = null;
            this.b = null;
            a(aVar);
        }
    }

    private void b(final cn.xiaochuankeji.tieba.ui.voice.c.a aVar) {
        if (aVar != null && !TextUtils.isEmpty(aVar.a)) {
            c();
            this.a.a(new IMediaPlayer$OnInfoListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    switch (i) {
                        case 701:
                            if (iMediaPlayer.isPlaying() && this.a.c != null) {
                                this.a.c.a();
                                break;
                            }
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                            if (iMediaPlayer.isPlaying() && this.a.c != null) {
                                this.a.c.b();
                                break;
                            }
                    }
                    return true;
                }
            });
            this.a.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ c b;

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    this.b.c();
                    if (this.b.c != null) {
                        if (this.b.b != null) {
                            this.b.b.c = 0;
                        }
                        this.b.c.a(aVar);
                    }
                }
            });
            this.a.a(new IMediaPlayer$OnErrorListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    this.a.a = null;
                    this.a.b = null;
                    return false;
                }
            });
            this.a.a(Uri.parse(aVar.a));
            this.a.start();
            AudioManager audioManager = (AudioManager) BaseApplication.getAppContext().getSystemService("audio");
            if (audioManager != null && audioManager.getStreamVolume(3) == 0) {
                g.b("请调大手机音量后播放");
            }
        }
    }

    private void c() {
        if (this.a != null) {
            this.a.g();
        }
    }

    public boolean a(long j) {
        if (this.a == null || this.b == null || j != this.b.c || !this.a.isPlaying()) {
            return false;
        }
        return true;
    }

    public void a(a aVar) {
        if (this.c != null) {
            this.c.a(null);
        }
        this.c = aVar;
    }
}
