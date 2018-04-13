package cn.xiaochuankeji.tieba.background.i;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.share.ShareService;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import rx.e;

public class a {
    public static void a(long j, String str, String str2, int i, long j2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "post");
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("from", str);
        jSONObject.put("to", str2);
        if (!(i == 0 || j2 == 0)) {
            jSONObject.put("otype", Integer.valueOf(i));
            jSONObject.put("oid", Long.valueOf(j2));
        }
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("abtestingid", str3);
        }
        ((ShareService) c.b(ShareService.class)).shareReport(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>() {
            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(EmptyJson emptyJson) {
                b.c("对象分享上报成功");
            }
        });
    }

    public static void a(long j, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "post");
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("from", str);
        jSONObject.put("to", str2);
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("abtestingid", str3);
        }
        ((ShareService) c.b(ShareService.class)).shareReport(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>() {
            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(EmptyJson emptyJson) {
                b.c("帖子分享上报成功");
            }
        });
    }

    public static void a(String str, long j, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", str);
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("from", str2);
        jSONObject.put("to", str3);
        if (!TextUtils.isEmpty(str4)) {
            jSONObject.put("abtestingid", str4);
        }
        ((ShareService) c.b(ShareService.class)).shareReport(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>() {
            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(EmptyJson emptyJson) {
                b.c("跟帖分享上报成功");
            }
        });
    }

    public static void a(long j, long j2, String str, String str2, int i, long j3, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "review");
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("rid", Long.valueOf(j2));
        jSONObject.put("from", str);
        jSONObject.put("to", str2);
        jSONObject.put("otype", Integer.valueOf(i));
        jSONObject.put("oid", Long.valueOf(j3));
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("abtestingid", str3);
        }
        ((ShareService) c.b(ShareService.class)).shareReport(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>() {
            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(EmptyJson emptyJson) {
                b.c("评论对象分享上报成功");
            }
        });
    }

    public static void a(long j, long j2, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "review");
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("rid", Long.valueOf(j2));
        jSONObject.put("from", str);
        jSONObject.put("to", str2);
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("abtestingid", str3);
        }
        ((ShareService) c.b(ShareService.class)).shareReport(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>() {
            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(EmptyJson emptyJson) {
                b.c("评论分享上报成功");
            }
        });
    }
}
