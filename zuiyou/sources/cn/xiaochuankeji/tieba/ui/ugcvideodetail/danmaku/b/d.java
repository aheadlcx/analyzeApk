package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b;

import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgPageJson;
import cn.xiaochuankeji.tieba.json.UgcVideoSubDanmakus;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.g.a;
import java.util.ArrayList;
import java.util.List;
import rx.j;

public class d {
    private a a;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a b;
    private int c = 0;
    private int d = 0;
    private String e;
    private long f;
    private List<UgcVideoDanmakuJson> g;
    private boolean h = false;
    private boolean i = false;
    private long j = 0;
    private long k = 0;

    public d(a aVar) {
        this.a = aVar;
        this.b = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
        this.g = new ArrayList();
    }

    public void a(long j, int i) {
        this.g.clear();
        this.c = i;
        this.a.a(true);
        if (this.c == 0) {
            this.j = j;
            this.b.d(this.j, this.e).a(rx.a.b.a.a()).b(new j<UgcVideoSubDanmakus>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoSubDanmakus) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                    this.a.a.a(false);
                }

                public void a(UgcVideoSubDanmakus ugcVideoSubDanmakus) {
                    boolean z = true;
                    this.a.a.a(false);
                    this.a.a.a();
                    this.a.e = ugcVideoSubDanmakus.offset;
                    if (ugcVideoSubDanmakus.parentInfo != null) {
                        this.a.g.add(ugcVideoSubDanmakus.parentInfo);
                    }
                    if (ugcVideoSubDanmakus.danmakus.size() > 0) {
                        this.a.g.addAll(ugcVideoSubDanmakus.danmakus);
                        d dVar = this.a;
                        if (ugcVideoSubDanmakus.more != 1) {
                            z = false;
                        }
                        dVar.h = z;
                        this.a.a.b(false);
                    } else {
                        this.a.a.b(true);
                    }
                    this.a.a.a(this.a.g, this.a.h, this.a.i);
                }
            });
            return;
        }
        this.k = j;
        this.d = i;
        this.b.b(this.k, this.d).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuMsgJson>(this) {
            final /* synthetic */ d a;

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
            }

            public void a(UgcVideoDanmakuMsgJson ugcVideoDanmakuMsgJson) {
                boolean z;
                boolean z2 = true;
                this.a.a.a(false);
                this.a.f = ugcVideoDanmakuMsgJson.offset;
                if (2 == this.a.d) {
                    this.a.g.add(ugcVideoDanmakuMsgJson.ancestorDanmaku);
                } else if (3 == this.a.d) {
                    this.a.g.add(ugcVideoDanmakuMsgJson.ancestorDanmaku);
                    this.a.g.add(ugcVideoDanmakuMsgJson.sourceDanmaku);
                }
                this.a.j = ugcVideoDanmakuMsgJson.ancestorDanmaku.id;
                this.a.a.a(this.a.j);
                this.a.g.addAll(ugcVideoDanmakuMsgJson.danmakus);
                d dVar = this.a;
                if (1 == ugcVideoDanmakuMsgJson.more) {
                    z = true;
                } else {
                    z = false;
                }
                dVar.h = z;
                d dVar2 = this.a;
                if (this.a.h) {
                    z2 = false;
                }
                dVar2.i = z2;
                this.a.a.a(this.a.g, this.a.h, this.a.i);
            }
        });
    }

    public void a() {
        if (this.d == 0) {
            this.b.d(this.j, this.e).a(rx.a.b.a.a()).b(new j<UgcVideoSubDanmakus>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoSubDanmakus) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                }

                public void a(UgcVideoSubDanmakus ugcVideoSubDanmakus) {
                    boolean z = true;
                    this.a.e = ugcVideoSubDanmakus.offset;
                    if (ugcVideoSubDanmakus.danmakus.size() > 0) {
                        this.a.g.addAll(ugcVideoSubDanmakus.danmakus);
                        d dVar = this.a;
                        if (1 != ugcVideoSubDanmakus.more) {
                            z = false;
                        }
                        dVar.h = z;
                        this.a.a.a(this.a.g, this.a.h, this.a.i);
                    }
                }
            });
        } else {
            this.b.a(this.k, this.f, this.d).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuMsgPageJson>(this) {
                final /* synthetic */ d a;

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
                    this.a.f = ugcVideoDanmakuMsgPageJson.offset;
                    if (ugcVideoDanmakuMsgPageJson.danmakus.size() > 0) {
                        boolean z2;
                        this.a.g.addAll(ugcVideoDanmakuMsgPageJson.danmakus);
                        d dVar = this.a;
                        if (ugcVideoDanmakuMsgPageJson.more == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        dVar.h = z2;
                        d dVar2 = this.a;
                        if (this.a.h) {
                            z = false;
                        }
                        dVar2.i = z;
                        this.a.a.a(this.a.g, this.a.h, this.a.i);
                    }
                }
            });
        }
    }

    public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
        this.g.add(1, ugcVideoDanmakuJson);
        this.a.b(false);
        this.a.a(this.g, this.h, this.i);
        this.a.a();
    }

    public void b() {
        this.e = null;
        this.f = 0;
        this.g.clear();
        this.k = 0;
        this.j = 0;
        this.d = 0;
        this.h = false;
        this.i = false;
        this.a.a(this.g, this.h, this.i);
        this.a.b(false);
    }

    public void c() {
        this.h = false;
        this.i = false;
        this.d = 0;
        this.e = null;
        this.f = 0;
        this.k = 0;
        a(this.j, this.d);
    }
}
