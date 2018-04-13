package cn.tatagou.sdk.a;

import java.util.HashMap;

public class c {
    private static c b;
    private HashMap<String, String> a = new HashMap();

    public static c getInstance() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    public void addProperty(String str, String str2) {
        this.a.put(str, str2);
    }

    public String getKeyParams(String str) {
        return (String) this.a.get(str);
    }

    public void removeKey(String str) {
        this.a.remove(str);
    }

    public void clear() {
        this.a.clear();
    }
}
