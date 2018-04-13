package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b;

import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuDanmakus;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgPageJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.f.a;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.f.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import rx.j;
import rx.k;

public class c implements a {
    private b a;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a b;
    private long c;
    private int d;
    private String e;
    private long f;
    private UgcVideoInfoBean g;
    private boolean h = false;
    private boolean i = false;
    private List<UgcVideoDanmakuJson> j = new ArrayList();
    private k k;

    public c(b bVar) {
        this.a = bVar;
        this.b = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean, long j, int i) {
        long j2 = 0;
        this.g = ugcVideoInfoBean;
        this.c = j;
        this.d = i;
        if (this.d == 0 || this.d == 1) {
            this.j.clear();
            this.a.a(this.j, this.h, this.i);
            this.a.a(true);
            if (this.d == 0) {
                long j3;
                if (ugcVideoInfoBean.pid == 0) {
                    j3 = ugcVideoInfoBean.id;
                } else {
                    j3 = ugcVideoInfoBean.pid;
                    j2 = ugcVideoInfoBean.id;
                }
                this.k = this.b.a(j3, j2, null).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuDanmakus>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((UgcVideoDanmakuDanmakus) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        g.a(th.getMessage());
                        this.a.a.a(false);
                        if (!(th instanceof ClientErrorException)) {
                            this.a.a.a(true, true);
                        }
                    }

                    public void a(UgcVideoDanmakuDanmakus ugcVideoDanmakuDanmakus) {
                        boolean z = true;
                        this.a.a.a(false);
                        if (ugcVideoDanmakuDanmakus.danmakus.size() > 0) {
                            c cVar = this.a;
                            if (ugcVideoDanmakuDanmakus.more != 1) {
                                z = false;
                            }
                            cVar.h = z;
                            this.a.j.addAll(ugcVideoDanmakuDanmakus.danmakus);
                            this.a.a.a(this.a.j, this.a.h, this.a.i);
                            this.a.e = ugcVideoDanmakuDanmakus.offset;
                        } else {
                            this.a.a.a(true, false);
                        }
                        this.a.a.a(ugcVideoDanmakuDanmakus.total);
                    }
                });
                return;
            }
            this.k = this.b.b(j, 1).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuMsgJson>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoDanmakuMsgJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                    this.a.a.a(false);
                    if (!(th instanceof ClientErrorException)) {
                        this.a.a.a(true, true);
                    }
                }

                public void a(UgcVideoDanmakuMsgJson ugcVideoDanmakuMsgJson) {
                    boolean z = true;
                    this.a.a.a(false);
                    if (ugcVideoDanmakuMsgJson.danmakus.size() > 0) {
                        this.a.h = ugcVideoDanmakuMsgJson.more == 1;
                        c cVar = this.a;
                        if (this.a.h) {
                            z = false;
                        }
                        cVar.i = z;
                        this.a.j.addAll(ugcVideoDanmakuMsgJson.danmakus);
                        this.a.a.a(this.a.j, this.a.h, this.a.i);
                        this.a.f = ugcVideoDanmakuMsgJson.offset;
                        return;
                    }
                    this.a.a.a(true, false);
                }
            });
        } else if (2 == this.d || 3 == this.d) {
            this.a.a(j, i);
        } else if (4 == this.d) {
            this.a.a(j, 0);
        }
    }

    public void a() {
        long j = 0;
        if (this.d == 0) {
            long j2;
            if (this.g.pid == 0) {
                j2 = this.g.id;
            } else {
                j2 = this.g.pid;
                j = this.g.id;
            }
            this.k = this.b.a(j2, j, this.e).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuDanmakus>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoDanmakuDanmakus) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                }

                public void a(UgcVideoDanmakuDanmakus ugcVideoDanmakuDanmakus) {
                    boolean z = true;
                    Collection collection = ugcVideoDanmakuDanmakus.danmakus;
                    if (collection.size() > 0) {
                        this.a.j.addAll(collection);
                        c cVar = this.a;
                        if (1 != ugcVideoDanmakuDanmakus.more) {
                            z = false;
                        }
                        cVar.h = z;
                        this.a.a.a(this.a.j, this.a.h, this.a.i);
                        this.a.e = ugcVideoDanmakuDanmakus.offset;
                    }
                }
            });
        } else if (1 == this.d) {
            this.k = this.b.a(this.c, this.f, 1).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuMsgPageJson>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoDanmakuMsgPageJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                }

                public void a(UgcVideoDanmakuMsgPageJson ugcVideoDanmakuMsgPageJson) {
                    boolean z = true;
                    Collection collection = ugcVideoDanmakuMsgPageJson.danmakus;
                    if (collection.size() > 0) {
                        this.a.j.addAll(collection);
                        this.a.h = 1 == ugcVideoDanmakuMsgPageJson.more;
                        c cVar = this.a;
                        if (this.a.h) {
                            z = false;
                        }
                        cVar.i = z;
                        this.a.a.a(this.a.j, this.a.h, this.a.i);
                        this.a.f = ugcVideoDanmakuMsgPageJson.offset;
                    }
                }
            });
        }
    }

    public void b() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.i = false;
        a(this.g, this.c, this.d);
    }

    public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
        this.j.add(0, ugcVideoDanmakuJson);
        this.a.a(this.j, this.h, this.i);
        this.a.a(false, false);
        this.a.b();
    }

    public void c() {
        if (2 == this.d || 3 == this.d || 4 == this.d) {
            a(this.g, 0, 0);
        }
    }

    public void d() {
        if (this.k != null) {
            this.k.unsubscribe();
        }
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = 0;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j.clear();
        this.a.a();
    }
}
