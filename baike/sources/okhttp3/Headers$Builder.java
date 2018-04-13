package okhttp3;

import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.message.TokenParser;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.Util;

public final class Headers$Builder {
    final List<String> a = new ArrayList(20);

    Headers$Builder a(String str) {
        int indexOf = str.indexOf(Config.TRACE_TODAY_VISIT_SPLIT, 1);
        if (indexOf != -1) {
            return a(str.substring(0, indexOf), str.substring(indexOf + 1));
        }
        if (str.startsWith(Config.TRACE_TODAY_VISIT_SPLIT)) {
            return a("", str.substring(1));
        }
        return a("", str);
    }

    public Headers$Builder add(String str) {
        int indexOf = str.indexOf(Config.TRACE_TODAY_VISIT_SPLIT);
        if (indexOf != -1) {
            return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
        }
        throw new IllegalArgumentException("Unexpected header: " + str);
    }

    public Headers$Builder add(String str, String str2) {
        b(str, str2);
        return a(str, str2);
    }

    Headers$Builder a(String str, String str2) {
        this.a.add(str);
        this.a.add(str2.trim());
        return this;
    }

    public Headers$Builder removeAll(String str) {
        int i = 0;
        while (i < this.a.size()) {
            if (str.equalsIgnoreCase((String) this.a.get(i))) {
                this.a.remove(i);
                this.a.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    public Headers$Builder set(String str, String str2) {
        b(str, str2);
        removeAll(str);
        a(str, str2);
        return this;
    }

    private void b(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else {
            int i;
            char charAt;
            int length = str.length();
            for (i = 0; i < length; i++) {
                charAt = str.charAt(i);
                if (charAt <= TokenParser.SP || charAt >= '') {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str}));
                }
            }
            if (str2 == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            length = str2.length();
            i = 0;
            while (i < length) {
                charAt = str2.charAt(i);
                if ((charAt > '\u001f' || charAt == '\t') && charAt < '') {
                    i++;
                } else {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str, str2}));
                }
            }
        }
    }

    public String get(String str) {
        for (int size = this.a.size() - 2; size >= 0; size -= 2) {
            if (str.equalsIgnoreCase((String) this.a.get(size))) {
                return (String) this.a.get(size + 1);
            }
        }
        return null;
    }

    public Headers build() {
        return new Headers(this);
    }
}
