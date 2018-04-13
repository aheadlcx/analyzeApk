package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Deprecated
public final class SchemeRegistry {
    private final ConcurrentHashMap<String, Scheme> a = new ConcurrentHashMap();

    public final Scheme getScheme(String str) {
        Scheme scheme = get(str);
        if (scheme != null) {
            return scheme;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }

    public final Scheme getScheme(HttpHost httpHost) {
        Args.notNull(httpHost, "Host");
        return getScheme(httpHost.getSchemeName());
    }

    public final Scheme get(String str) {
        Args.notNull(str, "Scheme name");
        return (Scheme) this.a.get(str);
    }

    public final Scheme register(Scheme scheme) {
        Args.notNull(scheme, "Scheme");
        return (Scheme) this.a.put(scheme.getName(), scheme);
    }

    public final Scheme unregister(String str) {
        Args.notNull(str, "Scheme name");
        return (Scheme) this.a.remove(str);
    }

    public final List<String> getSchemeNames() {
        return new ArrayList(this.a.keySet());
    }

    public void setItems(Map<String, Scheme> map) {
        if (map != null) {
            this.a.clear();
            this.a.putAll(map);
        }
    }
}
