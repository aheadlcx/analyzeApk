package cn.xiaochuankeji.tieba.push;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.config.PushApiService;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.e;

public class a {
    private static String a;

    private static void e(String str) {
        cn.xiaochuankeji.tieba.background.a.c().edit().putString("key_android_client_id", str).apply();
    }

    @Nullable
    public static String a() {
        return cn.xiaochuankeji.tieba.background.a.c().getString("key_android_client_id", null);
    }

    public static void a(final String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase(a)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("clientid", str);
            ((PushApiService) c.b(PushApiService.class)).bindClientId(jSONObject).a(rx.f.a.c()).a(new e<EmptyJson>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(EmptyJson emptyJson) {
                    a.a = str;
                    a.e(str);
                }
            });
        }
    }

    public static void b(String str) {
        a = null;
        if (!TextUtils.isEmpty(str)) {
            e(null);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("clientid", str);
            ((PushApiService) c.b(PushApiService.class)).unbindClientId(jSONObject).a(rx.f.a.c()).a(new e<EmptyJson>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(EmptyJson emptyJson) {
                }
            });
        }
    }

    public static void b() {
        a = null;
    }
}
