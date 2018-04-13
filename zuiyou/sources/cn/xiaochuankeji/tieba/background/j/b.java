package cn.xiaochuankeji.tieba.background.j;

import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.danmaku.e;
import cn.xiaochuankeji.tieba.common.d.a;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;

public class b {
    private static b d;
    private a a;
    private String b;
    private Executor c = Executors.newSingleThreadExecutor();
    private a e = null;
    private e f = null;
    private a g = a.a();

    private b() {
    }

    public static b a() {
        if (d == null) {
            d = new b();
        }
        return d;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(this.b)) {
            i();
        }
        this.b = str;
        this.f = new e(0, str);
        boolean b = this.f.b();
        String c = this.f.c();
        if (b) {
            c(c);
            return;
        }
        if (this.g != null) {
            this.g.c();
        }
        this.f.a(new e.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(e eVar, String str) {
                if (this.a.g != null) {
                    this.a.g.a(true);
                }
                if (this.a.g.d()) {
                    this.a.c(str);
                } else {
                    this.a.b = null;
                }
            }

            public void b(e eVar, String str) {
                if (this.a.g != null) {
                    this.a.g.a(false);
                    cn.htjyb.c.a.b.a(eVar.c());
                    this.a.g.b();
                    this.a.b = null;
                }
            }
        });
        this.f.d();
    }

    public String b() {
        return this.b;
    }

    private void c(String str) {
        this.a = new a(BaseApplication.getAppContext());
        this.a.a(str);
        this.a.start();
        this.a.a(new IMediaPlayer$OnPreparedListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onPrepared(IMediaPlayer iMediaPlayer) {
                if (this.a.g != null && this.a.a != null) {
                    this.a.g.a(0, this.a.a.getDuration());
                }
            }
        });
        this.a.a(new IMediaPlayer$OnCompletionListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onCompletion(IMediaPlayer iMediaPlayer) {
                this.a.i();
            }
        });
        this.a.a(new IMediaPlayer$OnErrorListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (this.a.f != null) {
                    cn.htjyb.c.a.b.a(this.a.f.c());
                }
                return false;
            }
        });
    }

    public boolean b(String str) {
        return this.b != null && this.b.equals(str);
    }

    public boolean c() {
        if (this.a == null || !this.a.isPlaying()) {
            return false;
        }
        return true;
    }

    public boolean d() {
        return this.f == null ? false : this.f.f();
    }

    public int e() {
        return this.a == null ? 0 : this.a.getCurrentPosition();
    }

    public int f() {
        return this.a == null ? 0 : this.a.getDuration();
    }

    public void g() {
        if (this.a != null && this.a.canPause()) {
            this.a.pause();
        }
        if (this.g != null) {
            this.g.a(this.b);
        }
    }

    public void h() {
        if (this.a != null) {
            this.a.start();
            if (this.g != null) {
                this.g.a(e(), this.a.getDuration());
            }
        }
    }

    public void i() {
        if (!TextUtils.isEmpty(this.b)) {
            if (this.g != null) {
                this.g.b(this.b);
            }
            this.g.b();
            this.b = null;
            if (this.a != null) {
                this.e = this.a;
                this.a = null;
                this.c.execute(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.e.g();
                    }
                });
            }
            if (this.f != null) {
                this.f.e();
                this.f.a(null);
                this.f = null;
            }
        }
    }
}
