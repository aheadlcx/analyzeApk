package cz.msebera.android.httpclient.config;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public final class Registry<I> implements Lookup<I> {
    private final Map<String, I> a;

    Registry(Map<String, I> map) {
        this.a = new ConcurrentHashMap(map);
    }

    public I lookup(String str) {
        if (str == null) {
            return null;
        }
        return this.a.get(str.toLowerCase(Locale.ROOT));
    }

    public String toString() {
        return this.a.toString();
    }
}
