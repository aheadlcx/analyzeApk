package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoPublishedDanmakuJson;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.b.a;
import rx.j;

public class b {
    private a a;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a b = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private UgcVideoInfoBean c;
    private long d;
    private long e;
    private String f;

    public b(a aVar) {
        this.a = aVar;
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean, long j, String str) {
        this.c = ugcVideoInfoBean;
        this.d = j;
        this.f = str;
    }

    public void a(String str) {
        long j = 0;
        if (TextUtils.isEmpty(str)) {
            g.a("还没有输入文字");
            return;
        }
        long j2;
        if (this.c.pid == 0) {
            j2 = this.c.id;
        } else {
            j2 = this.c.pid;
            j = this.c.id;
        }
        long j3 = this.c.videoInfo.id;
        this.a.a();
        this.b.a(j2, j, j3, this.e, this.d, str, this.f).a(rx.a.b.a.a()).b(new j<UgcVideoPublishedDanmakuJson>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoPublishedDanmakuJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
                this.a.a.a(false, null);
            }

            public void a(UgcVideoPublishedDanmakuJson ugcVideoPublishedDanmakuJson) {
                this.a.a.a(true, ugcVideoPublishedDanmakuJson.danmakuJson);
            }
        });
    }

    public void a(long j) {
        this.e = j;
    }

    public long a() {
        return this.e;
    }
}
