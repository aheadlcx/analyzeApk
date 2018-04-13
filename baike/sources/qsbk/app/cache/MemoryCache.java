package qsbk.app.cache;

import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryCache {
    static MemoryCache a = null;
    private Map<String, String> b = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long c = 0;
    private long d = 100000;

    public MemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 20);
    }

    public static MemoryCache findOrCreateMemoryCache() {
        if (a == null) {
            a = new MemoryCache();
        }
        return a;
    }

    public void setLimit(long j) {
        this.d = j;
        Log.i("MemoryCache", "MemoryCache will use up to " + ((((double) this.d) / 1024.0d) / 1024.0d) + "MB");
    }

    public String get(String str) {
        try {
            if (this.b.containsKey(str)) {
                return (String) this.b.get(str);
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void put(String str, String str2) {
        try {
            if (this.b.containsKey(str)) {
                this.c -= a((String) this.b.get(str));
            }
            this.b.put(str, str2);
            this.c += a(str2);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        Log.i("MemoryCache", "cache size=" + this.c + " length=" + this.b.size());
        if (this.c > this.d) {
            Iterator it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                this.c -= a((String) ((Entry) it.next()).getValue());
                it.remove();
                if (this.c <= this.d) {
                    break;
                }
            }
            Log.i("MemoryCache", "Clean cache. New size " + this.b.size());
        }
    }

    public void clear() {
        this.b.clear();
    }

    long a(String str) {
        if (str == null) {
            return 0;
        }
        return (long) str.toString().getBytes().length;
    }
}
