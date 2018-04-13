package cn.xiaochuankeji.tieba.ui.hollow.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.util.HashMap;
import java.util.Map;
import rx.j;

public class a {
    private static a b;
    private Map<Long, ReportPlayHollowJson> a = new HashMap();
    private cn.xiaochuankeji.tieba.api.log.a c = new cn.xiaochuankeji.tieba.api.log.a();

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(ReportPlayHollowJson reportPlayHollowJson) {
        if (reportPlayHollowJson != null) {
            try {
                if (reportPlayHollowJson.ownerId > 0) {
                    if (this.a == null) {
                        this.a = new HashMap();
                    }
                    if (this.a.containsKey(Long.valueOf(reportPlayHollowJson.ownerId)) && ((ReportPlayHollowJson) this.a.get(Long.valueOf(reportPlayHollowJson.ownerId))).playDur > reportPlayHollowJson.playDur) {
                        reportPlayHollowJson.playDur = ((ReportPlayHollowJson) this.a.get(Long.valueOf(reportPlayHollowJson.ownerId))).playDur;
                    }
                    this.a.put(Long.valueOf(reportPlayHollowJson.ownerId), reportPlayHollowJson);
                    if (this.a.size() >= 10) {
                        a("auto");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void a(final String str) {
        if (this.a.size() > 0) {
            cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.c != null) {
                        try {
                            JSONArray jSONArray = new JSONArray();
                            for (Long l : this.b.a.keySet()) {
                                jSONArray.add(JSON.parseObject(JSON.toJSONString((ReportPlayHollowJson) this.b.a.get(l))));
                            }
                            this.b.c.a("play", "audio", str, 0, 0, jSONArray).a(rx.f.a.c()).b(new j<Void>(this) {
                                final /* synthetic */ AnonymousClass1 a;

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
                                    if (this.a.b.a != null) {
                                        this.a.b.a.clear();
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }
    }
}
