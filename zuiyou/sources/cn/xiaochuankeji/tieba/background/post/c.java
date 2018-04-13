package cn.xiaochuankeji.tieba.background.post;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;

public class c {
    private static c a;
    private String b;

    private c() {
        this.b = "";
        this.b = a.a().getString("key_last_show_url", "");
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Editor edit = a.a().edit();
            edit.putString("key_last_show_url", str);
            edit.apply();
            this.b = str;
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.b.equals(str);
    }
}
