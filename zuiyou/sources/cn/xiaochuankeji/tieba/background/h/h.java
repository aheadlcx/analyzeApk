package cn.xiaochuankeji.tieba.background.h;

import java.util.HashMap;

public class h {
    private static h a;
    private HashMap<String, String> b = new HashMap();

    public static h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    private h() {
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }
}
