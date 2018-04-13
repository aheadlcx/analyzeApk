package cn.tatagou.sdk.e.a;

import cn.tatagou.sdk.util.p;
import java.util.HashMap;

public class a {
    private String a;
    private HashMap<String, String> b = new HashMap();

    public a(String str) {
        this.a = str;
        this.b.put("event", this.a);
    }

    public void addProperty(String str, String str2) {
        if (!p.isEmpty(str2)) {
            this.b.put(str, str2);
        }
    }

    public Object getProperty(String str) {
        return this.b.get(str);
    }

    public String getEvent() {
        return this.a;
    }

    public void setEvent(String str) {
        this.a = str;
    }

    public HashMap<String, String> getParams() {
        return this.b;
    }

    public void setParams(HashMap<String, String> hashMap) {
        this.b = hashMap;
    }

    public void setAllParams(HashMap<String, String> hashMap) {
        this.b.putAll(hashMap);
    }
}
