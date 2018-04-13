package cn.xiaochuankeji.tieba.background.h;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.config.ConfigService;
import cn.xiaochuankeji.tieba.b.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.common.util.d;
import com.iflytek.speech.UtilityConfig;
import com.izuiyou.a.a.b;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.upgrade.UpgradeListener;
import com.tencent.tauth.AuthActivity;
import rx.e;

public class c {
    public static UpgradeListener a = new UpgradeListener() {
        public void onUpgrade(int i, UpgradeInfo upgradeInfo, boolean z, boolean z2) {
            if (upgradeInfo != null && c.a().b()) {
                String str = upgradeInfo.apkUrl;
                String valueOf = String.valueOf(upgradeInfo.newFeature);
                Object i2 = upgradeInfo.updateType == 2 ? c.b : c.c;
                String str2 = upgradeInfo.versionName;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AuthActivity.ACTION_KEY, i2);
                jSONObject.put("detail", valueOf);
                jSONObject.put("link", str);
                jSONObject.put("update", Integer.valueOf(1));
                jSONObject.put("ver", str2);
                c.a().a(jSONObject);
                org.greenrobot.eventbus.c.a().d(new g());
            }
        }
    };
    private static String b = "alert";
    private static String c = "alertone";
    private static String d = "redone";
    private static c e;

    public static class a {
        public String a;
        public String b;
        public String c;
        public boolean d;
        public String e;
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (e == null) {
                e = new c();
            }
            cVar = e;
        }
        return cVar;
    }

    private c() {
    }

    public boolean b() {
        return cn.xiaochuankeji.tieba.background.a.a().getBoolean("k_beta_setting", false);
    }

    public void a(boolean z) {
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("k_beta_setting", z).apply();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enable", Integer.valueOf(z ? 1 : 0));
        ((ConfigService) cn.xiaochuankeji.tieba.network.c.b(ConfigService.class)).updateBeta(jSONObject).a(new e<EmptyJson>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                b.e(th);
            }

            public void a(EmptyJson emptyJson) {
            }
        });
    }

    public boolean c() {
        if (e() && cn.xiaochuankeji.tieba.background.a.a().getBoolean("SETTING_BADGE_KEY", false)) {
            return true;
        }
        return false;
    }

    public void d() {
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("SETTING_BADGE_KEY", false).apply();
    }

    public boolean e() {
        return f() != null;
    }

    private void a(JSONObject jSONObject) {
        String str = "kVersionData_" + b();
        if (jSONObject == null || jSONObject.isEmpty()) {
            cn.xiaochuankeji.tieba.background.a.a().edit().remove(str).apply();
            return;
        }
        jSONObject.put("k_base_version", "4.1.8.9");
        cn.xiaochuankeji.tieba.background.a.a().edit().putString(str, jSONObject.toJSONString()).apply();
    }

    public a f() {
        return b(false);
    }

    public a b(boolean z) {
        boolean z2 = true;
        String str = "kVersionData_" + b();
        Object string = cn.xiaochuankeji.tieba.background.a.a().getString(str, null);
        if (string == null || TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject parseObject = JSON.parseObject(string);
            String string2 = parseObject.getString("k_base_version");
            if (TextUtils.isEmpty(string2)) {
                return null;
            }
            if (a(string2)) {
                if (parseObject.getIntValue("update") == 1) {
                    a aVar = new a();
                    aVar.a = parseObject.getString(AuthActivity.ACTION_KEY);
                    aVar.b = parseObject.getString("detail");
                    aVar.c = parseObject.getString("link");
                    if (parseObject.getIntValue("update") != 1) {
                        z2 = false;
                    }
                    aVar.d = z2;
                    aVar.e = parseObject.getString("ver");
                    return aVar;
                }
                cn.xiaochuankeji.tieba.background.a.a().edit().remove(str).apply();
                return null;
            }
            cn.xiaochuankeji.tieba.background.a.a().edit().remove(str).apply();
            return null;
        } catch (Exception e) {
            cn.xiaochuankeji.tieba.background.a.a().edit().remove(str).apply();
            return null;
        }
    }

    private static boolean a(@NonNull String str) {
        return "4.1.8.9".equals(str);
    }

    public void g() {
        if (b()) {
            Beta.checkUpgrade(true, true);
            return;
        }
        Beta.checkUpgrade(true, true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("product", "tieba");
        jSONObject.put(UtilityConfig.KEY_DEVICE_INFO, "android");
        jSONObject.put("ver", "4.1.8.9");
        ((ConfigService) cn.xiaochuankeji.tieba.network.c.b(ConfigService.class)).versionConfig(jSONObject).a(new e<JSONObject>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(JSONObject jSONObject) {
                if (jSONObject.getIntValue("update") == 1) {
                    this.a.a(jSONObject);
                }
            }
        });
    }

    public void a(Activity activity) {
        final a f = f();
        if (f != null && !a(f.e)) {
            final String str = "update_version_name_" + b();
            boolean equals = b.equals(f.a);
            boolean equals2 = c.equals(f.a);
            if (equals || equals2) {
                f.a("新版" + f.e, f.b, activity, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ c c;

                    public void a(boolean z) {
                        if (z && !TextUtils.isEmpty(f.c) && d.a(Uri.parse(f.c))) {
                            cn.xiaochuankeji.tieba.network.filedownload.e.b(f.c);
                            cn.xiaochuankeji.tieba.background.utils.g.a("开始下载...");
                        }
                        cn.xiaochuankeji.tieba.background.a.a().edit().putString(str, "4.1.8.9").apply();
                    }
                }, equals);
            } else if (d.equals(f.a)) {
                boolean z = !"4.1.8.9".equals(cn.xiaochuankeji.tieba.background.a.a().getString(str, null));
                Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                edit.putString(str, "4.1.8.9");
                if (z) {
                    edit.putBoolean("SETTING_BADGE_KEY", true);
                    edit.putBoolean("key_can_update", true);
                    MainActivity.a("tab_my", 1);
                }
                edit.apply();
            }
        }
    }

    public void h() {
        ((ConfigService) cn.xiaochuankeji.tieba.network.c.b(ConfigService.class)).getBeta(new JSONObject()).a(new e<JSONObject>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(JSONObject jSONObject) {
                boolean z = true;
                int intValue = jSONObject.getIntValue("enable");
                Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                String str = "k_beta_setting";
                if (intValue != 1) {
                    z = false;
                }
                edit.putBoolean(str, z).apply();
            }
        });
    }
}
