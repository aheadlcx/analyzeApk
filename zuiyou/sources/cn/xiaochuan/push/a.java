package cn.xiaochuan.push;

import android.content.Context;
import android.support.annotation.Nullable;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.huawei.c;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.base.BuildExt;
import java.util.HashMap;

public class a implements d {
    private static final HashMap<String, Object> a = new HashMap();
    private static final HashMap<String, d> b = new HashMap();
    private f c;

    private static class a {
        private static final a a = new a();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.c = null;
    }

    public void a(f fVar) {
        this.c = fVar;
        b.clear();
        String a = a(fVar.a());
        b.c("Push init sys:" + a);
        Object obj = -1;
        switch (a.hashCode()) {
            case 0:
                if (a.equals("")) {
                    obj = 5;
                    break;
                }
                break;
            case 3343:
                if (a.equals("hw")) {
                    obj = 2;
                    break;
                }
                break;
            case 3476:
                if (a.equals("ma")) {
                    obj = 3;
                    break;
                }
                break;
            case 3484:
                if (a.equals("mi")) {
                    obj = null;
                    break;
                }
                break;
            case 3501:
                if (a.equals("mz")) {
                    obj = 1;
                    break;
                }
                break;
            case 3553:
                if (a.equals("op")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                b.put("mi", cn.xiaochuan.push.xiaomi.a.a());
                return;
            case 1:
                b.put("mz", cn.xiaochuan.push.meizu.a.a());
                return;
            case 2:
                b.put("hw", c.a());
                return;
            case 3:
                b.put("ma", cn.xiaochuan.push.xiaomi.a.a());
                return;
            case 4:
                b.put("op", cn.xiaochuan.push.oppo.b.a());
                return;
            case 5:
                b.put("", cn.xiaochuan.push.a.a.a());
                return;
            default:
                return;
        }
    }

    private String a(Context context) {
        if (cn.xiaochuan.a.a.b()) {
            return "mi";
        }
        if (cn.xiaochuan.a.a.a()) {
            return c.a(context) ? "ma" : "hw";
        } else {
            if (BuildExt.isRom().ok) {
                return "mz";
            }
            if (cn.xiaochuan.push.oppo.b.a(context)) {
                return "op";
            }
            return "ma";
        }
    }

    public static HashMap<String, Object> b() {
        return a;
    }

    public void a(long j) {
        b.c("push register mid:" + j);
        for (d a : b.values()) {
            a.a(j);
        }
    }

    public void b(long j) {
        for (d b : b.values()) {
            b.b(j);
        }
    }

    public void a(int i, String str, @Nullable e eVar) {
        if (eVar != null) {
            if (i != 3 || a(str)) {
                b.c("action:" + i + "  PUSH_TOKEN:" + a + "  message:" + eVar);
                if (this.c != null) {
                    try {
                        eVar.l = str;
                        eVar.m = a().d();
                        this.c.a(i, str, eVar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static boolean a(String str) {
        return b.containsKey(str);
    }

    public void a(String str, String str2) {
        b().put(str, str2);
        if (this.c != null) {
            try {
                this.c.a(str, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(int i) {
        for (d a : b.values()) {
            a.a(i);
        }
    }

    public Context c() {
        return this.c.a();
    }

    private int d() {
        return BaseApplication.isApplicationInBackground() ? 1 : -1;
    }
}
