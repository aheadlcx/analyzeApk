package cn.xiaochuankeji.tieba.push.service;

import android.content.Intent;
import android.os.IBinder;
import cn.xiaochuan.daemon.AbsWorkService;
import cn.xiaochuan.push.c;
import com.izuiyou.a.a.a;
import com.izuiyou.a.a.b;
import java.util.concurrent.TimeUnit;
import rx.d;
import rx.k;

public class DaemonService extends AbsWorkService {
    public static boolean b = false;
    public static k c;
    private static a d;
    private int e = 0;

    public static void b() {
        a.b("Daemon", "reset token,ready to send sync package");
        cn.xiaochuankeji.tieba.push.a.b();
        c.a().a(new Runnable() {
            public void run() {
                if (DaemonService.d != null) {
                    DaemonService.d.c();
                }
            }
        });
    }

    public void onCreate() {
        super.onCreate();
        e();
    }

    private void e() {
        try {
            a.c("Daemon", "thread will restart");
            f();
            this.e++;
            d = new a(new d(this) {
                final /* synthetic */ DaemonService a;

                {
                    this.a = r1;
                }

                public void a(c cVar) {
                    this.a.e = 0;
                    if (!(DaemonService.c == null || DaemonService.c.isUnsubscribed())) {
                        DaemonService.c.unsubscribe();
                    }
                    DaemonService.c = d.a(cVar.a, TimeUnit.MILLISECONDS).a(new rx.b.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void call() {
                            b.c(Thread.currentThread().getId() + " subscribe heartbeat");
                        }
                    }).b(new rx.b.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void call() {
                            b.c(Thread.currentThread().getId() + " cancel heartbeat");
                        }
                    }).a(new rx.b.b<Long>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void call(Object obj) {
                            a((Long) obj);
                        }

                        public void a(Long l) {
                            DaemonService.d.a();
                        }
                    }, new rx.b.b<Throwable>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void call(Object obj) {
                            a((Throwable) obj);
                        }

                        public void a(Throwable th) {
                            a.d("Daemon", th);
                            this.a.a.e();
                        }
                    });
                }

                public void a() {
                    this.a.e = 0;
                    this.a.e();
                }
            });
            d.start();
        } catch (Throwable e) {
            a.a(e);
            if (this.e < 3) {
                c.a().a(new Runnable(this) {
                    final /* synthetic */ DaemonService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.e();
                    }
                }, 10000);
            }
        }
    }

    public void onDestroy() {
        f();
        super.onDestroy();
    }

    public static void c() {
        b = true;
        f();
        AbsWorkService.a();
    }

    private static void f() {
        try {
            if (d != null) {
                d.b();
                d.interrupt();
                d = null;
            }
        } catch (Exception e) {
            a.d("Daemon", e);
        }
        try {
            if (c != null) {
                c.unsubscribe();
                c = null;
            }
        } catch (Exception e2) {
            a.d("Daemon", e2);
        }
    }

    public Boolean a(Intent intent, int i, int i2) {
        return Boolean.valueOf(b);
    }

    public void b(Intent intent, int i, int i2) {
        b.c("startWork");
    }

    public void c(Intent intent, int i, int i2) {
        c();
    }

    public Boolean d(Intent intent, int i, int i2) {
        boolean z = (c == null || c.isUnsubscribed()) ? false : true;
        return Boolean.valueOf(z);
    }

    public IBinder a(Intent intent, Void voidR) {
        a.c("Daemon", "onBind" + intent);
        return null;
    }

    public void a(Intent intent) {
        a.c("Daemon", "onServiceKilled");
        f();
    }
}
