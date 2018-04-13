package cz.msebera.android.httpclient.conn.util;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.net.IDN;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public final class PublicSuffixMatcher {
    private final Map<String, String> a;
    private final Map<String, String> b;

    public PublicSuffixMatcher(Collection<String> collection, Collection<String> collection2) {
        Args.notNull(collection, "Domain suffix rules");
        this.a = new ConcurrentHashMap(collection.size());
        for (String str : collection) {
            this.a.put(str, str);
        }
        if (collection2 != null) {
            this.b = new ConcurrentHashMap(collection2.size());
            for (String str2 : collection2) {
                this.b.put(str2, str2);
            }
            return;
        }
        this.b = null;
    }

    public String getDomainRoot(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(".")) {
            return null;
        }
        String toLowerCase = str.toLowerCase(Locale.ROOT);
        String str2 = null;
        while (toLowerCase != null) {
            if (this.b == null || !this.b.containsKey(IDN.toUnicode(toLowerCase))) {
                if (!this.a.containsKey(IDN.toUnicode(toLowerCase))) {
                    int indexOf = toLowerCase.indexOf(46);
                    String substring = indexOf != -1 ? toLowerCase.substring(indexOf + 1) : null;
                    if (substring != null && this.a.containsKey("*." + IDN.toUnicode(substring))) {
                        break;
                    }
                    if (indexOf == -1) {
                        toLowerCase = str2;
                    }
                    str2 = toLowerCase;
                    toLowerCase = substring;
                } else {
                    break;
                }
            }
            return toLowerCase;
        }
        return str2;
    }

    public boolean matches(String str) {
        boolean z = true;
        if (str == null) {
            return false;
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        if (getDomainRoot(str) != null) {
            z = false;
        }
        return z;
    }
}
