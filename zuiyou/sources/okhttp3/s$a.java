package okhttp3;

import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.c;

public final class s$a {
    final List<String> a = new ArrayList(20);

    s$a a(String str) {
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            return b(str.substring(0, indexOf), str.substring(indexOf + 1));
        }
        if (str.startsWith(":")) {
            return b("", str.substring(1));
        }
        return b("", str);
    }

    public s$a a(String str, String str2) {
        d(str, str2);
        return b(str, str2);
    }

    s$a b(String str, String str2) {
        this.a.add(str);
        this.a.add(str2.trim());
        return this;
    }

    public s$a b(String str) {
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

    public s$a c(String str, String str2) {
        d(str, str2);
        b(str);
        b(str, str2);
        return this;
    }

    private void d(String str, String str2) {
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
                if (charAt <= ' ' || charAt >= '') {
                    throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
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
                    throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt), Integer.valueOf(i), str, str2));
                }
            }
        }
    }

    public s a() {
        return new s(this);
    }
}
