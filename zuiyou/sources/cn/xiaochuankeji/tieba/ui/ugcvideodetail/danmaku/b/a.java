package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b;

import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuLikeJson;
import org.json.JSONObject;
import rx.d;
import rx.j;

public class a {
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.a.a a;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a b = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private d<UgcVideoDanmakuLikeJson> c;
    private d<EmptyJson> d;

    public a(cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.a.a aVar) {
        this.a = aVar;
    }

    public void a(final boolean z, final long j) {
        if (this.c == null && this.d == null) {
            if (z) {
                this.c = this.b.f(j);
            } else {
                this.c = this.b.g(j);
            }
            this.c.a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuLikeJson>(this) {
                final /* synthetic */ a c;

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoDanmakuLikeJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.c.c = null;
                    g.a(th.getMessage());
                }

                public void a(UgcVideoDanmakuLikeJson ugcVideoDanmakuLikeJson) {
                    this.c.c = null;
                    if ((ugcVideoDanmakuLikeJson.liked == 1 && z) || (ugcVideoDanmakuLikeJson.liked == -1 && !z)) {
                        this.c.a.a(z, j);
                    }
                }
            });
        }
    }

    public void a(final long j) {
        if (this.c == null && this.d == null) {
            this.d = this.b.h(j);
            this.d.a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
                final /* synthetic */ a b;

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.b.d = null;
                    g.a(th.getMessage());
                }

                public void a(EmptyJson emptyJson) {
                    this.b.d = null;
                    this.b.a.a(j);
                }
            });
        }
    }

    public void b(long j) {
        new b(j, "ugcvideo_danmaku", 0, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }
}
