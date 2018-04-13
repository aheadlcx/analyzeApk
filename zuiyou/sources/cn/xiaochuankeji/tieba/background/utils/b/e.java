package cn.xiaochuankeji.tieba.background.utils.b;

import android.os.Handler;
import android.os.Looper;
import cn.xiaochuankeji.tieba.background.h.i;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.io.File;

public class e {
    public static String a = "BlockUpload";
    private a b;
    private i c;
    private LocalMedia d;
    private File e;
    private long f;
    private String g;
    private cn.xiaochuankeji.tieba.background.h.i.a h;
    private Handler i;
    private b j;
    private boolean k = false;

    public interface a {
        void a(boolean z, boolean z2, long j, String str, String str2);
    }

    public interface b {
        void a(int i, int i2);
    }

    public e(LocalMedia localMedia, a aVar) {
        this.d = localMedia;
        this.b = aVar;
        this.c = i.a();
        this.i = new Handler(Looper.getMainLooper());
    }

    public void a() {
        this.e = new File(this.d.path);
        this.f = this.e.length();
        if (this.f <= 4096) {
            c();
        } else {
            b();
        }
    }

    private void b() {
        new g(this.e, new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (!z) {
                    this.a.b.a(false, false, 0, null, str);
                } else if (z2) {
                    this.a.c();
                } else {
                    this.a.d();
                }
            }
        }).a();
    }

    private void c() {
        new Thread(new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g = cn.htjyb.c.a.b.b(this.a.e);
                this.a.i.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        new a(this.a.a.g, new a(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z, String str, String str2, String str3) {
                                boolean z2 = false;
                                if (z) {
                                    if (!(str == null || str.trim().equals(""))) {
                                        z2 = true;
                                    }
                                    if (z2) {
                                        this.a.a.a.k = true;
                                        this.a.a.a.a(str, str2);
                                        return;
                                    }
                                    this.a.a.a.d();
                                    return;
                                }
                                this.a.a.a.b.a(false, false, 0, null, str3);
                            }
                        }).a();
                    }
                });
            }
        }, a).start();
    }

    private void d() {
        this.h = this.c.a(this.d.path);
        if (this.h == null) {
            e();
        } else {
            f();
        }
    }

    private void e() {
        new c(this.e.length(), new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z, long j, int i, String str) {
                if (z) {
                    this.a.h = new cn.xiaochuankeji.tieba.background.h.i.a();
                    this.a.h.a = this.a.d.path;
                    this.a.h.b = j;
                    this.a.h.c = i;
                    this.a.c.a(this.a.h);
                    this.a.f();
                    return;
                }
                this.a.b.a(false, false, 0, null, str);
            }
        }).a();
    }

    private void f() {
        d dVar = new d(this.e, this.h.b, this.h.d, this.h.c, new b(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z, int i, int i2, String str) {
                if (z) {
                    this.a.g();
                } else if (-2 == i2) {
                    this.a.c.b(this.a.h.a);
                    this.a.e();
                } else {
                    this.a.c.a(this.a.h.a, i);
                    this.a.b.a(false, false, 0, null, str);
                }
            }
        });
        dVar.a(new cn.xiaochuankeji.tieba.background.utils.b.d.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                if (this.a.j != null) {
                    this.a.j.a(i, i2);
                }
            }
        });
        dVar.a();
    }

    private void g() {
        new b(this.h.b, this.d.mimeType, new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str, String str2, int i, String str3) {
                boolean z2 = false;
                if (z) {
                    boolean z3 = (str == null || str.trim().equals("")) ? false : true;
                    if (!(str2 == null || str2.trim().equals(""))) {
                        z2 = true;
                    }
                    if (z3 && r2) {
                        this.a.a(str, str2);
                    }
                } else if (-2 == i || -3 == i || -4 == i) {
                    this.a.c.b(this.a.d.path);
                    this.a.e();
                } else {
                    this.a.b.a(false, false, 0, null, str3);
                }
            }
        }).a();
    }

    private void a(final String str, String str2) {
        new f(str, str2, new a(this) {
            final /* synthetic */ e b;

            public void a(boolean z, long j, String str) {
                if (z) {
                    this.b.c.b(this.b.d.path);
                    this.b.b.a(true, this.b.k, j, str, null);
                    return;
                }
                this.b.b.a(false, false, 0, null, str);
            }
        }).a();
    }

    public void a(b bVar) {
        this.j = bVar;
    }
}
