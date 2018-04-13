package cn.xiaochuankeji.tieba.ui.debug;

import android.content.SharedPreferences;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.utils.d.a;

public class b {
    private static b a;
    private boolean b = false;

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void b() {
        String string = f().getString("api_server", "api.izuiyou.com");
        a.a(string);
        if (string == null || !string.contains("test.izuiyou.com")) {
            this.b = false;
        } else {
            this.b = true;
        }
    }

    public void c() {
        a.a("api.izuiyou.com");
        f().edit().putString("api_server", "api.izuiyou.com").apply();
        this.b = false;
    }

    public void d() {
        a.a("test.izuiyou.com");
        f().edit().putString("api_server", "test.izuiyou.com").apply();
        this.b = true;
    }

    public void a(boolean z) {
        f().edit().putBoolean("leak_canary_enabled", z).apply();
    }

    public boolean e() {
        return f().getBoolean("leak_canary_enabled", false);
    }

    public void b(boolean z) {
        f().edit().putBoolean("enable_https", z).apply();
    }

    private SharedPreferences f() {
        return BaseApplication.getAppContext().getSharedPreferences("debug_options", 0);
    }
}
