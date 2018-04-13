package qsbk.app.core.net.OkHttp;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class a extends LinkedHashMap<String, String> {
    a() {
    }

    protected boolean removeEldestEntry(Entry<String, String> entry) {
        return size() > OkhttpDns.a;
    }
}
