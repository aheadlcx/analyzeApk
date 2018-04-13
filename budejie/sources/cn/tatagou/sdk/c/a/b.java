package cn.tatagou.sdk.c.a;

import java.util.HashMap;
import java.util.Map;

public class b {
    Map<String, Object> a = new HashMap();

    public b() {
        this.a.put("__time__", Integer.valueOf(new Long(System.currentTimeMillis() / 1000).intValue()));
    }

    public b(Map<String, Object> map) {
        this.a = map;
    }

    public void PutTime(int i) {
        this.a.put("__time__", Integer.valueOf(i));
    }

    public void PutContent(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            if (str2 == null) {
                this.a.put(str, "");
            } else {
                this.a.put(str, str2);
            }
        }
    }

    public Map<String, Object> GetContent() {
        return this.a;
    }
}
