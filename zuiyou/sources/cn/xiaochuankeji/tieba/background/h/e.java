package cn.xiaochuankeji.tieba.background.h;

import java.util.HashMap;

public class e {
    private static e a;
    private HashMap<String, String> b = new HashMap();

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private e() {
    }

    public boolean a(String str) {
        return this.b.containsKey(str);
    }

    public String b(String str) {
        return (String) this.b.get(str);
    }
}
