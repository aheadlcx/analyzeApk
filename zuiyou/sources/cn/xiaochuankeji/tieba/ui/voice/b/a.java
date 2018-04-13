package cn.xiaochuankeji.tieba.ui.voice.b;

import cn.xiaochuankeji.tieba.ui.hollow.report.ReportPlayHollowJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import rx.e;

public class a {
    private static final String a = cn.xiaochuankeji.tieba.background.utils.d.a.f;
    private ReportPlayHollowJson b;
    private cn.xiaochuankeji.tieba.api.log.a c;
    private String d;
    private boolean e;

    private static class a {
        private static a a = new a();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.b = new ReportPlayHollowJson();
        this.b.version = a;
        this.b.deviceType = 0;
        this.b.audioUri = "";
        this.b.audioUrl = "";
        this.b.owner = "post";
        this.b.cType = 2;
        this.b.success = 0;
        this.b.delayTime = 0;
        this.e = true;
        this.c = new cn.xiaochuankeji.tieba.api.log.a();
        this.d = "";
    }

    public void a(long j, long j2, String str, long j3, String str2) {
        a(j, j2, str, j3);
        this.d = str2;
    }

    public void a(long j, long j2, String str, long j3) {
        this.b.audioDuration = j3;
        this.b.audioUrl = str;
        this.b.ownerId = j;
        this.b.memberId = j2;
        this.e = false;
    }

    public void a(long j, long j2, long j3, boolean z) {
        if (!this.e && this.b != null && this.b.ownerId == j) {
            this.c.a("play", "audio", this.d, 0, this.b.ownerId, a(j2, j3, z)).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new e<Void>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(Void voidR) {
                }
            });
        }
    }

    public void a(long j, long j2) {
        a(j, j2, d.a().j(), true);
    }

    private JSONArray a(long j, long j2, boolean z) {
        JSONArray jSONArray = new JSONArray();
        this.b.audioDuration /= 1000;
        this.b.playDur = j / 1000;
        this.b.delayTime = j2;
        this.b.success = z ? 1 : 0;
        jSONArray.add(JSON.parseObject(JSON.toJSONString(this.b)));
        this.e = true;
        return jSONArray;
    }
}
