package cn.xiaochuankeji.tieba.ui.voice.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.common.d.a;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;

public class d implements IMediaPlayer$OnCompletionListener, IMediaPlayer$OnErrorListener, IMediaPlayer$OnInfoListener, IMediaPlayer$OnPreparedListener {
    private static volatile d a;
    private Logger b = Logger.getLogger("voiceplayer");
    private Context c = BaseApplication.getAppContext();
    private a d = new a(this.c);
    private c e = new c();
    private List<b> f = new ArrayList();
    @SuppressLint({"HandlerLeak"})
    private Handler g = new Handler(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.f != null && !this.a.f.isEmpty() && this.a.h) {
                this.a.e.f = (long) this.a.d.getCurrentPosition();
                for (int i = 0; i < this.a.f.size(); i++) {
                    ((b) this.a.f.get(i)).a(this.a.e);
                }
                this.a.g.sendMessageDelayed(this.a.g.obtainMessage(0), 1000);
            }
        }
    };
    private boolean h = false;
    private boolean i = true;
    private long j;

    private d() {
        this.d.a((IMediaPlayer$OnPreparedListener) this);
        this.d.a((IMediaPlayer$OnCompletionListener) this);
        this.d.a((IMediaPlayer$OnInfoListener) this);
        this.d.a((IMediaPlayer$OnErrorListener) this);
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public void a(b bVar) {
        this.f.add(bVar);
    }

    public void b(b bVar) {
        this.f.remove(bVar);
    }

    public c b() {
        return this.e;
    }

    public void c() {
        this.b.info("start");
        this.d.a(this.e.d);
        this.d.start();
        this.e.c = 1;
        this.g.sendMessageDelayed(this.g.obtainMessage(0), 1000);
        this.h = true;
        g();
    }

    public void d() {
        this.b.info("release");
        this.d.g();
        this.g.removeMessages(0);
        this.h = false;
        this.e.f = 0;
        this.e.c = 3;
        g();
        this.d.a(this.e.d);
        this.d.start();
        this.g.sendMessageDelayed(this.g.obtainMessage(0), 1000);
        this.h = true;
    }

    public void e() {
        this.b.info("release");
        this.d.g();
        this.g.removeMessages(0);
        this.h = false;
        this.e.c = 0;
        this.e.f = 0;
        g();
    }

    public void f() {
        this.b.info("pause");
        if (this.d.isPlaying()) {
            this.d.pause();
            this.g.removeMessages(0);
            this.h = false;
            this.e.f = (long) this.d.getCurrentPosition();
            this.e.c = 2;
            g();
        }
    }

    public void g() {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                ((b) this.f.get(i)).a(this.e);
            }
        }
    }

    public boolean h() {
        return this.d.isPlaying();
    }

    public boolean i() {
        return this.d.canPause() && this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void onPrepared(IMediaPlayer iMediaPlayer) {
        this.b.info("prepare");
        this.d.start();
        this.e.c = 1;
        g();
    }

    public void onCompletion(IMediaPlayer iMediaPlayer) {
        this.b.info("complete");
        this.e.c = 0;
        this.e.f = this.e.e;
        this.g.removeMessages(0);
        this.h = false;
        g();
    }

    public long j() {
        return this.j;
    }

    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        switch (i) {
            case 701:
                this.j = System.currentTimeMillis();
                break;
            case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                this.j = System.currentTimeMillis() - this.j;
                break;
        }
        return false;
    }

    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
        a.a().a(this.e.a, 0, this.j, false);
        return false;
    }
}
