package cn.xiaochuankeji.tieba.background.j;

import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.danmaku.e;
import com.izuiyou.a.a.b;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;

public class d {
    private static d d;
    private cn.xiaochuankeji.tieba.common.d.a a;
    private String b;
    private Executor c = Executors.newSingleThreadExecutor();
    private cn.xiaochuankeji.tieba.common.d.a e = null;
    private e f = null;
    private a g;

    public interface a {
        void a(String str);
    }

    private d() {
    }

    public static d a() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    public void a(String str, a aVar) {
        if (this.b != null) {
            b();
        }
        this.g = aVar;
        this.b = str;
        a(str);
    }

    private void a(String str) {
        this.a = new cn.xiaochuankeji.tieba.common.d.a(BaseApplication.getAppContext());
        this.a.b();
        this.a.a(str);
        this.a.start();
        this.a.a(new IMediaPlayer$OnCompletionListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onCompletion(IMediaPlayer iMediaPlayer) {
                this.a.b();
            }
        });
    }

    public void b() {
        if (this.b != null) {
            if (this.g != null) {
                this.g.a(this.b);
            }
            this.b = null;
            d();
        }
    }

    private void d() {
        if (this.a != null) {
            this.e = this.a;
            this.a = null;
            this.c.execute(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    b.e("上个player被release!");
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

    public long c() {
        if (this.a == null) {
            return -1;
        }
        int currentPosition = this.a.a().getCurrentPosition();
        return currentPosition > 0 ? (long) currentPosition : 0;
    }
}
