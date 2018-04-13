package cn.xiaochuankeji.tieba.api.config;

import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import com.sina.weibo.sdk.constant.WBConstants;
import java.util.Map;
import rx.e;

public class c {
    private static String a = "key_push_register_time";
    private static String b = "key_push_register_cr";
    private static c d;
    private JSONObject c;

    public static c a() {
        if (d == null) {
            synchronized (c.class) {
                if (d == null) {
                    d = new c();
                }
            }
        }
        return d;
    }

    private c() {
        Object string = a.a().getString(b, null);
        if (TextUtils.isEmpty(string)) {
            this.c = new JSONObject();
        } else {
            this.c = JSONObject.parseObject(string);
        }
    }

    public void b() {
        long c = a.g().c();
        Map b = cn.xiaochuan.push.a.b();
        String c2 = d.c(c + b.toString());
        c = this.c.containsKey(a) ? this.c.getLong(a).longValue() : 0;
        String string = this.c.containsKey(b) ? this.c.getString(b) : null;
        b.c("RegisterCard CR:" + string + "  Current CR:" + c2);
        if (Math.abs(System.currentTimeMillis() - c) >= com.umeng.analytics.a.i || !c2.equalsIgnoreCase(string)) {
            this.c.put(a, Long.valueOf(System.currentTimeMillis()));
            this.c.put(b, c2);
            a.a().edit().putString(b, this.c.toJSONString()).apply();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("channels", new JSONObject(b));
            a(true, jSONObject);
        }
    }

    private void a(final boolean z, final JSONObject jSONObject) {
        ((PushApiService) cn.xiaochuankeji.tieba.network.c.b(PushApiService.class)).register(jSONObject).a(rx.f.a.c()).a(new e<EmptyJson>(this) {
            final /* synthetic */ c c;

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (z) {
                    this.c.a(false, jSONObject);
                    return;
                }
                this.c.c.put(c.a, Long.valueOf(0));
                this.c.c.put(c.b, null);
                a.a().edit().putString(c.b, this.c.c.toJSONString()).apply();
            }

            public void a(EmptyJson emptyJson) {
            }
        });
    }

    public static void c() {
        a.a().edit().remove(b).apply();
    }

    public void a(cn.xiaochuan.push.e eVar) {
        a(eVar, true);
    }

    private void a(final cn.xiaochuan.push.e eVar, final boolean z) {
        if (eVar != null && eVar.k != null) {
            JSONObject jSONObject = eVar.k.getJSONObject("recv_cb");
            if (jSONObject != null) {
                Object string = jSONObject.getString("url");
                jSONObject = jSONObject.getJSONObject("data");
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("data", jSONObject);
                    if (eVar.m != 0) {
                        jSONObject2.put("background", Integer.valueOf(eVar.m));
                    }
                    if (eVar.n != 0) {
                        jSONObject2.put(WBConstants.AUTH_PARAMS_DISPLAY, Integer.valueOf(eVar.n));
                    } else {
                        jSONObject2.put(WBConstants.AUTH_PARAMS_DISPLAY, Integer.valueOf(cn.xiaochuan.push.b.b.a(BaseApplication.getAppContext()) ? 1 : -1));
                    }
                    jSONObject2.put("recv_ts", Long.valueOf(System.currentTimeMillis()));
                    ((PushApiService) cn.xiaochuankeji.tieba.network.c.b(PushApiService.class)).clickedCallback(string, jSONObject2).b(rx.f.a.c()).a(rx.f.a.c()).a(new e<Object>(this) {
                        final /* synthetic */ c c;

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            if (z) {
                                this.c.a(eVar, false);
                            }
                            com.izuiyou.a.a.a.b("PushManager", "receive callback failed! " + th);
                        }

                        public void onNext(Object obj) {
                            com.izuiyou.a.a.a.b("PushManager", "receive callback success! ");
                        }
                    });
                }
            }
        }
    }

    public void b(cn.xiaochuan.push.e eVar) {
        b(eVar, true);
    }

    private void b(final cn.xiaochuan.push.e eVar, final boolean z) {
        if (eVar != null && eVar.k != null) {
            JSONObject jSONObject = eVar.k.getJSONObject("click_cb");
            if (jSONObject != null) {
                Object string = jSONObject.getString("url");
                jSONObject = jSONObject.getJSONObject("data");
                if (!TextUtils.isEmpty(string) && jSONObject != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("data", jSONObject);
                    if (eVar.m != 0) {
                        jSONObject2.put("background", Integer.valueOf(eVar.m));
                    }
                    ((PushApiService) cn.xiaochuankeji.tieba.network.c.b(PushApiService.class)).clickedCallback(string, jSONObject2).a(rx.f.a.c()).a(new e<Object>(this) {
                        final /* synthetic */ c c;

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            if (z) {
                                this.c.b(eVar, false);
                            }
                            com.izuiyou.a.a.a.b("PushManager", "click callback failed! " + th);
                        }

                        public void onNext(Object obj) {
                            com.izuiyou.a.a.a.b("PushManager", "click callback success! ");
                        }
                    });
                }
            }
        }
    }
}
