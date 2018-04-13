package cn.xiaochuankeji.tieba.background.upload;

import android.os.Handler;
import android.os.Looper;
import cn.xiaochuankeji.tieba.api.upload.a;
import cn.xiaochuankeji.tieba.d.b;
import cn.xiaochuankeji.tieba.json.ConvertImageIdJson;
import cn.xiaochuankeji.tieba.json.ConvertMediaInfo;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class j {
    private a a = new a();
    private d b = new d();
    private k c = new k();
    private LocalMedia d;
    private List<Long> e = new ArrayList();
    private List<Long> f = new ArrayList();
    private HashMap<String, LocalMedia> g = new HashMap();
    private b h;
    private f i;
    private volatile Exception j;
    private volatile int k;
    private Handler l;
    private volatile boolean m;

    public void a(final List<LocalMedia> list, final String str, b bVar, f fVar) {
        this.h = bVar;
        this.i = fVar;
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new FutureTask(new Callable<Object>(this) {
            final /* synthetic */ j c;

            public Object call() throws Exception {
                this.c.b();
                int i = 0;
                while (i < list.size()) {
                    if (this.c.m) {
                        break;
                    }
                    this.c.k = i;
                    this.c.d = (LocalMedia) list.get(i);
                    try {
                        if (this.c.d.type == 1) {
                            this.c.c();
                        } else {
                            if (this.c.d != null && this.c.d.rotate == 0) {
                                this.c.d.rotate = b.b(this.c.d.path);
                            }
                            this.c.h();
                        }
                        i++;
                    } catch (Exception e) {
                        this.c.j = e;
                    }
                }
                if (!this.c.m) {
                    if (this.c.j != null) {
                        this.c.l.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.c.i != null) {
                                    this.a.c.i.a(this.a.c.j.getMessage());
                                }
                            }
                        });
                    } else {
                        this.c.e.clear();
                        this.c.f.clear();
                        this.c.g.clear();
                        try {
                            this.c.a(str, list);
                        } catch (Exception e2) {
                            this.c.j = e2;
                        }
                        if (!this.c.m) {
                            if (this.c.j == null) {
                                this.c.l.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        if (this.a.c.i != null) {
                                            this.a.c.i.a(this.a.c.e, this.a.c.f, this.a.c.g);
                                        }
                                    }
                                });
                            } else {
                                this.c.l.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        if (this.a.c.j != null && this.a.c.i != null) {
                                            this.a.c.i.a(this.a.c.j.getMessage());
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
                return null;
            }
        }));
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
        if (this.c != null) {
            this.c.a(str);
        }
    }

    public void a(b bVar) {
        this.b.a(bVar);
        this.c.a(bVar);
    }

    private void b() {
        this.l = new Handler(Looper.getMainLooper());
        this.m = false;
        this.j = null;
        this.k = 0;
    }

    public void a() {
        this.m = true;
        this.b.b();
        this.c.a();
        e.b().a();
        this.h = null;
        this.i = null;
        this.b.a(null);
        this.c.a(null);
    }

    private void a(String str, final List<LocalMedia> list) throws Exception {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            LocalMedia localMedia = (LocalMedia) list.get(i);
            ConvertMediaInfo convertMediaInfo = new ConvertMediaInfo();
            convertMediaInfo.fmt = localMedia.fmt;
            convertMediaInfo.width = localMedia.width;
            convertMediaInfo.height = localMedia.height;
            convertMediaInfo.md5 = localMedia.md5;
            convertMediaInfo.resId = localMedia.resId;
            convertMediaInfo.resType = localMedia.resType;
            convertMediaInfo.rotate = localMedia.rotate;
            convertMediaInfo.uri = localMedia.uri;
            convertMediaInfo.videoThumbUri = localMedia.videoThumbUrl;
            arrayList.add(convertMediaInfo);
        }
        this.a.a(arrayList, str).b(new rx.j<ConvertImageIdJson>(this) {
            final /* synthetic */ j b;

            public /* synthetic */ void onNext(Object obj) {
                a((ConvertImageIdJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.j = new UploadException(th);
            }

            public void a(ConvertImageIdJson convertImageIdJson) {
                if (convertImageIdJson.convertMediaMap == null || convertImageIdJson.convertMediaMap.size() == 0) {
                    this.b.j = new UploadException("json convert to map error");
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    LocalMedia localMedia = (LocalMedia) list.get(i);
                    ConvertMediaInfo convertMediaInfo = (ConvertMediaInfo) convertImageIdJson.convertMediaMap.get(localMedia.resId);
                    if (convertMediaInfo != null) {
                        if (localMedia.type == 1) {
                            this.b.e.add(Long.valueOf(convertMediaInfo.mediaServerId));
                        }
                        localMedia.id = convertMediaInfo.mediaServerId;
                        this.b.f.add(Long.valueOf(convertMediaInfo.mediaServerId));
                        this.b.g.put(localMedia.path, localMedia);
                    }
                }
            }
        });
        if (this.j != null) {
            throw this.j;
        }
    }

    private void c() throws Exception {
        Exception exception = null;
        try {
            if (e()) {
                return;
            }
        } catch (Exception e) {
        }
        if (!this.m) {
            Exception exception2;
            try {
                g();
                exception2 = null;
            } catch (Exception e2) {
                exception2 = e2;
            }
            if (!this.m && exception2 != null) {
                if (!(exception2 instanceof UploadException)) {
                    try {
                        d();
                    } catch (Exception e3) {
                        exception = e3;
                    }
                    if (exception != null) {
                        throw exception;
                    }
                } else if (((UploadException) exception2).isNeedRetry) {
                    try {
                        d();
                    } catch (Exception e4) {
                        exception = e4;
                    }
                    if (exception != null) {
                        throw exception;
                    }
                } else {
                    throw exception2;
                }
            }
        }
    }

    private void d() throws Exception {
        this.c.b(this.d, new b(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                final long j3 = j;
                final long j4 = j2;
                this.a.l.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 c;

                    public void run() {
                        if (this.c.a.h != null) {
                            this.c.a.h.a(j3, j4, this.c.a.k);
                        }
                    }
                });
            }
        });
    }

    private boolean e() throws Exception {
        this.b.a();
        if (this.b.a(this.d)) {
            return true;
        }
        return false;
    }

    private boolean f() throws Exception {
        this.b.a();
        if (this.b.b(this.d)) {
            return true;
        }
        return false;
    }

    private void g() throws Exception {
        this.b.a();
        this.b.a(this.d, new b(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                final long j3 = j;
                final long j4 = j2;
                this.a.l.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 c;

                    public void run() {
                        if (this.c.a.h != null) {
                            this.c.a.h.a(j3, j4, this.c.a.k);
                        }
                    }
                });
            }
        });
    }

    private void h() throws Exception {
        try {
            if (f()) {
                return;
            }
        } catch (Exception e) {
        }
        if (!this.m) {
            Exception exception;
            try {
                this.b.a();
                this.b.b(this.d, new b(this) {
                    final /* synthetic */ j a;

                    {
                        this.a = r1;
                    }

                    public void a(long j, long j2, int i) {
                        final long j3 = j;
                        final long j4 = j2;
                        this.a.l.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 c;

                            public void run() {
                                if (this.c.a.h != null) {
                                    this.c.a.h.a(j3, j4, this.c.a.k);
                                }
                            }
                        });
                    }
                });
                exception = null;
            } catch (Exception e2) {
                exception = e2;
            }
            if (!this.m) {
                if (exception != null) {
                    try {
                        this.c.a(this.d, new b(this) {
                            final /* synthetic */ j a;

                            {
                                this.a = r1;
                            }

                            public void a(long j, long j2, int i) {
                                final long j3 = j;
                                final long j4 = j2;
                                this.a.l.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass6 c;

                                    public void run() {
                                        if (this.c.a.h != null) {
                                            this.c.a.h.a(j3, j4, this.c.a.k);
                                        }
                                    }
                                });
                            }
                        });
                        exception = null;
                    } catch (Exception e3) {
                        exception = e3;
                    }
                }
                if (exception != null) {
                    throw exception;
                }
            }
        }
    }
}
