package cn.xiaochuankeji.tieba.background.review;

import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONObject;

public class g {
    private static g a;

    public interface a {
        void a(boolean z, String str);
    }

    private g() {
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    public void a(long j, long j2, String str, int i, a aVar) {
        final a aVar2 = aVar;
        b anonymousClass1 = new b<JSONObject>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(true, null);
                }
            }
        };
        aVar2 = aVar;
        new k(j, j2, str, i, anonymousClass1, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ g b;

            public void onErrorResponse(XCError xCError, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(false, xCError.getMessage());
                }
            }
        }).execute();
    }

    public void b(long j, long j2, String str, int i, a aVar) {
        final a aVar2 = aVar;
        b anonymousClass3 = new b<JSONObject>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(true, null);
                }
            }
        };
        aVar2 = aVar;
        new i(j, j2, str, i, anonymousClass3, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ g b;

            public void onErrorResponse(XCError xCError, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(false, xCError.getMessage());
                }
            }
        }).execute();
    }

    public void c(long j, long j2, String str, int i, a aVar) {
        final a aVar2 = aVar;
        b anonymousClass5 = new b<JSONObject>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(true, null);
                }
            }
        };
        aVar2 = aVar;
        new j(j, j2, str, i, anonymousClass5, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ g b;

            public void onErrorResponse(XCError xCError, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(false, xCError.getMessage());
                }
            }
        }).execute();
    }

    public void d(long j, long j2, String str, int i, a aVar) {
        final a aVar2 = aVar;
        b anonymousClass7 = new b<JSONObject>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(true, null);
                }
            }
        };
        aVar2 = aVar;
        new h(j, j2, str, i, anonymousClass7, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ g b;

            public void onErrorResponse(XCError xCError, Object obj) {
                if (aVar2 != null) {
                    aVar2.a(false, xCError.getMessage());
                }
            }
        }).execute();
    }
}
