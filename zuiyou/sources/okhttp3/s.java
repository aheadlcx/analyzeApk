package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

public final class s {
    private final String[] a;

    s(s$a s_a) {
        this.a = (String[]) s_a.a.toArray(new String[s_a.a.size()]);
    }

    private s(String[] strArr) {
        this.a = strArr;
    }

    @Nullable
    public String a(String str) {
        return a(this.a, str);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int i) {
        return this.a[i * 2];
    }

    public String b(int i) {
        return this.a[(i * 2) + 1];
    }

    public List<String> b(String str) {
        int a = a();
        List list = null;
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (list == null) {
                    list = new ArrayList(2);
                }
                list.add(b(i));
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public s$a b() {
        s$a s_a = new s$a();
        Collections.addAll(s_a.a, this.a);
        return s_a;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof s) && Arrays.equals(((s) obj).a, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int a = a();
        for (int i = 0; i < a; i++) {
            stringBuilder.append(a(i)).append(": ").append(b(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<String, List<String>> c() {
        Map<String, List<String>> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int a = a();
        for (int i = 0; i < a; i++) {
            String toLowerCase = a(i).toLowerCase(Locale.US);
            List list = (List) treeMap.get(toLowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(toLowerCase, list);
            }
            list.add(b(i));
        }
        return treeMap;
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static s a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        } else if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        } else {
            int i;
            String[] strArr2 = (String[]) strArr.clone();
            for (i = 0; i < strArr2.length; i++) {
                if (strArr2[i] == null) {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
                strArr2[i] = strArr2[i].trim();
            }
            i = 0;
            while (i < strArr2.length) {
                String str = strArr2[i];
                String str2 = strArr2[i + 1];
                if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
                }
            }
            return new s(strArr2);
        }
    }
}
