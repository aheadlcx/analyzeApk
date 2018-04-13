package com.facebook.common.d;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class a {
    public static final Map<String, String> a = ImmutableMap.of("mkv", "video/x-matroska");

    public static boolean a(@Nullable String str) {
        return str != null && str.startsWith("video/");
    }

    @Nullable
    public static String b(String str) {
        String c = c(str);
        if (c == null) {
            return null;
        }
        String toLowerCase = c.toLowerCase(Locale.US);
        c = MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase);
        if (c == null) {
            return (String) a.get(toLowerCase);
        }
        return c;
    }

    @Nullable
    private static String c(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0 || lastIndexOf == str.length() - 1) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }
}
