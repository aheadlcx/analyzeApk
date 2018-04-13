package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UriPatternMatcher<T> {
    @GuardedBy("this")
    private final Map<String, T> a = new HashMap();

    public synchronized void register(String str, T t) {
        Args.notNull(str, "URI request pattern");
        this.a.put(str, t);
    }

    public synchronized void unregister(String str) {
        if (str != null) {
            this.a.remove(str);
        }
    }

    @Deprecated
    public synchronized void setHandlers(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.a.clear();
        this.a.putAll(map);
    }

    @Deprecated
    public synchronized void setObjects(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.a.clear();
        this.a.putAll(map);
    }

    @Deprecated
    public synchronized Map<String, T> getObjects() {
        return this.a;
    }

    public synchronized T lookup(String str) {
        T t;
        Args.notNull(str, "Request path");
        t = this.a.get(str);
        if (t == null) {
            String str2 = null;
            for (String str3 : this.a.keySet()) {
                String str32;
                T t2;
                if (a(str32, str) && (str2 == null || str2.length() < str32.length() || (str2.length() == str32.length() && str32.endsWith("*")))) {
                    t2 = this.a.get(str32);
                } else {
                    str32 = str2;
                    t2 = t;
                }
                t = t2;
                str2 = str32;
            }
        }
        return t;
    }

    protected boolean a(String str, String str2) {
        boolean z = false;
        if (str.equals("*")) {
            return true;
        }
        if ((str.endsWith("*") && str2.startsWith(str.substring(0, str.length() - 1))) || (str.startsWith("*") && str2.endsWith(str.substring(1, str.length())))) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return this.a.toString();
    }
}
