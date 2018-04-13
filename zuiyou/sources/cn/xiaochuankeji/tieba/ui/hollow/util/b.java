package cn.xiaochuankeji.tieba.ui.hollow.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder.RecorderStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder.a;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import rx.d;
import rx.d$a;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;

public class b implements IAudioRecorder {
    private a a;
    private cn.xiaochuankeji.tieba.common.medialib.b b;
    private List<String> c;
    private String d;
    private String e;
    private File f;
    private boolean g;
    private long h;
    private long i;
    private long j;
    private long k;
    private boolean l;
    private Handler m;

    public b() {
        f();
        g();
        h();
    }

    private void f() {
        this.b = new cn.xiaochuankeji.tieba.common.medialib.b(new cn.xiaochuankeji.tieba.common.medialib.a(16000, 1, 2));
        this.b.a(new cn.xiaochuankeji.tieba.common.medialib.b.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(Throwable th) {
                if (this.a.a != null) {
                    this.a.a.a(RecorderStatus.FAILURE);
                }
            }
        });
    }

    @SuppressLint({"HandlerLeak"})
    private void g() {
        this.m = new Handler(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.j = System.currentTimeMillis() - this.a.k;
                if (!this.a.l && this.a.a != null) {
                    if (this.a.i + this.a.j > this.a.h) {
                        this.a.b();
                        this.a.a.a(this.a.i / 1000);
                        this.a.a.a(RecorderStatus.FILLED);
                        return;
                    }
                    this.a.a.a((this.a.i + this.a.j) / 1000);
                    this.a.m.sendMessageDelayed(this.a.m.obtainMessage(0), 1000);
                }
            }
        };
    }

    private void h() {
        this.f = new File(cn.xiaochuankeji.tieba.background.a.e().v(), "audio");
        if (!this.f.exists()) {
            this.f.mkdir();
        }
        this.e = new File(this.f, "output.wav").getAbsolutePath();
        this.c = new LinkedList();
        this.i = 0;
        this.j = 0;
        this.l = false;
        this.g = false;
    }

    private File i() {
        return new File(this.f, "" + System.currentTimeMillis() + ".wav");
    }

    public void a() {
        this.d = i().getAbsolutePath();
        this.k = System.currentTimeMillis();
        this.l = false;
        this.g = false;
        this.b.a(this.d, 1.0f, new cn.xiaochuankeji.tieba.common.medialib.b.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public long a() {
                return 0;
            }
        });
        this.m.sendMessageDelayed(this.m.obtainMessage(0), 1000 - (this.i % 1000));
        if (this.a != null) {
            this.a.a(RecorderStatus.RECORDING);
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.a(RecorderStatus.PAUSE);
        }
        this.j = System.currentTimeMillis() - this.k;
        this.b.a((int) this.j);
        this.l = true;
        this.i += this.j;
        this.c.add(this.d);
    }

    public void c() {
        if (this.a != null) {
            this.a.a(RecorderStatus.PREPARE);
        }
        this.i = 0;
        j();
    }

    public void d() {
        this.m.removeMessages(0);
        this.m = null;
        this.b.b();
        this.b = null;
        j();
    }

    private void j() {
        for (String file : this.c) {
            new File(file).delete();
        }
        this.c.clear();
    }

    public void a(@Nullable a aVar) {
        if (aVar != null) {
            aVar.a(RecorderStatus.PREPARE);
            this.a = aVar;
        }
    }

    public void a(long j) {
        this.h = 1000 * j;
    }

    public d<String> e() {
        return d.b(new d$a<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                if (this.a.g) {
                    jVar.onNext(this.a.e);
                } else if (this.a.c.isEmpty()) {
                    jVar.onError(new Throwable("录制失败"));
                } else {
                    FFmpegMainCaller.concatMedia(new ArrayList(this.a.c), this.a.e, this.a.f.getAbsolutePath() + "/mux.txt");
                    this.a.g = true;
                    jVar.onNext(this.a.e);
                }
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a());
    }
}
